# Zadanie Rekrutacyjne
Program for solving problem of porportional division of discount on given products. Console user interface which takes input from keyboard and performs suitable actions.
## Assumptions :
* user can choose to create products manually or automatically (hardcoded objects)
* user can create maximum 5 products
* general discount is a given value that must be divided for each product
* single discount is a part of general discount counted for each product individually
* general discount cannot be bigger than sum of products' prices
## Project :
### model package
* contains model of product
### utility package
* ProductService - class intended to operate on Product objects
* UserInterface - class intended to operate on user input
### Main
* runs the program
### ProductServiceTest
* unit testing of arithmetic functions
* tests performed with junit 4.12 which is included thanks to Gradle dependencies
## Method of use
Run "main" method in Main.class and follow the directions
