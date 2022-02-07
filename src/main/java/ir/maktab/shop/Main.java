package ir.maktab.shop;

import ir.maktab.shop.customeexception.NotFoundException;
import ir.maktab.shop.entity.*;
import ir.maktab.shop.service.admin.AdminService;
import ir.maktab.shop.service.category.CategoryService;
import ir.maktab.shop.service.customer.CustomerService;
import ir.maktab.shop.service.order.OrderService;
import ir.maktab.shop.service.product.ProductService;
import ir.maktab.shop.service.shoppingcard.ShoppingCardService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final AdminService adminService = new AdminService();
    private static final CustomerService customerService = new CustomerService();
    private static final ProductService productService = new ProductService();
    private static final CategoryService categoryService = new CategoryService();
    private static final Scanner scanner = new Scanner(System.in);
    private static Admin admin;
    private static Customer customer;
    private static final OrderService orderService=new OrderService();
    private static final ShoppingCardService shoppingCartService=new ShoppingCardService();
    public static void main(String[] args) throws SQLException {

        showMenuLogin();
        loginMethod();
        if (admin != null) {
            adminMenu();
        }
        if (customer != null) {
            customerMenu();
        }

    }

    private static void customerMenu() throws SQLException {
        boolean state=true;
        showMenuCustomer();
        while (state){
            switch (getNumber()){
                case 1:
                    productService.findAll();
                    Product product=getProduct();
                    orderService.save(createOrder(product));
                    break;
                case 2:
                    categoryService.findAll();
                    Category category=getCategory();
                    productService.findByCategory(category.getId());
                    break;
                case 3:
                    ShoppingCard shoppingCard = shoppingCartService.lastOneShippingCardByUserId(customer.getId());
                    orderService.findByShoppingCart(shoppingCard.getId());
                    break;
                case 4:
                    state = false;
                    break;
                default:
                    System.out.println("wrong!");
                    break;
            }
        }
    }

    private static Product getProduct() {
        System.out.println("please enter product: ");
        while (true) {
            try {
            int productId = getNumber();
               Product product= productService.findById(productId);
               return product;
            }catch (NotFoundException | SQLException n){
                System.out.println(n.getMessage());
            }
        }
    }

    private static Order createOrder(Product product) throws SQLException {
        return new Order(0,1,product,customer,shoppingCartService.lastOneShippingCardByUserId(customer.getId()));
    }

    private static void showMenuCustomer() {
        System.out.println("1.bye product");
        System.out.println("2.search by category");
        System.out.println("3.get orders");
        System.out.println("4.exit");
    }

    private static void adminMenu() throws SQLException {
        showMenuAdmin();
        boolean state = true;
        while (state) {
            switch (getNumber()) {
                case 1:
                    categoryService.findAll();
                    Category category = getCategory();
                    productService.save(createProduct(category));
                    break;
                case 2:
                    categoryService.save(creteCategory());
                    break;
                case 3:
                    state=false;
                    break;
                default:
                    System.out.println("wrong!");
                    break;
            }
        }
    }

    private static Category creteCategory() throws SQLException {
        scanner.nextLine();
        System.out.println("please enter title: ");
        String title=scanner.nextLine();
        System.out.println("please enter description: ");
        String description=scanner.nextLine();
        categoryService.findAll();
        Category category=getCategorys();
        return new Category(0,title,description,category);
    }

    private static Category getCategory() {
        System.out.println("please enter category id: ");
        while (true) {
            try {
                int categoryId = getNumber();
                Category byId = categoryService.findById(categoryId);
                return byId;
            } catch (NotFoundException | SQLException n) {
                System.out.println(n.getMessage());
            }
        }
    }

    private static Category getCategorys() {
        System.out.println("please enter category id: ");
        while (true) {
            try {
                int categoryId = getNumber();
                if(categoryId==0)
                    return null;
                Category byId = categoryService.findById(categoryId);
                return byId;
            } catch (NotFoundException | SQLException n) {
                System.out.println(n.getMessage());
            }
        }
    }

    private static Product createProduct(Category category) {
        scanner.nextLine();
        System.out.println("please enter name: ");
        String name = scanner.nextLine();
        System.out.println("please enter description: ");
        String description = scanner.nextLine();
        System.out.println("please enter qty: ");
        int qty = getNumber();
        System.out.println("please enter price: ");
        int price = getNumber();
        return new Product(name, description, category, qty, price);
    }

    private static void showMenuAdmin() {
        System.out.println("1.create product");
        System.out.println("2.create category");
        System.out.println("3.exit");
    }

    private static void loginMethod() throws SQLException {
        boolean state = true;
        while (state) {
            switch (getNumber()) {
                case 1:
                    scanner.nextLine();
                    System.out.println("please enter the username: ");
                    String username1 = scanner.nextLine();
                    System.out.println("please enter the password: ");
                    String password1 = scanner.nextLine();
                    try {
                        customer = customerService.login(username1, password1);
                        state = false;
                    } catch (NotFoundException | SQLException n) {
                        System.out.println(n.getMessage());
                    }
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("please enter the username: ");
                    String username = scanner.nextLine();
                    System.out.println("please enter the password: ");
                    String password = scanner.nextLine();
                    try {
                        admin = adminService.login(username, password);
                        state = false;
                    } catch (NotFoundException | SQLException n) {
                        System.out.println(n.getMessage());
                    }
                    break;
                case 3:
                    int save = adminService.save(createAdmin());
                    admin = adminService.findById(save);
                    state = false;
                    break;
                case 4:
                    int customerId = customerService.save(createCustomer());
                    customer = customerService.findById(customerId);
                    state = false;
                    break;
                case 5:
                    state = false;
                    break;
                default:
                    System.out.println("wrong!");
                    break;
            }
        }
    }

    private static Admin createAdmin() {
        scanner.nextLine();
        System.out.println("please enter username: ");
        String username = scanner.nextLine();
        System.out.println("please enter password: ");
        String password = scanner.nextLine();
        System.out.println("please enter the national code: ");
        String nationalCode = scanner.nextLine();
        return new Admin(0, username, password, nationalCode);
    }

    private static Customer createCustomer() {
        scanner.nextLine();
        System.out.println("please enter username: ");
        String username = scanner.nextLine();
        System.out.println("please enter password: ");
        String password = scanner.nextLine();
        System.out.println("please enter address");
        String address = scanner.nextLine();
        return new Customer(0, username, password, address);
    }

    private static int getNumber() {
        while (true) {
            try {
                int number = scanner.nextInt();
                return number;
            } catch (Exception e) {
                System.out.println("number not currect");
                scanner.nextLine();
            }
        }
    }

    public static void showMenuLogin() {
        System.out.println("1.login customer");
        System.out.println("2.login admin");
        System.out.println("3.create admin");
        System.out.println("4.create customer");
        System.out.println("5.exit");
    }
}
