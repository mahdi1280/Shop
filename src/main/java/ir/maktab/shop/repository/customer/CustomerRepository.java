package ir.maktab.shop.repository.customer;

import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.ShoppingCard;

import java.util.List;

public class CustomerRepository implements CustomerInterface {

    @Override
    public int save(Customer customer) {
        return 0;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public List<ShoppingCard> findShoppingCardByUserId(int id) {
        return null;
    }

    @Override
    public Customer login(String username, String password) {
        return null;
    }
}
