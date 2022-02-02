package ir.maktab.shop.service.customer;

import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.customer.CustomerRepository;
import ir.maktab.shop.service.UserService;

import java.util.List;

public class CustomerService extends UserService<Customer, CustomerRepository> {

    private CustomerRepository customerRepository;

    public CustomerService() {
        super(new CustomerRepository());
        this.customerRepository = new CustomerRepository();
    }

    public void findShoppingCardByUserId(int id) {
        List<ShoppingCard> shoppingCardByUserId = customerRepository.findShoppingCardByUserId(id);
        for (ShoppingCard shoppingCard : shoppingCardByUserId) {
            System.out.println(shoppingCard.toString());
        }
    }
}
