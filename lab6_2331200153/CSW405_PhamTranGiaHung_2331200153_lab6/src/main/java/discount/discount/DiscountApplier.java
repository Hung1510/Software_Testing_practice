package discount.discount;

public class DiscountApplier {

    private ProductDao dao;

    public DiscountApplier (ProductDao dao) {
        this.dao = dao;
    }

    public void setNewPrices() {

        for(Product product : dao.all()) {
            if(product.getCategory().equals("BUSINESS")) {
                //product.setPrice(product.getPrice() * 0.9); -> wrong business logic,must be 110%
                product.setPrice(product.getPrice() * 1.1);
            }
            if(product.getCategory().equals("HOME")) {
                //product.setPrice(product.getPrice() * 1.1); -> wrong home logic,must be 90%
                product.setPrice(product.getPrice() * 0.9);
            }
        }

    }
}
