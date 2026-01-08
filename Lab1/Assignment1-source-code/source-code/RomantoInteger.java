package basicExample;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RomantoInteger {
	
	private static Map<Character, Integer> map;
	static {
		map=new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
	}
	
	public int convert(String s)
	{
		boolean match=Pattern.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$",s);
		if (match==true)
		{
		int convertedNumber=0;
		for(int i=0;i<s.length();i++)
		   {

			int currentNumber=map.get(s.charAt(i));
			int next=i+1 < s.length() ? map.get(s.charAt(i+1)) :0;
			
			if (currentNumber>=next)
				convertedNumber-=currentNumber;
			else
				convertedNumber+=currentNumber;
			
		}
		return convertedNumber;
	}
		else
			return -1;
	
	}
}
