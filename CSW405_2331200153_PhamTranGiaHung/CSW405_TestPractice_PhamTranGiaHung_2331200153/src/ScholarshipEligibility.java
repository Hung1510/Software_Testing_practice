public class ScholarshipEligibility {

    public static String calculateScholarshipEligibility(double gpa, int extracurricularActivities, int volunteerHours) {
        if (gpa < 0.0 || gpa > 4.0) {
            return "Invalid";
        }
        if (extracurricularActivities < 0 || extracurricularActivities > 10) {
            return "Invalid";
        }
        if (volunteerHours < 0 || volunteerHours > 300) {
            return "Invalid";
        }

        //the code state scholarship GPA need 3.5, but the assignment state only need >=3
        //if (gpa >= 3.5 && extracurricularActivities >= 5 && volunteerHours >= 100)
        if (gpa >= 3.0 && extracurricularActivities >= 5 && volunteerHours >= 100) {
            return "Full scholarship";

        //the code state scholarship GPA need 3.0, but the assignment state only need >= 2.5
        // } else if (gpa >= 3.0 && extracurricularActivities >= 3 && volunteerHours >= 50) {
        } else if (gpa >= 2.5 && extracurricularActivities >= 3 && volunteerHours >= 50) {
            return "Partial scholarship";
        } else {
            return "Not eligible";
        }
    }
}