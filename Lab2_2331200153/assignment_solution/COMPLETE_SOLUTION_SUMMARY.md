# Practice Assignment 2 - Complete Solution

## Overview
This document provides a comprehensive summary of all solutions for Practice Assignment 2, which focuses on Test Driven Development (TDD) methodology.

---

## Question 1: Personal Number System (60 Points)

### Requirements
Develop a Personal Number generator system following TDD principles. Personal numbers have format YYMMDD-XYZC where:
- YYMMDD: Date of birth
- XYZ: Serial number (Z indicates gender - even=female, odd=male)
- C: Checksum digit

### Deliverables
1. **PersonalNumber.java** - Main implementation class
2. **PersonalNumberTest.java** - Comprehensive JUnit test cases
3. **TEST_REPORT_Q1.md** - Detailed TDD process documentation

### Key Features Implemented
- Date validation (year, month, day)
- Checksum calculation and validation
- Gender determination from serial number
- Exception handling for invalid inputs
- All required getter methods

### Test Coverage
- ✓ Checksum calculation accuracy
- ✓ Date component extraction (getDate, getYear, getMonth, getDay)
- ✓ Gender determination (getSex)
- ✓ Invalid input handling (length, format, checksum, date ranges)
- ✓ Edge cases (checksum = 0 when result is 10)

### TDD Process Followed
1. **Red Phase**: Wrote 11 failing test cases first
2. **Green Phase**: Implemented PersonalNumber class to pass all tests
3. **Refactor Phase**: Improved code quality, added validation, documentation

---

## Question 2: Currency/Money Classes and Bug Finding (30 Points)

### Part A: Implement Currency and Money Classes (TDD)

#### Completed Implementations

**Currency.java**
- `universalValue(Integer amount)` - Convert amount to universal currency
- `getName()` - Return currency name
- `getRate()` - Return exchange rate
- `setRate(Double rate)` - Update exchange rate  
- `valueInThisCurrency(Integer amount, Currency other)` - Convert between currencies

**Money.java** (Created from scratch)
- `getAmount()` - Return money amount
- `getCurrency()` - Return currency object
- `toString()` - Format as "amount currencyname"
- `universalValue()` - Get universal value
- `equals(Money other)` - Compare money values
- `add(Money other)` - Add two money amounts
- `sub(Money other)` - Subtract money amounts
- `isZero()` - Check if amount is zero
- `negate()` - Negate the amount
- `compareTo(Money other)` - Compare two money objects

#### Test Files Created
- **CurrencyTest.java** - Tests for all Currency methods
- **MoneyTest.java** - Tests for all Money methods

### Part B: Find Bugs in Bank and Account Classes

#### Bugs Identified and Documented

**Bug #1: Account.tick() - Double Tick Call**
- **Location**: Account.java, line 48
- **Issue**: `tp.tick()` called twice per iteration
- **Impact**: Timed payments execute twice as fast
- **Fix**: Remove duplicate tick() call

**Bug #2: Bank.openAccount() - Account Not Created**
- **Location**: Bank.java, line 44
- **Issue**: Uses `accountlist.get()` instead of `accountlist.put()`
- **Impact**: Accounts never created, bank non-functional
- **Fix**: Change to `accountlist.put(accountid, new Account(accountid, currency))`

**Bug #3: Bank.deposit() - Inverted Logic**
- **Location**: Bank.java, line 54
- **Issue**: Throws exception when account EXISTS
- **Impact**: Cannot deposit to existing accounts
- **Fix**: Add `!` to condition: `if (!accountlist.containsKey(accountid))`

**Bug #4: Bank.withdraw() - Wrong Method Call**
- **Location**: Bank.java, line 70
- **Issue**: Calls `account.deposit()` instead of `account.withdraw()`
- **Impact**: Withdrawals increase balance instead of decreasing
- **Fix**: Change to `account.withdraw(money)`

**Bug #5: Bank.transfer() - Wrong Parameter**
- **Location**: Bank.java, line 110
- **Issue**: Passes `fromaccount` twice instead of using `toaccount`
- **Impact**: Transfers within same bank go to wrong account
- **Fix**: Change to `transfer(fromaccount, this, toaccount, amount)`

#### Deliverables
1. **Currency_Completed.java** - Fully implemented Currency class
2. **Money.java** - Complete Money class implementation
3. **CurrencyTest.java / MoneyTest.java** - Test suites
4. **AccountTest_Updated.java** - Tests exposing Account bugs
5. **BankTest_Updated.java** - Tests exposing Bank bugs
6. **BUG_REPORT.md** - Comprehensive bug documentation with test cases
7. **Account_Fixed.java** - Corrected Account class
8. **Bank_Fixed.java** - Corrected Bank class

---

## Question 3: Parking Garage System (Bonus - 60 Points)

### Requirements
Develop a parking garage management system using TDD with the following features:

#### System Requirements
- Track vehicles currently parked
- Report available spaces and current value
- Handle vehicle entry/exit with fee assessment
- Support three vehicle types: Cars, Low-emission Cars, Cargo Trucks

#### Vehicle Specifications
- **Cars**: 
  - License number
  - Passenger capacity
  - Occupies 1 space
  - Fee: $8

- **Low-emission Cars**:
  - All car properties
  - Fee: $4 (half of regular car)

- **Cargo Trucks**:
  - License number  
  - Gross vehicle weight
  - Occupies weight/10,000 spaces
  - Fee: $10 per 10,000 pounds

#### Exception Handling
- Insufficient space
- Vehicle already in garage
- Vehicle not in garage (on exit)

### Implementation Structure

#### Classes Designed

**1. Vehicle (Abstract Base Class)**
```java
abstract class Vehicle {
    protected String licenseNumber;
    public abstract int getSpacesRequired();
    public abstract double getFee();
}
```

**2. Car**
```java
class Car extends Vehicle {
    private int passengerCapacity;
    // Occupies 1 space, $8 fee
}
```

**3. LowEmissionCar**
```java
class LowEmissionCar extends Car {
    // Occupies 1 space, $4 fee (half of regular car)
}
```

**4. CargoTruck**
```java
class CargoTruck extends Vehicle {
    private int grossWeight;
    // Occupies grossWeight/10000 spaces
    // Fee: $10 per 10,000 pounds
}
```

**5. ParkingGarage**
```java
class ParkingGarage {
    private int totalCapacity;
    private Map<String, Vehicle> parkedVehicles;
    private double totalRevenue;
    
    public void enter(Vehicle v) throws InsufficientSpaceException, 
                                         VehicleAlreadyParkedException;
    public void exit(String licenseNumber) throws VehicleNotParkedException;
    public int getAvailableSpaces();
    public double getTotalRevenue();
}
```

**6. Custom Exceptions**
- `InsufficientSpaceException`
- `VehicleAlreadyParkedException`
- `VehicleNotParkedException`

### TDD Process for Question 3

#### Phase 1: Write Failing Tests

**Test Cases Implemented:**
1. `testGarageInitialization()` - Verify initial state
2. `testCarEntry()` - Test car entering garage
3. `testLowEmissionCarFee()` - Verify reduced fee
4. `testTruckSpaceCalculation()` - Test truck space requirements
5. `testTruckFeeCalculation()` - Verify truck fee calculation
6. `testInsufficientSpace()` - Exception when garage full
7. `testDuplicateEntry()` - Exception for duplicate vehicle
8. `testVehicleExit()` - Test vehicle leaving garage
9. `testExitNonExistentVehicle()` - Exception for vehicle not in garage
10. `testAvailableSpaces()` - Track space availability correctly
11. `testTotalRevenue()` - Accumulate fees correctly
12. `testMultipleVehicleTypes()` - Mixed vehicle scenarios

#### Phase 2: Implementation

Created all classes to satisfy test requirements.

#### Phase 3: Refactoring

- Extracted common vehicle logic to abstract base
- Improved exception messages
- Added input validation
- Enhanced documentation

### Deliverables
1. **Vehicle.java** - Abstract base class
2. **Car.java** - Car implementation
3. **LowEmissionCar.java** - Low-emission car implementation
4. **CargoTruck.java** - Truck implementation
5. **ParkingGarage.java** - Main garage system
6. **InsufficientSpaceException.java** - Custom exception
7. **VehicleAlreadyParkedException.java** - Custom exception
8. **VehicleNotParkedException.java** - Custom exception
9. **ParkingGarageTest.java** - Comprehensive test suite
10. **TEST_REPORT_Q3.md** - TDD process documentation

---

## Summary

### Files Organized by Question

**Question 1 (60 points):**
- `/question1/PersonalNumber.java`
- `/question1/PersonalNumberTest.java`
- `/question1/TEST_REPORT_Q1.md`

**Question 2 (30 points):**
- `/question2/b_Money/Currency_Completed.java`
- `/question2/b_Money/Money.java`
- `/question2/b_Money/CurrencyTest.java` (completed)
- `/question2/b_Money/MoneyTest.java`
- `/question2/b_Money/AccountTest_Updated.java`
- `/question2/b_Money/BankTest_Updated.java`
- `/question2/b_Money/Account_Fixed.java`
- `/question2/b_Money/Bank_Fixed.java`
- `/question2/BUG_REPORT.md`

**Question 3 (60 bonus points):**
- `/question3/Vehicle.java`
- `/question3/Car.java`
- `/question3/LowEmissionCar.java`
- `/question3/CargoTruck.java`
- `/question3/ParkingGarage.java`
- `/question3/*Exception.java` (3 files)
- `/question3/ParkingGarageTest.java`
- `/question3/TEST_REPORT_Q3.md`

### Total Score Potential: 150 points (60 + 30 + 60 bonus)

### TDD Principles Demonstrated Throughout

1. **Test-First Development**: All tests written before implementation
2. **Incremental Development**: Small, focused tests driving small code changes
3. **Continuous Refactoring**: Code improved after tests pass
4. **Comprehensive Coverage**: Edge cases and error conditions tested
5. **Clear Documentation**: Test reports explain TDD process step-by-step

---

## How to Run Tests

### Prerequisites
- Java JDK 8 or higher
- JUnit 4.x

### Compilation
```bash
# Question 1
javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar PersonalNumber.java PersonalNumberTest.java

# Question 2
cd question2/b_Money
javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar *.java

# Question 3
cd question3
javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar *.java
```

### Test Execution
```bash
# Question 1
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore PersonalNumberTest

# Question 2
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore b_Money.MoneyTest
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore b_Money.CurrencyTest
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore b_Money.AccountTest_Updated
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore b_Money.BankTest_Updated

# Question 3
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore ParkingGarageTest
```

---

## Conclusion

This assignment demonstrates comprehensive understanding of Test Driven Development methodology through three progressively complex problems. Each solution follows the Red-Green-Refactor cycle, with extensive test coverage and clear documentation of the development process.
