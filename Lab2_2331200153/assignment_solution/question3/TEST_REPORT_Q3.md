# Question 3: Parking Garage System - TDD Test Report

## Test Driven Development Process

### Phase 1: Write Failing Tests (Red Phase)

The following test cases were written BEFORE implementing the system:

#### 1. Basic Functionality Tests

**testGarageInitialization()**
- Purpose: Verify garage starts in correct initial state
- Expected: capacity=10, available=10, vehicles=0, revenue=$0

**testCarEntry()**
- Purpose: Verify basic car parking works correctly
- Expected: Spaces decrease by 1, fee of $8 collected

**testVehicleExit()**
- Purpose: Verify vehicles can leave garage
- Expected: Spaces increase, vehicle removed, revenue unchanged

#### 2. Vehicle Type Tests

**testLowEmissionCarFee()**
- Purpose: Verify low-emission cars charged half fee
- Expected: $4 fee instead of $8

**testTruckSpaceCalculation()**
- Purpose: Verify trucks occupy correct number of spaces
- Test Data: 25000 lbs truck
- Expected: Occupies 3 spaces (25000/10000 rounded up)

**testTruckFeeCalculation()**
- Purpose: Verify truck fee calculation
- Test Data: 25000 lbs -> $25, 50000 lbs -> $50
- Formula: (weight/10000) * $10

#### 3. Exception Handling Tests

**testInsufficientSpace()**
- Purpose: Verify exception when garage full
- Setup: Fill garage to 9 spaces
- Action: Try to park 3-space truck
- Expected: InsufficientSpaceException thrown

**testDuplicateEntry()**
- Purpose: Verify exception for duplicate parking
- Action: Park same vehicle twice
- Expected: VehicleAlreadyParkedException thrown

**testExitNonExistentVehicle()**
- Purpose: Verify exception when removing non-existent vehicle
- Expected: VehicleNotParkedException thrown

#### 4. State Management Tests

**testAvailableSpaces()**
- Purpose: Verify accurate space tracking
- Actions: Multiple enters/exits with different vehicle sizes
- Verify: Correct space count after each operation

**testTotalRevenue()**
- Purpose: Verify revenue accumulation
- Actions: Park various vehicle types
- Verify: Correct cumulative revenue
- Important: Revenue doesn't decrease on exit

**testMultipleVehicleTypes()**
- Purpose: Verify mixed vehicle scenario
- Actions: Park cars, eco-cars, trucks in combination
- Verify: Correct spaces and revenue calculations

#### 5. Validation Tests

**testNullVehicleEntry()**
- Expected: IllegalArgumentException for null vehicle

**testNullLicenseExit()**
- Expected: IllegalArgumentException for null license

**testInvalidCarCapacity()**
- Expected: IllegalArgumentException for capacity ≤ 0

**testInvalidTruckWeight()**
- Expected: IllegalArgumentException for weight ≤ 0

**testInvalidGarageCapacity()**
- Expected: IllegalArgumentException for capacity ≤ 0

#### 6. Complex Integration Tests

**testComplexScenario()**
- Purpose: Simulate realistic usage pattern
- Scenario: Morning rush, deliveries, exits, more arrivals
- Verify: System maintains consistency throughout

### Phase 2: Implementation (Green Phase)

After writing all tests, implemented the following classes to make tests pass:

#### Class Structure

```
Vehicle (abstract base)
├── Car
│   └── LowEmissionCar
└── CargoTruck

ParkingGarage (main system)

Exceptions:
├── InsufficientSpaceException
├── VehicleAlreadyParkedException
└── VehicleNotParkedException
```

#### Key Implementation Details

**Vehicle Class**
- Abstract base with common license number handling
- Abstract methods: getSpacesRequired(), getFee()
- Proper equals/hashCode based on license number

**Car Class**
- Fixed 1 space requirement
- Fixed $8 fee
- Passenger capacity tracking

**LowEmissionCar Class**
- Extends Car
- Overrides fee to $4 (50% discount)

**CargoTruck Class**
- Space calculation: ceil(weight / 10000)
- Fee calculation: (weight / 10000) * $10
- Weight validation

**ParkingGarage Class**
```java
Key Methods:
- enter(Vehicle) - Park vehicle, collect fee
- exit(String license) - Remove vehicle
- getAvailableSpaces() - Calculate free spaces
- getTotalRevenue() - Return accumulated fees
- isVehicleParked(String) - Check if vehicle present
```

**Exception Classes**
- Descriptive error messages
- Constructors with license numbers where applicable

### Phase 3: Refactor (Blue Phase)

#### Code Quality Improvements

1. **Validation Enhancement**
   - Added null checks
   - Added empty string checks
   - Added range validations (positive values)

2. **Error Messages**
   - Made exception messages more descriptive
   - Included context (license numbers, space needs)

3. **Documentation**
   - Added comprehensive JavaDoc
   - Documented formulas and calculations
   - Included examples in comments

4. **Encapsulation**
   - Made fields private
   - Provided appropriate getters
   - No setters for immutable properties

5. **Constants**
   - Extracted magic numbers to named constants
   - PARKING_FEE, FEE_PER_10000_LBS, etc.

## Test Results Summary

All 20 test cases pass successfully:

### Basic Functionality (3 tests)
✓ testGarageInitialization
✓ testCarEntry  
✓ testVehicleExit

### Vehicle Types (3 tests)
✓ testLowEmissionCarFee
✓ testTruckSpaceCalculation
✓ testTruckFeeCalculation

### Exception Handling (3 tests)
✓ testInsufficientSpace (expected exception)
✓ testDuplicateEntry (expected exception)
✓ testExitNonExistentVehicle (expected exception)

### State Management (3 tests)
✓ testAvailableSpaces
✓ testTotalRevenue
✓ testMultipleVehicleTypes

### Validation (5 tests)
✓ testNullVehicleEntry (expected exception)
✓ testNullLicenseExit (expected exception)
✓ testInvalidCarCapacity (expected exception)
✓ testInvalidTruckWeight (expected exception)
✓ testInvalidGarageCapacity (expected exception)

### Additional Tests (3 tests)
✓ testGetVehicle
✓ testVehicleCreation
✓ testComplexScenario

## Design Decisions

### 1. Space Calculation for Trucks
Used Math.ceil() to round up fractional spaces:
- 25000 lbs -> 2.5 spaces -> 3 spaces
- 15000 lbs -> 1.5 spaces -> 2 spaces

### 2. Fee Collection Timing
Fees collected on entry, not exit:
- Simpler logic
- Prevents vehicles leaving without paying
- More realistic (pay on entry or validation)

### 3. License Number as Key
Used license number strings as unique identifiers:
- Natural choice
- Easy to work with
- Proper equals/hashCode implemented

### 4. Immutable Vehicle Properties
Once created, vehicle properties cannot change:
- Weight, capacity, license don't change during parking
- Prevents inconsistent state
- Simpler reasoning about system

### 5. Revenue Never Decreases
Revenue accumulates, never reduced:
- Matches real-world (no refunds on exit)
- Simpler implementation
- Clear audit trail

## TDD Benefits Demonstrated

1. **Clear Requirements**
   - Tests served as executable specifications
   - Clarified edge cases early (rounding, validation)

2. **Early Bug Prevention**
   - Caught space calculation issues in design
   - Identified need for various validations

3. **Design Improvement**
   - TDD led to clean inheritance hierarchy
   - Proper separation of concerns
   - Abstract base class emerged naturally

4. **Confidence in Changes**
   - Safe to refactor with comprehensive tests
   - Easy to add new vehicle types

5. **Documentation**
   - Tests document expected behavior
   - Examples of usage patterns

## Future Enhancements

Potential additions identified during TDD process:
1. Maximum stay time
2. Hourly vs daily rates
3. Reserved spaces
4. Vehicle type restrictions
5. Parking space assignment (specific spots)
6. History/logging of entries/exits

## Conclusion

The Parking Garage system was successfully developed using pure TDD methodology. All 20 test cases pass, demonstrating:

- Correct space management
- Accurate fee calculation
- Proper exception handling
- Support for three vehicle types
- Robust validation

The TDD approach resulted in clean, well-tested code with high confidence in correctness.
