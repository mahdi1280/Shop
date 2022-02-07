package ir.maktab.shop.frame;

import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.repository.product.ProductModel;
import ir.maktab.shop.service.product.ProductService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CustomerFrame extends JFrame implements ActionListener {

    private final Customer customer;
    private final ProductService productService = new ProductService();
    private final JButton buyProductBtn = new JButton("buy product");
    private final JTextField search = new JTextField();
    private ProductModel productModel;
    private JTable table;
    private final JButton orderBtn = new JButton("my order");
    private final JLabel searchLbl = new JLabel("search by name: ");

    private CustomerFrame(Customer customer) throws SQLException {
        this.setTitle(customer.getUsername());
        productModel = new ProductModel(productService.findAllProduct());
        table= new JTable(productModel);
        this.customer = customer;
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panelBTn = new JPanel();
        panelBTn.setLayout(new GridLayout(2, 2, 5, 5));
        panelBTn.add(searchLbl);
        panelBTn.add(search);
        panelBTn.add(buyProductBtn);
        panelBTn.add(orderBtn);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panelBTn, BorderLayout.SOUTH);
        this.pack();
        orderBtn.addActionListener(this);
        buyProductBtn.addActionListener(this);
        search.addActionListener(this);
        this.setVisible(true);
    }

    public static CustomerFrame createObject(Customer customer) {
        try {
            return new CustomerFrame(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == search) {
            try {
                productModel.setProducts(productService.findByName(search.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(actionEvent.getSource() == buyProductBtn){
            if(table.getSelectedRow()<0)
                JOptionPane.showMessageDialog(this,"not selected product");
            else{
                FrameDialogBuyProduct.createObject(productModel.getProducts().get(table.getSelectedRow()),customer );
            }
        }
        if(orderBtn == actionEvent.getSource()){
            try {
                FrameDialogShoppingCart.createObject(customer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
