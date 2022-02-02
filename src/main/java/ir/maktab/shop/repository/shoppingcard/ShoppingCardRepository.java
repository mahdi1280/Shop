package ir.maktab.shop.repository.shoppingcard;

import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.BaseRepository;

import java.util.List;

public class ShoppingCardRepository implements BaseRepository<ShoppingCard> {

    @Override
    public int save(ShoppingCard shoppingCard) {
        System.out.println("shopping card save");
        return 0;
    }

    @Override
    public void update(ShoppingCard shoppingCard) {

    }

    @Override
    public List<ShoppingCard> findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ShoppingCard findById(int id) {
        return null;
    }

}
