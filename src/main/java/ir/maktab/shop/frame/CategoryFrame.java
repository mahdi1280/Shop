package ir.maktab.shop.frame;

import ir.maktab.shop.repository.category.CategoryRepository;
import ir.maktab.shop.service.category.CategoryModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CategoryFrame extends JFrame implements ActionListener {

    private final CategoryRepository categoryRepository = new CategoryRepository();
    private final JTable table;
    private final JScrollPane scrollPane;
    private final CategoryModel categoryModel;
    private final Panel panel=new Panel();
    private final JButton button=new JButton("create category");

    private CategoryFrame() throws SQLException {
        categoryModel = new CategoryModel(categoryRepository.findAll());
        table=new JTable(categoryModel);
        scrollPane=new JScrollPane(table);
        panel.add(button);
        button.addActionListener(this);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(panel,BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    public static CategoryFrame createObject() throws SQLException {
        return new CategoryFrame();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            CategoryAddDialog.createObject();
            categoryModel.setCategories(categoryRepository.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
