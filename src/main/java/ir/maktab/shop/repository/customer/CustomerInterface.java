package ir.maktab.shop.repository.customer;

import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.user.UserInterface;

import java.util.List;

public interface CustomerInterface extends UserInterface<Customer> {

    List<ShoppingCard> findShoppingCardByUserId(int id);
}
