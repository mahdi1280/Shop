package ir.maktab.shop.frame;

import ir.maktab.shop.customeexception.InputEmptyException;
import ir.maktab.shop.customeexception.NotFoundException;
import ir.maktab.shop.entity.Admin;
import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.service.admin.AdminService;
import ir.maktab.shop.service.customer.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginFrame extends JFrame implements ActionListener {

    private final JButton login = new JButton("Login");
    private final JButton create = new JButton("Create");
    private final String[] s = {"Customer", "Admin"};
    private final JTextField userNameTxt = new JTextField();
    private final JTextField passwordTxt = new JTextField();
    private final JComboBox<String> comboBox = new JComboBox<>(s);
    private Customer customer;
    private Admin admin;
    private final AdminService adminService = new AdminService();
    private final CustomerService customerService = new CustomerService();
    private final JTextField addressNationalCodeTxt = new JTextField();

    private LoginFrame() {
        this.setTitle("Login");
        setLayout(new GridLayout(5, 2, 5, 5));


        JLabel userNameLbl = new JLabel("User Name: ");
        JLabel passwordLbl = new JLabel("Password: ");
        JLabel addressNationalCode = new JLabel("Address/NationalCode: ");
        JLabel roleLbl = new JLabel("role");


        this.add(userNameLbl);
        this.add(userNameTxt);
        this.add(passwordLbl);
        this.add(passwordTxt);
        this.add(addressNationalCode);
        this.add(addressNationalCodeTxt);
        this.add(roleLbl);
        this.add(comboBox);
        this.add(login);
        this.add(create);
        this.pack();
        login.addActionListener(this);
        create.addActionListener(this);
        setVisible(true);
    }

    public static LoginFrame createObject() {
        return new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == create) {
            try {
                checkInputCreate();
            } catch (InputEmptyException i) {
                JOptionPane.showMessageDialog(this, i.getMessage());
                return;
            }
            if (this.comboBox.getSelectedIndex() == 0) {
                try {
                    saveCustomer();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            } else {
                try {
                    saveAdmin();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }

        } else if (actionEvent.getSource() == login) {
            try {
                checkInputLogin();
            } catch (InputEmptyException i) {
                JOptionPane.showMessageDialog(this, i.getMessage());
                return;
            }
            if (this.comboBox.getSelectedIndex() == 0) {
                loginCustomer();
            } else {
                loginAdmin();
            }
        }
    }

    private void saveAdmin() throws SQLException {
        int save = adminService.save(createAdmin());
        admin = adminService.findById(save);
        AdminFrame.createObject(admin);
        this.setVisible(false);
    }

    private Admin createAdmin() {
        return new Admin(0
                , userNameTxt.getText()
                , passwordTxt.getText()
                , addressNationalCodeTxt.getText());
    }

    private void saveCustomer() throws SQLException {
        int customerId = customerService.save(createCustomer());
        customer = customerService.findById(customerId);
        CustomerFrame.createObject(customer);
        this.setVisible(false);
    }

    private Customer createCustomer() {
        return new Customer(0, userNameTxt.getText()
                , passwordTxt.getText()
                , addressNationalCodeTxt.getText());
    }

    private void checkInputCreate() {
        if (userNameTxt.getText().length() == 0) {
            throw new InputEmptyException("user name input is empty.");
        }
        if (passwordTxt.getText().length() == 0) {
            throw new InputEmptyException("password input in empty");
        }
        if (addressNationalCodeTxt.getText().length() == 0) {
            throw new InputEmptyException("address or national code empty.");
        }
    }

    private void loginAdmin() {
        try {
            admin = adminService.login(userNameTxt.getText(), passwordTxt.getText());
            AdminFrame.createObject(admin);
            this.setVisible(false);
        } catch (NotFoundException | SQLException n) {
            System.out.println(n.getMessage());
        }
    }

    private void loginCustomer() {
        try {
            customer = customerService.login(userNameTxt.getText(), passwordTxt.getText());
            CustomerFrame.createObject(customer);
            this.setVisible(false);
        } catch (NotFoundException | SQLException n) {
            JOptionPane.showMessageDialog(this, n.getMessage());
        }
    }

    private void checkInputLogin() {
        if (userNameTxt.getText().length() == 0) {
            throw new InputEmptyException("user name input is empty.");
        }
        if (passwordTxt.getText().length() == 0) {
            throw new InputEmptyException("password input in empty");
        }
    }
}
