package Codes;

public class Quadric_Equation {
	int root;
	
	public String calculateroot(int a, int b, int c)
	{
		root=(b*b)-4*a*c;
		if((a<0)||(b<0)||(c<0)||(a>100)||(b>100)||(c>100))
			return "Invalid input";
		else if(a==0)
			return "Not a quadratic equation";
		else if (root==0)
			return "Roots are equal";
		else if(root<0)
			return "Imaginary roots";
		else
			return "Real Roots";
		
	}

}
