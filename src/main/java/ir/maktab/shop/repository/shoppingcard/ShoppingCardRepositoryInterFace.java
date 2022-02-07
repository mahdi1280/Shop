package ir.maktab.shop.repository.shoppingcard;

import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.BaseRepository;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingCardRepositoryInterFace extends BaseRepository<ShoppingCard> {

    ShoppingCard lastOneShippingCardByUserId(int id) throws SQLException;

    List<ShoppingCard> findAllByCustomerId(int id) throws SQLException;
}
