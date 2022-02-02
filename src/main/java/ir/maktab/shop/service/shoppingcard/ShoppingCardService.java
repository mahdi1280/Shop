package ir.maktab.shop.service.shoppingcard;

import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.shoppingcard.ShoppingCardRepository;
import ir.maktab.shop.service.ShopService;

public class ShoppingCardService extends ShopService<ShoppingCard, ShoppingCardRepository> {

    public ShoppingCardService() {
        super(new ShoppingCardRepository());
    }
}
