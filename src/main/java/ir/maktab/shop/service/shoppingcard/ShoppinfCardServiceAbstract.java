package ir.maktab.shop.service.shoppingcard;

import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.shoppingcard.ShoppingCardRepository;
import ir.maktab.shop.service.ShopService;

import java.sql.SQLException;
import java.util.Currency;
import java.util.List;

public abstract class ShoppinfCardServiceAbstract extends ShopService<ShoppingCard, ShoppingCardRepository> {

    public ShoppinfCardServiceAbstract(ShoppingCardRepository shoppingCardRepository) {
        super(shoppingCardRepository);
    }

   abstract ShoppingCard lastOneShippingCardByUserId(int id) throws SQLException;

    abstract List<ShoppingCard> findByCustomerId(Customer customer) throws SQLException;
}
