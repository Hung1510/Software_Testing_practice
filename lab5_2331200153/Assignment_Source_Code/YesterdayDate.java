

public class YesterdayDate {
	public int day, month, year, yes_day, yes_month, yes_year;
	public String s1;

	public int check(int day, int month) {
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31)
			return 1;
		else
			return 0;
	}

	public int isleap(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
			return 1;
		else
			return 0;
	}

	public void setYearmonthdate(int year, int date, int month) {
		this.year = year;
		this.day = date;
		this.month = month;
	}

	public String yesterday_date() {
		yes_month = month;
		yes_year = year;
		if (day < 1 || day > 31) {
			s1 = "Value of day, not in the range 1...31";
			return s1;
		}
		if (month < 1 || month > 12) {
			s1 = "value of month, not in the range 1....12";
			return s1;
		}
		if ((check(day, month)) == 1) {
			s1 = "value of day, not in the range day<=30";
			return s1;
		}

		if (year < 1800 || year > 2025) {
			s1 = "value of year, not in the range 1800.......2025";
			return s1;
		}
		if (month == 2) {
			if ((isleap(year) == 1) && day > 29) {
				s1 = "invalid date input for leap year";
				return s1;
			} else if ((isleap(year) == 0) && day > 28) {
				s1 = "invalid date input for not a leap year";
				return s1;
			}
		}

		switch (month) {
		case 5:
		case 7:
		case 10:
		case 12:
			if (day > 1)
				yes_day = day - 1;
			else {
				yes_day = 31;
				yes_month = month - 1;
			}
			break;
		case 2:
		case 4:
		case 6:
		case 8:
		case 9:
		case 11:
			if (day > 1)
				yes_day = day - 1;
			else {
				yes_day = 30;
				yes_month = month - 1;
			}
			break;

		case 1:
			if (day > 1)
				yes_day = day - 1;
			else {
				yes_day = 31;
				yes_month = 12;
				if (year == 1800) {
					s1 = "the previous day is out of boundary value of year";
					return s1;
				} else
					yes_year = year - 1;
			}
			break;
		case 3:
			if (day > 1)
				yes_day = day - 1;
			else if ((isleap(year) == 1) && day == 1) {
				yes_day = 29;
				yes_month = 2;
			}
			else if (day == 1) {
				yes_day = 28;
				yes_month = 2;
			}
			break;
		}
		s1 = "Previous Date is :" + yes_day + "-" + yes_month + "-" + yes_year;
		return s1;
	}
}



