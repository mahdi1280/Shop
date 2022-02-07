package ir.maktab.shop.frame;

import ir.maktab.shop.customeexception.InputEmptyException;
import ir.maktab.shop.entity.Category;
import ir.maktab.shop.entity.Product;
import ir.maktab.shop.repository.category.CategoryRepository;
import ir.maktab.shop.repository.product.ProductRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ProductCreateDialog extends JDialog implements ActionListener {

    private final CategoryRepository categoryRepository = new CategoryRepository();

    private final ProductRepository productRepository = new ProductRepository();
    private final JTextField nameTxt = new JTextField();
    private final JTextField descriptionTxt = new JTextField();
    private final JTextField qtyTxt = new JTextField();
    private final JTextField priceTxt = new JTextField();
    private final JComboBox<String> category;

    private final JLabel nameLbl = new JLabel("name: ");
    private final JLabel descriptionLbl = new JLabel("description: ");
    private final JLabel qtyLbl = new JLabel("qty: ");
    private final JLabel priceLbl = new JLabel("price: ");
    private final JLabel categoryLbl = new JLabel("category");

    private final JButton create = new JButton("Save");
    private final List<Category> categoryList;

    private ProductCreateDialog() throws SQLException {
        this.setTitle("Create Product");
        categoryList = categoryRepository.findAll();
        category = new JComboBox<>(getTitle(categoryList));
        this.setLayout(new GridLayout(6, 2, 5, 5));
        this.add(nameLbl);
        this.add(nameTxt);
        this.add(descriptionLbl);
        this.add(descriptionTxt);
        this.add(qtyLbl);
        this.add(qtyTxt);
        this.add(priceLbl);
        this.add(priceTxt);
        this.add(categoryLbl);
        this.add(category);
        this.add(create);
        create.addActionListener(this);
        this.pack();
        this.setModal(true);
        this.setVisible(true);
    }

    private String[] getTitle(List<Category> categories) {
        String[] title = new String[categories.size()];
        for (int i = 0; i < title.length; i++) {
            title[i] = categories.get(i).getTitle();
        }
        return title;
    }

    public static ProductCreateDialog createObject() throws SQLException {
        return new ProductCreateDialog();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            checkField();
        } catch (InputEmptyException i) {
            JOptionPane.showMessageDialog(this, i.getMessage());
            return;
        }
        try {
            productRepository.save(new Product(
                    0,
                    nameTxt.getText()
                    , descriptionTxt.getText()
                    , categoryList.get(category.getSelectedIndex())
                    , Integer.parseInt(qtyTxt.getText())
                    , Integer.parseInt(priceTxt.getText())
            ));
            JOptionPane.showMessageDialog(this, "save successfuly");
            this.setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkField() {
        if (nameTxt.getText().length() == 0) {
            throw new InputEmptyException("name is empty");
        } else if (descriptionTxt.getText().length() == 0) {
            throw new InputEmptyException("description is empty");
        } else if (qtyTxt.getText().length() == 0) {
            throw new InputEmptyException("qty is empty");
        } else if (priceTxt.getText().length() == 0) {
            throw new InputEmptyException("price is empty");
        }
    }
}
