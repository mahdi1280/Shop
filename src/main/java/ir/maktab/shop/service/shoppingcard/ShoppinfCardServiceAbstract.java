package ir.maktab.shop.service.shoppingcard;

import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.shoppingcard.ShoppingCardRepository;
import ir.maktab.shop.service.ShopService;

import java.sql.SQLException;

public abstract class ShoppinfCardServiceAbstract extends ShopService<ShoppingCard, ShoppingCardRepository> {

    public ShoppinfCardServiceAbstract(ShoppingCardRepository shoppingCardRepository) {
        super(shoppingCardRepository);
    }

   abstract ShoppingCard lastOneShippingCardByUserId(int id) throws SQLException;
}
