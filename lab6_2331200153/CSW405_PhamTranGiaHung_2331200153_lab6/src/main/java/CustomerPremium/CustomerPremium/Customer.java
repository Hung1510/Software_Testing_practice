package CustomerPremium.CustomerPremium;

public class Customer {
	String customer;
	double previousBill;
	int NumberofOrders;
	String tag;
	double presentBill;
	
	public Customer(String customer, double prebill, int no, double bill,String tag ) {
        this.customer = customer;
        this.previousBill = prebill;
      this.NumberofOrders=no;
      this.presentBill=bill;
      this.tag=tag;
    }
	public int getNumberofOrders() {
		return NumberofOrders;
	}
	public String getCustomer() {
		return customer;
	}
	public double getPreviousBill() {
		return previousBill;
	}
	public double getPresentsBill() {
		return presentBill;
	}
	public String getTag() {
		return tag;
	}
	public void setPresentBill(double presentBill)
	{
		this.presentBill=presentBill;
		
	}
	public void setTag(String tag)
	{
		this.tag=tag;
		
	}

}
