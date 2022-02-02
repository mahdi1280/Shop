package ir.maktab.shop.service.shoppingcard;

import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.shoppingcard.ShoppingCardRepository;
import ir.maktab.shop.service.ShopService;

import java.sql.SQLException;

public class ShoppingCardService extends ShoppinfCardServiceAbstract {

    private final ShoppingCardRepository shoppingCardRepository;
    public ShoppingCardService() {
        super(new ShoppingCardRepository());
        shoppingCardRepository=new ShoppingCardRepository();
    }

    @Override
    public ShoppingCard lastOneShippingCardByUserId(int id) throws SQLException {
       return shoppingCardRepository.lastOneShippingCardByUserId(id);
    }
}
