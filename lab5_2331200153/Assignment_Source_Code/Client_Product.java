

public class Client_Product {
String Gender;
String City_Dwellers;
String productTypes="No Product";
int age;
public void setInput(String Gender, String City_Dwellers, int age)
{
	this.Gender=Gender;
	this.City_Dwellers=City_Dwellers;
	this.age=age;
}

public String assignProduct()
{
	if(Gender.equals("Female"))
	{
		if(City_Dwellers.equals("Yes"))
		{
			if((age<35))
			{
				productTypes="W X and Y";
				
			}
			if((age>=35)||(age<=65))
			{
				productTypes="X and Y";
			}
			if(age>65)
			{
				productTypes="Only Y";
				
			}
		}
		else if(City_Dwellers.contentEquals("No"))
			{
			if((age<35))
			{
				productTypes="W and X";
			}
			if((age>=35)&&(age<=65))
			{
				
				productTypes="Only X";
			}
			}
	}
	else
		if(Gender.contentEquals("Male"))
		{
			if(City_Dwellers.contentEquals("Yes"))
			{
				if((age<35)||(age>=35)&&(age<=65)||(age>65))
				{
					productTypes="Only X";
				}
				
			}
			else if(City_Dwellers.contentEquals("No"))
				{
				if((age<35)&&(age>65))
				{
					productTypes="Only X";
				}
				if((age>=35)&&(age<=65))
				{
					productTypes="W and Z";
				}
				}
		 }
return productTypes;
}


}

