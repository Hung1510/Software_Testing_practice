/**
 * Personal Number class representing a Swedish-style personal number
 * Format: YYMMDD-XYZC
 * where YYMMDD is date of birth, XYZ is serial (Z indicates gender), C is checksum
 */
public class PersonalNumber {
    private String year;
    private String month;
    private String day;
    private String serialNumber;
    private int checksum;
    
    /**
     * Constructor for PersonalNumber
     * @param personalNumber 10-digit personal number string (YYMMDDXYZC)
     * @throws IllegalArgumentException if personal number is invalid
     */
    public PersonalNumber(String personalNumber) {
        // Validate format
        if (personalNumber == null || personalNumber.length() != 10) {
            throw new IllegalArgumentException("Personal number must be exactly 10 digits");
        }
        
        // Check if all characters are digits
        if (!personalNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Personal number must contain only digits");
        }
        
        // Parse components
        this.year = personalNumber.substring(0, 2);
        this.month = personalNumber.substring(2, 4);
        this.day = personalNumber.substring(4, 6);
        this.serialNumber = personalNumber.substring(6, 9);
        this.checksum = Character.getNumericValue(personalNumber.charAt(9));
        
        // Validate date components
        validateDate();
        
        // Validate checksum
        int calculatedChecksum = calculateChecksum(personalNumber.substring(0, 9));
        if (calculatedChecksum != this.checksum) {
            throw new IllegalArgumentException("Invalid checksum. Expected: " + calculatedChecksum + ", Got: " + this.checksum);
        }
    }
    
    /**
     * Validate the date components
     * @throws IllegalArgumentException if date is invalid
     */
    private void validateDate() {
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
        
        // Validate month (1-12)
        if (monthInt < 1 || monthInt > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        
        // Validate day (1-31, simplified validation)
        if (dayInt < 1 || dayInt > 31) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
        
        // More detailed day validation based on month
        if (monthInt == 2 && dayInt > 29) {
            throw new IllegalArgumentException("Invalid day for February: " + day);
        }
        
        if ((monthInt == 4 || monthInt == 6 || monthInt == 9 || monthInt == 11) && dayInt > 30) {
            throw new IllegalArgumentException("Invalid day for month " + month + ": " + day);
        }
    }
    
    /**
     * Calculate checksum according to the algorithm:
     * 1. Multiply digits alternately by 2 and 1
     * 2. If result > 9, sum the digits (e.g., 12 -> 1+2 = 3)
     * 3. Sum all results
     * 4. Take last digit of sum, subtract from 10
     * 5. If result is 10, checksum is 0
     * 
     * @param digits First 9 digits of personal number
     * @return calculated checksum
     */
    private int calculateChecksum(String digits) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            int multiplier = (i % 2 == 0) ? 2 : 1;
            int product = digit * multiplier;
            
            // If product > 9, sum the digits
            if (product > 9) {
                sum += (product / 10) + (product % 10);
            } else {
                sum += product;
            }
        }
        
        // Get last digit of sum
        int lastDigit = sum % 10;
        
        // Calculate checksum
        int checksum = (10 - lastDigit) % 10;
        
        return checksum;
    }
    
    /**
     * Get the date of birth in format YYMMDD
     * @return date string
     */
    public String getDate() {
        return year + month + day;
    }
    
    /**
     * Get the year of birth (YY)
     * @return year string
     */
    public String getYear() {
        return year;
    }
    
    /**
     * Get the month of birth (MM)
     * @return month string
     */
    public String getMonth() {
        return month;
    }
    
    /**
     * Get the day of birth (DD)
     * @return day string
     */
    public String getDay() {
        return day;
    }
    
    /**
     * Get the serial number (XYZ)
     * @return serial number string
     */
    public String getSerialNumber() {
        return serialNumber;
    }
    
    /**
     * Get the sex of the person based on the third digit of serial number (Z)
     * Even Z = Female, Odd Z = Male
     * @return "Male" or "Female"
     */
    public String getSex() {
        int z = Character.getNumericValue(serialNumber.charAt(2));
        return (z % 2 == 0) ? "Female" : "Male";
    }
    
    /**
     * Get the checksum digit
     * @return checksum
     */
    public int getCheckSum() {
        return checksum;
    }
    
    /**
     * String representation of personal number
     * @return formatted personal number
     */
    @Override
    public String toString() {
        return year + month + day + "-" + serialNumber + checksum;
    }
}
