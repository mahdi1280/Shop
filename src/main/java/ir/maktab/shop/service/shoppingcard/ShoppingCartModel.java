package ir.maktab.shop.service.shoppingcard;

import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.order.OrderRepository;
import ir.maktab.shop.repository.shoppingcard.ShoppingCardRepository;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ShoppingCartModel extends AbstractTableModel {

    private List<ShoppingCard> shoppingCards;
    private OrderRepository orderRepository=new OrderRepository();
    public ShoppingCartModel(List<ShoppingCard> shoppingCards) {
        this.shoppingCards = shoppingCards;
    }

    @Override
    public int getRowCount() {
        return shoppingCards.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int column) {
        if(column==0){
            return "id";
        }else if(column==1){
            return "customer";
        }else if(column==2){
            return "date";
        }else if(column == 3){
            return "payed";
        }else
            return "price";
    }

    @Override
    public Object getValueAt(int row, int column) {
        ShoppingCard shoppingCard=shoppingCards.get(row);
        if(column==0){
           return shoppingCard.getId();
        }else if(column==1){
            return shoppingCard.getCustomer().getUsername();
        }else if(column==2){
            return shoppingCard.getDate();
        }else if(column==3){
            return shoppingCard.isPayed();
        }else{
          return orderRepository.getAllAmount(shoppingCard.getId());
        }
    }

    public List<ShoppingCard> getShoppingCards() {
        return shoppingCards;
    }

    public void setShoppingCards(List<ShoppingCard> shoppingCards) {
        this.shoppingCards = shoppingCards;
        fireTableDataChanged();
    }
}
