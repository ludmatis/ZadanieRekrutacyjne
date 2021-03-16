package model;

public class Product {

    private String name;
    private Integer price;
    private Integer product_discount;


    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ": " +
                " price = " + price +
                " discount = " + product_discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getProduct_discount() {
        return product_discount;
    }

    public void setProduct_discount(Integer product_discount) {
        this.product_discount = product_discount;
    }
}
