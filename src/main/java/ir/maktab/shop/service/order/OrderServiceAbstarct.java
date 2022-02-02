package ir.maktab.shop.service.order;

import ir.maktab.shop.entity.Order;
import ir.maktab.shop.repository.order.OrderRepository;
import ir.maktab.shop.service.ShopService;

import java.sql.SQLException;
import java.util.List;

public abstract class OrderServiceAbstarct extends ShopService<Order, OrderRepository> {

    public OrderServiceAbstarct(OrderRepository orderRepository) {
        super(orderRepository);
    }

    public abstract void findByShoppingCart(int id) throws SQLException;
}
