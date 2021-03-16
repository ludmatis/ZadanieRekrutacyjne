import model.Product;
import org.junit.Before;
import org.junit.Test;
import utility.ProductService;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ProductServiceTests {

    private ProductService productService;

    @Before
    public void setUp(){
        productService = new ProductService(100);
        productService.addProduct(new Product("Product1", 500));
        productService.addProduct(new Product("Product2", 1500));
    }
    @Test
    public void testCountDiscount(){
        productService.countProductsDiscount();
        assertEquals(25, (long) (productService.getProducts().get(0).getProduct_discount()));
        assertEquals(75, (long) (productService.getProducts().get(1).getProduct_discount()));
    }
    @Test
    public void testPriceSum(){
        assertEquals(2000,(long) productService.getPriceSum());
    }


}
