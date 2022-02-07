package ir.maktab.shop.frame;

import ir.maktab.shop.customeexception.InputEmptyException;
import ir.maktab.shop.entity.Category;
import ir.maktab.shop.repository.category.CategoryRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryAddDialog extends JDialog implements ActionListener {


    private final CategoryRepository categoryRepository=new CategoryRepository();
    private final JTextField titleBtn = new JTextField();
    private final JTextField descriptionBtn = new JTextField();
    private final JComboBox<String> comboBoxBtn;
    private List<Category> categories=new ArrayList<>();
    private final JLabel titleLbl = new JLabel("title: ");
    private final JLabel descriptionLbl = new JLabel("description: ");
    private final JLabel parent = new JLabel("parent");
    private final JButton save = new JButton("save");

    private CategoryAddDialog() throws SQLException {
        categories=categoryRepository.findAll();
        comboBoxBtn = new JComboBox<>(getTitle(categories));
        comboBoxBtn.setSelectedItem("");
        this.setLayout(new GridLayout(4,2,5,5));
        this.add(titleLbl);
        this.add(titleBtn);
        this.add(descriptionLbl);
        this.add(descriptionBtn);
        this.add(parent);
        this.add(comboBoxBtn);
        this.add(save);
        save.addActionListener(this);
        this.pack();
        this.setModal(true);
        this.setVisible(true);
    }

    private String[] getTitle(List<Category> categories){
        String[] title = new String[categories.size()+1];
        title[categories.size()] = "";
        for(int i=0;i<title.length-1;i++){
            title[i] = categories.get(i).getTitle();
        }
        return title;
    }

    public static CategoryAddDialog createObject() throws SQLException {
        return new CategoryAddDialog();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            checkInput();
        }catch (InputEmptyException e){
            JOptionPane.showMessageDialog(this,e.getMessage());
            return;
        }
        try {
            categoryRepository.save(new Category(0
                    ,titleBtn.getText()
                    ,descriptionBtn.getText()
                    ,comboBoxBtn.getSelectedItem() != "" ?
                    new Category(categories.get(comboBoxBtn.getSelectedIndex()).getId()) : null));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setVisible(false);
    }

    private void checkInput() {
        if(titleBtn.getText().length()==0){
            throw new InputEmptyException("title is empty.");
        }if(descriptionBtn.getText().length()==0){
            throw new InputEmptyException("description is empty.");
        }
    }
}
