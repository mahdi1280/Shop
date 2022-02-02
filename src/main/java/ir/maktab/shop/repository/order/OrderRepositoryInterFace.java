package ir.maktab.shop.repository.order;

import ir.maktab.shop.entity.Order;
import ir.maktab.shop.repository.BaseRepository;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepositoryInterFace extends BaseRepository<Order> {

    List<Order> findAllByShoppingCard(int id) throws SQLException;
}
