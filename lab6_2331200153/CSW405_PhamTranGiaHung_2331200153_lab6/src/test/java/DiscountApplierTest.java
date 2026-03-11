import static org.mockito.Mockito.*;

import discount.discount.DiscountApplier;
import discount.discount.Product;
import discount.discount.ProductDao;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DiscountApplierTest {

    ProductDao dao;
    DiscountApplier applier;

    @Before
    public void setUp() {
        dao = mock(ProductDao.class);
        applier = new DiscountApplier(dao);
    }

    //home category: 90% of original price

    @Test
    public void testHomeCategoryGets10PercentDiscount() {
        List<Product> products = List.of(new Product("TV", 100, "HOME"));
        when(dao.all()).thenReturn(products);
        applier.setNewPrices();
        assertEquals(90, products.get(0).getPrice(), 0.01);
    }

    @Test
    public void testHomeCategoryWithDifferentPrice() {
        List<Product> products = List.of(new Product("Sofa", 200, "HOME"));
        when(dao.all()).thenReturn(products);
        applier.setNewPrices();
        assertEquals(180, products.get(0).getPrice(), 0.01);
    }

    //business category: 110% of original price

    @Test
    public void testBusinessCategoryGets10PercentIncrease() {
        List<Product> products = List.of(new Product("Laptop", 100, "BUSINESS"));
        when(dao.all()).thenReturn(products);
        applier.setNewPrices();
        assertEquals(110, products.get(0).getPrice(), 0.01);
    }

    @Test
    public void testBusinessCategoryWithDifferentPrice() {
        List<Product> products = List.of(new Product("Printer", 500, "BUSINESS"));
        when(dao.all()).thenReturn(products);
        applier.setNewPrices();
        assertEquals(550, products.get(0).getPrice(), 0.01);
    }

    //Other categories: price unchanged

    @Test
    public void testOtherCategoryPriceUnchanged() {
        List<Product> products = List.of(new Product("Book", 50, "EDUCATION"));
        when(dao.all()).thenReturn(products);
        applier.setNewPrices();
        assertEquals(50, products.get(0).getPrice(), 0.01);
    }

    @Test
    public void testUnknownCategoryPriceUnchanged() {
        List<Product> products = List.of(new Product("Widget", 75, "OTHER"));
        when(dao.all()).thenReturn(products);
        applier.setNewPrices();
        assertEquals(75, products.get(0).getPrice(), 0.01);
    }

    //Mixed list

    @Test
    public void testMixedListEachCategoryCorrectlyPriced() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("TV",     100, "HOME"));
        products.add(new Product("Laptop", 100, "BUSINESS"));
        products.add(new Product("Book",    50, "EDUCATION"));
        when(dao.all()).thenReturn(products);

        applier.setNewPrices();

        assertEquals(90,  products.get(0).getPrice(), 0.01); // HOME     → 90%
        assertEquals(110, products.get(1).getPrice(), 0.01); // BUSINESS → 110%
        assertEquals(50,  products.get(2).getPrice(), 0.01); // other    → unchanged
    }

    //Edge cases

    @Test
    public void testEmptyListNoException() {
        when(dao.all()).thenReturn(new ArrayList<>());
        applier.setNewPrices(); // not throw
    }

    @Test
    public void testZeroPriceHomeStaysZero() {
        List<Product> products = List.of(new Product("FreeItem", 0, "HOME"));
        when(dao.all()).thenReturn(products);
        applier.setNewPrices();
        assertEquals(0, products.get(0).getPrice(), 0.01);
    }

    @Test
    public void testZeroPriceBusinessStaysZero() {
        List<Product> products = List.of(new Product("FreeItem", 0, "BUSINESS"));
        when(dao.all()).thenReturn(products);
        applier.setNewPrices();
        assertEquals(0, products.get(0).getPrice(), 0.01);
    }

    //Verify dao interaction

    @Test
    public void testDaoIsCalledDuringSetNewPrices() {
        when(dao.all()).thenReturn(new ArrayList<>());
        applier.setNewPrices();
        verify(dao, atLeastOnce()).all();
    }
}