package ir.maktab.shop.repository.customer;

import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.Order;
import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.user.UserInterface;

import java.sql.SQLException;
import java.util.List;

public interface CustomerInterface extends UserInterface<Customer> {

    List<Order> findShoppingCardByUserId(int id) throws SQLException;
}
