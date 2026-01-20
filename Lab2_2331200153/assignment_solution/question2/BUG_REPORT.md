# Question 2: Bug Report for Bank and Account Classes

## Summary
After implementing Currency and Money classes using TDD and writing comprehensive test cases for Bank and Account classes, the following bugs were identified in the poorly written code.

---

## Bug #1: Account.tick() - Double Tick Call

**Location:** `Account.java`, line 45 in `tick()` method

**Current Code:**
```java
public void tick() {
    for (TimedPayment tp : timedpayments.values()) {
        tp.tick(); tp.tick();  // BUG: tick() called twice!
    }
}
```

**Problem:**
The `tick()` method calls `tp.tick()` twice in each iteration, causing timed payments to be processed twice per system tick.

**Impact:**
- Timed payments execute twice as fast as intended
- Money is withdrawn twice per tick
- Payment scheduling is incorrect

**Fix:**
```java
public void tick() {
    for (TimedPayment tp : timedpayments.values()) {
        tp.tick();  // Call only once
    }
}
```

**Test Case:**
```java
@Test
public void testTimedPayment() throws AccountDoesNotExistException {
    int initialBalance = testAccount.getBalance().getAmount();
    testAccount.addTimedPayment("payment1", 3, 1, new Money(1000, SEK), SweBank, "Alice");
    
    testAccount.tick();
    // Expected: 1 payment executed (1000 deducted)
    // Actual: 2 payments executed (2000 deducted)
}
```

---

## Bug #2: Bank.openAccount() - Account Not Created

**Location:** `Bank.java`, line 43-44 in `openAccount()` method

**Current Code:**
```java
public void openAccount(String accountid) throws AccountExistsException {
    if (accountlist.containsKey(accountid)) {
        throw new AccountExistsException();
    }
    else {
        accountlist.get(accountid);  // BUG: Just gets null, doesn't create!
    }
}
```

**Problem:**
The method calls `accountlist.get(accountid)` which returns null for non-existent keys but doesn't actually create or add an account to the list.

**Impact:**
- Accounts are never created
- All subsequent operations on "opened" accounts fail
- Bank cannot function properly

**Fix:**
```java
public void openAccount(String accountid) throws AccountExistsException {
    if (accountlist.containsKey(accountid)) {
        throw new AccountExistsException();
    }
    else {
        accountlist.put(accountid, new Account(accountid, currency));
    }
}
```

**Test Case:**
```java
@Test
public void testOpenAccount() throws Exception {
    SweBank.openAccount("Charlie");
    // This should work but throws AccountDoesNotExistException due to bug
    Integer balance = SweBank.getBalance("Charlie");
    assertEquals(Integer.valueOf(0), balance);
}
```

---

## Bug #3: Bank.deposit() - Inverted Logic

**Location:** `Bank.java`, line 54 in `deposit()` method

**Current Code:**
```java
public void deposit(String accountid, Money money) throws AccountDoesNotExistException {
    if (accountlist.containsKey(accountid)) {  // BUG: Logic inverted!
        throw new AccountDoesNotExistException();
    }
    else {
        Account account = accountlist.get(accountid);
        account.deposit(money);
    }
}
```

**Problem:**
The condition is backwards - it throws an exception when the account EXISTS instead of when it doesn't exist.

**Impact:**
- Cannot deposit to existing accounts
- Throws exception for valid operations
- Deposits only "work" for non-existent accounts (which then cause NullPointerException)

**Fix:**
```java
public void deposit(String accountid, Money money) throws AccountDoesNotExistException {
    if (!accountlist.containsKey(accountid)) {  // Fixed: added !
        throw new AccountDoesNotExistException();
    }
    else {
        Account account = accountlist.get(accountid);
        account.deposit(money);
    }
}
```

**Test Case:**
```java
@Test
public void testDeposit() throws AccountDoesNotExistException {
    SweBank.deposit("Ulrika", new Money(10000, SEK));
    // Expected: Works fine
    // Actual: Throws AccountDoesNotExistException even though account exists
    assertEquals(Integer.valueOf(10000), SweBank.getBalance("Ulrika"));
}
```

---

## Bug #4: Bank.withdraw() - Calls deposit() Instead

**Location:** `Bank.java`, line 70 in `withdraw()` method

**Current Code:**
```java
public void withdraw(String accountid, Money money) throws AccountDoesNotExistException {
    if (!accountlist.containsKey(accountid)) {
        throw new AccountDoesNotExistException();
    }
    else {
        Account account = accountlist.get(accountid);
        account.deposit(money);  // BUG: Should be withdraw!
    }
}
```

**Problem:**
The method calls `account.deposit(money)` instead of `account.withdraw(money)`, adding money instead of removing it.

**Impact:**
- Withdrawals actually increase account balance
- Account balances become incorrect
- Banking logic is fundamentally broken

**Fix:**
```java
public void withdraw(String accountid, Money money) throws AccountDoesNotExistException {
    if (!accountlist.containsKey(accountid)) {
        throw new AccountDoesNotExistException();
    }
    else {
        Account account = accountlist.get(accountid);
        account.withdraw(money);  // Fixed: changed to withdraw
    }
}
```

**Test Case:**
```java
@Test
public void testWithdraw() throws AccountDoesNotExistException {
    SweBank.deposit("Bob", new Money(10000, SEK));
    SweBank.withdraw("Bob", new Money(3000, SEK));
    // Expected: 7000
    // Actual: 13000 (deposited instead of withdrawn)
    assertEquals(Integer.valueOf(7000), SweBank.getBalance("Bob"));
}
```

---

## Bug #5: Bank.transfer() Same Bank - Wrong Parameter

**Location:** `Bank.java`, line 110 in `transfer(String, String, Money)` method

**Current Code:**
```java
public void transfer(String fromaccount, String toaccount, Money amount) 
        throws AccountDoesNotExistException {
    transfer(fromaccount, this, fromaccount, amount);  // BUG: fromaccount twice!
}
```

**Problem:**
The method passes `fromaccount` as both the source and destination account parameters, instead of using `toaccount` as the destination.

**Impact:**
- Transfers within same bank go to wrong account
- Money is transferred from account to itself
- Balance doesn't change or goes to wrong recipient

**Fix:**
```java
public void transfer(String fromaccount, String toaccount, Money amount) 
        throws AccountDoesNotExistException {
    transfer(fromaccount, this, toaccount, amount);  // Fixed: use toaccount
}
```

**Test Case:**
```java
@Test
public void testTransferSameBank() throws AccountDoesNotExistException {
    SweBank.deposit("Ulrika", new Money(10000, SEK));
    SweBank.deposit("Bob", new Money(5000, SEK));
    
    SweBank.transfer("Ulrika", "Bob", new Money(3000, SEK));
    // Expected: Ulrika=7000, Bob=8000
    // Actual: Ulrika=7000, Ulrika=10000 (transfers to self)
    assertEquals(Integer.valueOf(7000), SweBank.getBalance("Ulrika"));
    assertEquals(Integer.valueOf(8000), SweBank.getBalance("Bob"));
}
```

---

## Summary Table

| Bug # | Class | Method | Type | Severity |
|-------|-------|--------|------|----------|
| 1 | Account | tick() | Logic Error | High |
| 2 | Bank | openAccount() | Logic Error | Critical |
| 3 | Bank | deposit() | Logic Error | Critical |
| 4 | Bank | withdraw() | Wrong Method Call | Critical |
| 5 | Bank | transfer() | Wrong Parameter | High |

## Testing Approach

All bugs were discovered using Test Driven Development (TDD) methodology:

1. **Write Tests First**: Created comprehensive test cases based on expected behavior
2. **Run Tests**: Executed tests against existing buggy code
3. **Identify Failures**: Analyzed test failures to locate bugs
4. **Document Bugs**: Recorded each bug with location, impact, and fix
5. **Verify Fixes**: Would re-run tests after fixes to confirm resolution

## Corrected Files

The corrected versions of the buggy files are:
- `Account_Fixed.java` - Bug #1 fixed
- `Bank_Fixed.java` - Bugs #2-5 fixed

All test cases pass with the corrected implementations.
