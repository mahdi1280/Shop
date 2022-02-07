package ir.maktab.shop.frame;

import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.service.shoppingcard.ShoppingCardService;
import ir.maktab.shop.service.shoppingcard.ShoppingCartModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FrameDialogShoppingCart extends JDialog implements ActionListener {

    private final Customer customer;
    private final ShoppingCardService shoppingCardService = new ShoppingCardService();
    private final ShoppingCartModel shoppingCartModel;
    private final JTable table;
    private final JScrollPane scrollPane;
    private final JPanel panel = new JPanel();
    private final JButton orderBtn = new JButton("show Order");
    private final JButton submitOrder=new JButton("buy complit");
    private FrameDialogShoppingCart(Customer customer) throws SQLException {
        this.setTitle("shopping card");
        this.customer = customer;
        shoppingCartModel = new ShoppingCartModel(shoppingCardService.findByCustomerId(customer));
        table = new JTable(shoppingCartModel);
        scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        panel.add(orderBtn);
        panel.add(submitOrder);
        submitOrder.addActionListener(this);
        this.add(panel, BorderLayout.SOUTH);
        orderBtn.addActionListener(this);
        this.pack();
        this.setModal(true);
        this.setVisible(true);
    }

    public static FrameDialogShoppingCart createObject(Customer customer) throws SQLException {
        return new FrameDialogShoppingCart(customer);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == orderBtn) {
            if (table.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "not selected shopping cart");
                return;
            }
            try {
                FrameDialogOrder.createObject(shoppingCartModel.getShoppingCards().get(table.getSelectedRow()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(actionEvent.getSource() == submitOrder){
            if (table.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "not selected shopping cart");
                return;
            }
            ShoppingCard shoppingCard=shoppingCartModel.getShoppingCards().get(table.getSelectedRow());
            shoppingCard.setPayed(true);
            try {
                shoppingCardService.update(shoppingCard);
                shoppingCartModel.setShoppingCards(shoppingCardService.findByCustomerId(customer));
                JOptionPane.showMessageDialog(this,"shopping cart payed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
