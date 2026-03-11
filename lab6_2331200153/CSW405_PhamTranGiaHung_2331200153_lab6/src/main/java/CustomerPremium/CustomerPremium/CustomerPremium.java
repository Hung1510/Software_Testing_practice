package CustomerPremium.CustomerPremium;

import java.util.ArrayList;
import java.util.List;

public class CustomerPremium {
    List<Customer> allCustomers = new ArrayList<>();
    List<Customer> filtered = new ArrayList<>();

    public void PremiumCal(List<Customer> all) {

        this.allCustomers = all;
        for (Customer c : allCustomers) {
            if ((c.getPreviousBill() > 3000.0) && (c.getNumberofOrders() >= 5)) {
//                c.setPresentBill(c.getPresentsBill() - 0.20); --discount 20%, so must be * instead of -
                c.setPresentBill(c.getPresentsBill() - c.getPresentsBill()*0.20);
                c.setTag("Premium Customer");
            }
        }


    }

}
