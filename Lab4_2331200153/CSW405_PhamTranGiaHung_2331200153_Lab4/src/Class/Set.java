package Class;

import java.util.*;

/*Set represents sets of integers. The elements of a set are stored in an ArrayList. They are sorted 
and without duplicates to speed up some operations. Two methods might need an explanation:

  public void section(Set s) { ... }
removes from this set any element that is equal to an element in s.

  public boolean containsArithTriple() { ... }
returns true iff there are three elements, x, y and z, in this set such that y - x = z - y.*/


import java.util.ArrayList;
import java.util.List;

public class Set {
	
		  private List<Integer> a; 
		  
		  public Set() {
		    a = new ArrayList<Integer>();
		  }
		  
		  public int[] toArray() {
		    int[] ia = new int[a.size()];
		    for (int i = 0; i < ia.length; i++) {
		      ia[i] = a.get(i);
		    }
		    return ia;
		  }
		  
		  public void insert(int x) {

			  int flag=0;
				
		    	for (int i = 0; i < a.size(); i++) {
					//wrong operator for sort operation, should be < and not >
					// set should be descending order
					//if (a.get(i) > x) {
					if (a.get(i) < x) {
						a.add(i, x);
		    	        flag=1;
		    	        break;
					}
					else {
		    	        if (a.get(i) == x) {
		    	        	flag=1;
		    	          break;
		    	        }
					}
				}
		    	if(flag==0)
		    	    a.add(x);
		  }//this method inserts members in the set in sorted way
		  
		  public boolean member(int x) {
		    for (int i = 0; i < a.size(); i++) {
		      if (a.get(i) < x) {
		        return false;
		      }
			  else {
		        if (a.get(i) == x) {
		          return true;
		        }
		      } 
		    }
		    return false;
		  } 

		  public void section(Set s) {
			  for (int i = 0;  i < a.size();) {
			      if (s.a.contains(a.get(i))) {
			        a.remove(i);
			      } else {
			          i++;
			        } 
			      }
			    }
		  public boolean containsArithTriple() {
		    for (int i = 0; i < a.size(); i++) {
		      for (int j = 0; j < i; j++) {
		        if (member(2 * a.get(i) - a.get(j))) return true;
		      }
		    }
		    return false;
		  }
}
