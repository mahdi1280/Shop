package ir.maktab.shop.frame;

import ir.maktab.shop.repository.product.ProductModel;
import ir.maktab.shop.repository.product.ProductRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProductFrame extends JFrame implements ActionListener {

    private final ProductRepository productRepository = new ProductRepository();
    private final JTable table;
    private final JScrollPane scrollPane;
    private final ProductModel productModel;
    private final Panel panel = new Panel();
    private final JButton button = new JButton("create product");


    private ProductFrame() throws SQLException {
        this.setTitle("Product");
        productModel = new ProductModel(productRepository.findAll());
        table = new JTable(productModel);
        scrollPane = new JScrollPane(table);
        panel.add(button);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(panel,BorderLayout.SOUTH);
        button.addActionListener(this);
        this.pack();
        this.setVisible(true);
    }

    public static ProductFrame createObject() throws SQLException {
        return new ProductFrame();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            ProductCreateDialog.createObject();
            productModel.setProducts(productRepository.findByName("asd"));
            productModel.setProducts(productRepository.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
