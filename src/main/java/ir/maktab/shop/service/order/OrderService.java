package ir.maktab.shop.service.order;

import ir.maktab.shop.entity.Order;
import ir.maktab.shop.repository.order.OrderRepository;
import ir.maktab.shop.service.ShopService;

public class OrderService extends ShopService<Order, OrderRepository> {

    public OrderService() {
        super(new OrderRepository());
    }
}
