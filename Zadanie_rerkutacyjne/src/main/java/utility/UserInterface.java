package utility;


import model.Product;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {

    private ProductService productService = new ProductService();
    private Scanner scanner = new Scanner(System.in);
    private static final Integer PRODUCTS_MAX_AMOUNT = 5;

    public void runUserInterface() {
        String user_input;
        boolean finished = false;
        boolean create_new_list = false;
        while (!finished) {
            userInterfaceInfo();
            user_input = scanner.next();
            switch (user_input) {
                case "1":
                    if (productService.getProducts().size() == PRODUCTS_MAX_AMOUNT) {
                        create_new_list = generateManuallyProductsWarning();
                        if (create_new_list) {
                            productService.deleteData();
                            generateProductsManually();
                        }
                    } else {
                        generateProductsManually();
                    }

                    break;
                case "2":
                    if (productService.getProducts().size() > 0) {
                        create_new_list = generateAutomaticallyProductsWarning();
                        if (create_new_list) {
                            productService.deleteData();
                            productService.generateAutomaticallyProducts();
                        }
                    } else {
                        productService.generateAutomaticallyProducts();
                    }
                    break;
                case "3":
                    if (productService.getProducts().isEmpty()) {
                        System.out.println("Create products before discount");
                    } else if (productService.getDiscount() != null){
                        if(generateDiscountWarning()){
                            generateDiscount();
                            break;
                        }
                    } else {
                        generateDiscount();
                    }
                    break;
                case "4":
                    boolean discount_exists = false;
                    if (productService.getDiscount() != null) {
                        discount_exists = true;
                    } else {
                        System.out.println("No inserted discount");
                        break;
                    }
                    if (!productService.getProducts().isEmpty()) {
                        productService.countProductsDiscount();
                        productService.printProductsWithDiscounts();
                    } else {
                        System.out.println("No products to operate on");
                    }
                    break;
                case "5":
                    if (productService.getProducts().isEmpty()) {
                        System.out.println("No products to print");
                    } else {
                        productService.printProducts();
                    }
                    break;
                case "6":
                    finished = true;
                    break;
                default:
                    break;
            }
            create_new_list = false;
        }

    }


    private void generateProductsManually() {
        String user_input;
        boolean finished = false;
        while (!finished) {
            System.out.println("Create product y/n?");
            user_input = scanner.next();
            switch (user_input) {
                case "y":
                    if (productService.getProducts().size() < PRODUCTS_MAX_AMOUNT) {
                        generateSingleProduct();
                    } else {
                        System.out.println("Already created maximum possible products - 5");
                        finished = true;
                    }
                    break;
                case "n":
                    finished = true;
                    scanner.close();
            }
        }
    }

    private void generateDiscount() {
        boolean validated = false;
        int discount = 0;
        int price_sum = productService.getPriceSum();
        while (!validated) {
            System.out.println("Insert general discount");
            validated = true;
            try {
                discount = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Wrong input format");
                scanner.next();
                validated = false;
            }
            if (discount < 0) {
                System.out.println("Discount cannot be negative");
                validated = false;
            } else if (discount > price_sum) {
                System.out.println("Discount cannot be higher than sum of products' prices");
                validated = false;
            }

        }
        productService.setDiscount(discount);
    }

    private void generateSingleProduct() {
        System.out.println("Insert Product's name: ");
        String product_name = scanner.next();
        Integer product_price = validateIntegerInput();
        productService.addProduct(new Product(product_name, product_price));
    }

    private Integer validateIntegerInput() {
        boolean validated = false;
        int product_price = 0;
        while (!validated) {
            System.out.println("Insert Product's price");
            validated = true;
            try {
                product_price = scanner.nextInt();

            } catch (Exception e) {
                System.out.println("Wrong input format");
                scanner.next();
                validated = false;
            }
            if (product_price < 0) {
                System.out.println("Price cannot be negative");
                validated = false;
            }
        }
        return product_price;
    }

    private boolean generateManuallyProductsWarning() {
        System.out.println("List of products is already full(5), do You want to delete this data and create new list? y/n");
        String user_input = scanner.next();
        return user_input.equals("y");
    }

    private boolean generateAutomaticallyProductsWarning() {
        System.out.println("List of products contains at least 1 element. Do You want to delete this data and create new list from hardcoded data? y/n");
        String user_input = scanner.next();
        return user_input.equals("y");
    }

    private boolean generateDiscountWarning() {
        System.out.println("There is already defined discount, do You want to replace it? y/n");
        String user_input = scanner.next();
        return user_input.equals("y");
    }

    private void userInterfaceInfo() {
        System.out.println("Choose one option" +
                "\n" + "1 - manually create products "
                + "\n" + "2 - load hardcoded products and discount "
                + "\n" + "3 - insert discount"
                + "\n" + "4 - count and print discount for each product"
                + "\n" + "5 - print products and discount"
                + "\n" + "6 - exit program");
    }
}
