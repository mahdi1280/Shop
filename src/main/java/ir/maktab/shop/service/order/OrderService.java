package ir.maktab.shop.service.order;

import ir.maktab.shop.entity.Order;
import ir.maktab.shop.repository.order.OrderRepository;
import ir.maktab.shop.service.ShopService;

import java.sql.SQLException;
import java.util.List;

public class OrderService extends OrderServiceAbstarct {

    private final OrderRepository orderRepository=new OrderRepository();
    public OrderService() {
        super(new OrderRepository());
    }

    @Override
    public void findByShoppingCart(int id) throws SQLException {
        List<Order> allByShoppingCard = orderRepository.findAllByShoppingCard(id);
        for (Order or :allByShoppingCard) {
            System.out.println(or.toString());
        }
    }
}
