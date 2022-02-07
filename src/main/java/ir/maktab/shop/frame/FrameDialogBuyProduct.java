package ir.maktab.shop.frame;

import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.Order;
import ir.maktab.shop.entity.Product;
import ir.maktab.shop.service.order.OrderService;
import ir.maktab.shop.service.shoppingcard.ShoppingCardService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FrameDialogBuyProduct extends JDialog implements ActionListener {

    private final Product product;
    private final JTextField countTxt=new JTextField();
    private final JLabel countLabel = new JLabel("count: ");
    private final OrderService orderService = new OrderService();
    private final JLabel priceLbl = new JLabel("price: ");
    private final JLabel priceLblProduct = new JLabel("0");
    private final JLabel sumLbl = new JLabel("sum: ");
    private final JLabel sumCountLbl = new JLabel("0");
    private final JButton submit=new JButton("buy");
    private final JButton cancel=new JButton("cancel");
    private final ShoppingCardService shoppingCardService=new ShoppingCardService();
    private final Customer customer;
    private FrameDialogBuyProduct(Product product,Customer customer){
        this.customer = customer;
        this.product=product;
        priceLblProduct.setText(String.valueOf(product.getPrice()));
        this.setLayout(new GridLayout(4,2,5,5));
        this.add(countLabel);
        this.add(countTxt);
        countTxt.addActionListener(this);
        submit.addActionListener(this);
        this.add(priceLbl);
        this.add(priceLblProduct);
        this.add(sumLbl);
        this.add(sumCountLbl);
        this.add(submit);
        this.add(cancel);
        this.setModal(true);
        this.pack();
        cancel.addActionListener(this);
        this.setVisible(true);
    }

    public static FrameDialogBuyProduct createObject(Product product, Customer customer){
        return new FrameDialogBuyProduct(product,customer);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == countTxt){
            try {
                getCount();
            }catch (RuntimeException r){
                JOptionPane.showMessageDialog(this,r.getMessage());
                return;
            }
            sumCountLbl.setText(String.valueOf(product.getPrice() * Integer.parseInt(countTxt.getText())));
        }if(actionEvent.getSource() == cancel){
            this.setVisible(false);
        }if(actionEvent.getSource() == submit){
            try {
                getCount();
            }catch (RuntimeException r){
                JOptionPane.showMessageDialog(this,r.getMessage());
                return;
            }
            try {
                orderService.save(createOrder(product));
                JOptionPane.showMessageDialog(this,"buy is saved.");
                setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Order createOrder(Product product) throws SQLException {
        return new Order(0,Integer.parseInt(countTxt.getText()),product,customer,shoppingCardService.lastOneShippingCardByUserId(customer.getId()));
    }

    private void getCount() {
        try {
            Integer.parseInt(countTxt.getText());
        }catch (Exception e){
            throw new RuntimeException("number not valid!!");
        }
    }
}
