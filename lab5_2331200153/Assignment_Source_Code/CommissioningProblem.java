

public class CommissioningProblem {
	private int totalLocks, totalStocks, totalBarrales;
	private double lockPrice, stockPrice, barralesPrice, lockSales,stockSales,barraelSale, Sales,Commission;
	
	public CommissioningProblem()
	{
		lockPrice=45.0;
		stockPrice=30.0;
		barralesPrice=25.0;
	}
	
	public void setInput(int locks,int stocks,int barrales)
	{
		this.totalLocks=locks;
		this.totalStocks=stocks;
		this.totalBarrales=barrales;
	}
	
	
	public int getTotalLocks() {
		return totalLocks;
	}

	public int getTotalStocks() {
		return totalStocks;
	}

	public int getTotalBarrales() {
		return totalBarrales;
	}

	public double calSale()
	{
		lockSales=lockPrice*totalLocks;
		stockSales=stockPrice*totalStocks;
		barraelSale=barralesPrice*totalBarrales;
		
		Sales=lockSales+stockSales+barraelSale;
		
		
		return Sales;
	}
	
	public double calCommission(double Sales)
	{
		
     boolean c1, c2, c3;
        
        c1 = (1 <= totalLocks) && (totalLocks <= 70);
        c2 = (1 <= totalStocks) && (totalStocks < 80);
        c3 = (1 <= totalBarrales) && (totalBarrales >= 90);
        
        if (!c1 || !c2 || !c3)
        	Commission = 0.0;
        else
        {
		
		if (Sales>1800)
		{
			Commission=0.10*1000.0;
			Commission=Commission+0.15*800;
			Commission=Commission+0.20*(Sales-1800.0);
		}
		else if(Sales > 1000)
			 {
			Commission =0.10*1000;
			Commission=Commission+0.15*(Sales-1000);
			}
		else
			Commission=0.10*Sales;
        }
		return Commission;
	}

}
