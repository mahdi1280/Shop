package ir.maktab.shop.service.category;

import ir.maktab.shop.entity.Category;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CategoryModel extends AbstractTableModel {

    private List<Category> categories;

    public CategoryModel(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int getRowCount() {
        return categories.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public String getColumnName(int column) {
        if(column==0){
            return "id";
        }else if(column==1){
            return "title";
        }else if(column==2){
            return "description";
        }else{
            return "parent";
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        Category category=categories.get(row);
        if(column==0){
            return category.getId();
        }else if(column==1){
            return category.getTitle();
        }else if(column==2){
            return category.getDescription();
        }else{
            return category.getCategory().getId();
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        fireTableDataChanged();
    }
}
