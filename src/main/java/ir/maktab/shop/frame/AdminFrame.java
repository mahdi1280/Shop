package ir.maktab.shop.frame;

import ir.maktab.shop.entity.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminFrame extends JFrame implements ActionListener {

    private final Admin admin;
    private final JButton productBtn=new JButton("product");
    private final JButton categoryBtn = new JButton("category");
    private AdminFrame(Admin admin) {
        this.add(productBtn, BorderLayout.SOUTH);
        this.add(categoryBtn, BorderLayout.NORTH);
        categoryBtn.addActionListener(this);
        this.admin = admin;
        productBtn.addActionListener(this);
        this.setTitle("admin");
        this.setSize(300,150);
        this.setVisible(true);
    }

    public static AdminFrame createObject(Admin admin){
        return new AdminFrame(admin);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == categoryBtn){
            try {
                CategoryFrame.createObject();
                this.setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if(actionEvent.getSource() ==productBtn){
            try {
                ProductFrame.createObject();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
