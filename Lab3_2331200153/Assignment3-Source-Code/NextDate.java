

public class NextDate {
	public int day, month, year,tomm_day,tomm_month,tomm_year;
	public  String s1;
	public int check(int day,int month)
	{
	if((month==4||month==6||month==9 ||month==11) && day==31)
	return 1;
	else
	return 0;
	}
	public int isleap(int year)
	{
	if((year%4==0 && year%100!=0) || year%400==0)
	return 1;
	else
	return 0;
	}
	
	public void setYearmonthdate(int year,int date, int month) {
		this.year = year;
		this.day=date;
		this.month=month;
	}	
		
	
	public String next_date()
	{
		tomm_month=month;
		tomm_year= year;
		if(day<1 || day>31)
		{
			s1="Value of day, not in the range 1...31";
			return s1;
		}
		if(month<1 || month>12)
		{
			s1="value of month, not in the range 1....12";
			return s1;
		}
		 if((check(day,month))==1)
		{
			s1="value of day, not in the range day<=30";
			return s1;
		}
		
		if(year<=1800 || year>2025)
		{
			s1="value of year, not in the range 1800.......2025";
			return s1;
		}
		if(month==2)
		{
		if((isleap(year)==1) && day>29)
		{
			s1="invalid date input for leap year";
			return s1;
		}
		else if((isleap(year)==0)&& day>=28)
		{
			s1="invalid date input for not a leap year";
			return s1;
		}
		}
		
		switch (month)
		{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:if(day<31)
		tomm_day=day+1;
		else
		{
		tomm_day=1;
		tomm_month=month+1;
		}
		break;
		case 4:
		case 6:
		case 9:
		case 11: if(day<30)
		tomm_day=day+1;
		else
		{
		tomm_day=1;
		tomm_month=month+1;
		}
		break;
		
		case 12: if(day<=31)
		tomm_day=day+1;
		else
		{
		tomm_day=1;
		tomm_month=1;
		if(year==2025)
		{
			s1="the next day is out of boundary value of year";
		tomm_year=year+1;
		
		}
		else
		tomm_year=year+1;
		}
		break;
		case 2:
		if(day<28)
		tomm_day=day+1;
		else if((isleap(year)==1)&& day==28)
		tomm_day=day+1;
		else 
		{
		tomm_day=1;
		tomm_month=3;
		}
		break;
		}
		s1="Next Date is :"+tomm_day+"-"+tomm_month+"-"+tomm_year;
		return s1;
		}
}
