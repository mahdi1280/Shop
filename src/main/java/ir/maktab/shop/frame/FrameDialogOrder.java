package ir.maktab.shop.frame;

import com.sun.org.apache.xpath.internal.operations.Or;
import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.service.order.OrderModel;
import ir.maktab.shop.service.order.OrderService;

import javax.swing.*;
import java.sql.SQLException;

public class FrameDialogOrder extends JDialog {

    private final ShoppingCard shoppingCard;
    private final JTable table;
    private final JScrollPane scrollPane;
    private final OrderModel orderModel;
    private final OrderService orderService=new OrderService();
    private FrameDialogOrder(ShoppingCard shoppingCard) throws SQLException {
        this.setTitle("Order");
        this.shoppingCard = shoppingCard;
        orderModel=new OrderModel(orderService.getByShoppingCart(shoppingCard.getId()));
        table = new JTable(orderModel);
        scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.pack();
        this.setModal(true);
        setVisible(true);
    }

    public static FrameDialogOrder createObject(ShoppingCard shoppingCard) throws SQLException {
        return new FrameDialogOrder(shoppingCard);
    }
}
