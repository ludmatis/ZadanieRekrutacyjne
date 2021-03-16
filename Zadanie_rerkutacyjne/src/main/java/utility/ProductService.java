package utility;


import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ProductService {

    private Integer discount;
    private List<Product> products;
    private static final Integer DEFAULT_DISCOUNT = 1000;

    public ProductService() {
        products = new ArrayList<>();
    }

    public ProductService(Integer discount) {
        this.discount = discount;
        products = new ArrayList<>();
    }

    public void countProductsDiscount() {
        Integer price_sum = getPriceSum();
        AtomicReference<Integer> discount_rest = new AtomicReference<>(getDiscount());
        products.forEach(product -> {
            int single_discount = countSingleDiscount(product.getPrice(), price_sum, discount).intValue();
            product.setProduct_discount(single_discount);
            discount_rest.updateAndGet(v -> v - single_discount);
        });
        if (discount_rest.get() > 0) {
            int last_product_discount = products.get(products.size() - 1).getProduct_discount();
            products.get(products.size() - 1)
                    .setProduct_discount(last_product_discount + discount_rest.get());
        }
    }

    private Double countSingleDiscount(Integer product_price, Integer prices_sum, Integer discount) {
        return (product_price.doubleValue() / prices_sum.doubleValue()) * discount.doubleValue();
    }

    public void generateAutomaticallyProducts() {
        products.add(new Product("Product1", 500));
        products.add(new Product("Product2", 1100));
        products.add(new Product("Product3", 320));
        products.add(new Product("Product4", 250));
        products.add(new Product("Product5", 890));
        this.setDiscount(DEFAULT_DISCOUNT);
    }

    public void printProducts() {
        products.forEach(System.out::println);
        System.out.println("General discount: " + discount);
    }

    public void printProductsWithDiscounts() {
        products.forEach(product -> System.out.println("Discount for " + product.getName() + " = " + product.getProduct_discount() + "zł"));
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteData() {
        products.clear();
    }

    public Integer getPriceSum() {
        return products.stream().mapToInt(Product::getPrice).sum();
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
