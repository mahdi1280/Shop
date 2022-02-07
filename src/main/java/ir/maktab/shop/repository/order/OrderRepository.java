package ir.maktab.shop.repository.order;

import ir.maktab.shop.config.MyConnection;
import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.Order;
import ir.maktab.shop.entity.Product;
import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.BaseRepository;
import ir.maktab.shop.repository.product.ProductRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements OrderRepositoryInterFace {
    private final ProductRepository productRepository=new ProductRepository();
    @Override
    public int save(Order order) throws SQLException {
       String sql="insert into orders(product_id, customer_id, shopping_card_id,qty) values (?,?,?,?);";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,order.getProduct().getId());
        preparedStatement.setInt(2,order.getCustomer().getId());
        preparedStatement.setInt(3,order.getShoppingCard().getId());
        preparedStatement.setInt(4,order.getQty());
        preparedStatement.execute();
        return 0;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public List<Order> findAllByShoppingCard(int id) throws SQLException {
        String sql="select * from orders where shopping_card_id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Order> orders=new ArrayList<>();
        while (resultSet.next()){
            orders.add(createOrder(resultSet));
        }
        return orders;
    }

    @Override
    public Double getAllAmount(int id){
        double d=0;
        String sql="select * from orders where shopping_card_id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                d+= (productRepository.findById(resultSet.getInt("product_id")).getPrice() * resultSet.getInt("qty"));
            }
            return d;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Order createOrder(ResultSet resultSet) throws SQLException {
        return new Order(
                resultSet.getInt("id")
                , resultSet.getInt("qty")
                ,productRepository.findById(resultSet.getInt("product_id"))
                ,new Customer(resultSet.getInt("customer_id"))
                ,new ShoppingCard(resultSet.getInt("shopping_card_id")));
    }

}
