package Codes;

public class Intersection {
	float intersection_X,intersection_Y;
	
	public String findIntersection(float m1,float m2,float c1,float c2)
	{
		if( (m1 - m2) == 0)

			return "These are parallel lines. No Intersection between the lines.";
		else
			if((c1-c2==0))
				return "These are coincidental lines.No Intersection between the lines\n";

	    else

	    {

	        intersection_X = (c2 - c1) / (m1 - m2);

	        intersection_Y = m1 * intersection_X + c1;

	        return "Intersecting Point: "+intersection_X+intersection_Y;

	    }

	}
	

}
