package ir.maktab.shop.repository.product;

import ir.maktab.shop.entity.Product;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductModel extends AbstractTableModel {

    private List<Product> products;

    public ProductModel(List<Product> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "id";
        } else if (column == 1) {
            return "name";
        } else if (column == 2) {
            return "description";
        } else if (column == 3) {
            return "category";
        } else if (column == 4) {
            return "qty";
        } else if (column == 5) {
            return "price";
        }
        return "";
    }

    @Override
    public Object getValueAt(int row, int column) {
        Product product = products.get(row);
        if (column == 0) {
            return product.getId();
        } else if (column == 1) {
            return product.getName();
        } else if (column == 2) {
            return product.getDescription();
        } else if (column == 3) {
            return product.getCategory().getTitle();
        } else if (column == 4) {
            return product.getQty();
        } else if (column == 5) {
            return product.getPrice();
        }
        return null;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        fireTableDataChanged();
        fireTableDataChanged();
    }
}
