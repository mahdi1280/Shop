package ir.maktab.shop.service.order;

import ir.maktab.shop.entity.Order;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class OrderModel extends AbstractTableModel {

    private List<Order> orders;

    public OrderModel(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int column) {
        if (column == 0) {
            return "id";
        } else if (column == 1) {
            return "product id";
        } else if(column==2){
            return "qty";
        }else if(column==3){
            return "price";
        }else{
            return "price all";
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        Order order = orders.get(row);
        if (column == 0) {
            return order.getId();
        } else if (column == 1) {
            return order.getProduct().getName();
        } else if(column==2){
            return order.getQty();
        }else if(column==3){
            return order.getProduct().getPrice();
        }else {
            return order.getProduct().getPrice() * order.getQty();
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
