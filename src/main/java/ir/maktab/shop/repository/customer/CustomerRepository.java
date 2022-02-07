package ir.maktab.shop.repository.customer;

import ir.maktab.shop.config.MyConnection;
import ir.maktab.shop.customeexception.NotFoundException;
import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.Order;
import ir.maktab.shop.entity.Product;
import ir.maktab.shop.entity.ShoppingCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements CustomerInterface {

    @Override
    public int save(Customer customer) throws SQLException {
        String sql="insert into customer(username, password, address) values (?,?,?);";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql
        , Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,customer.getUsername());
        preparedStatement.setString(2,customer.getPassword());
        preparedStatement.setString(3,customer.getAddress());
        preparedStatement.execute();
        ResultSet preparedStatementGeneratedKeys = preparedStatement.getGeneratedKeys();
        preparedStatementGeneratedKeys.next();
        return preparedStatementGeneratedKeys.getInt(1);
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Customer findById(int id) throws SQLException {
        String sql="select * from customer where id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
           return createCustomer(resultSet);
        throw new NotFoundException("customer not found");
    }

    @Override
    public List<Order> findShoppingCardByUserId(int id) throws SQLException {
        String sql="select * from orders where customer_id=?;";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Order> orders=new ArrayList<>();
        while (resultSet.next()){
            orders.add(createOrder(resultSet));
        }
        return orders;
    }

    private Order createOrder(ResultSet resultSet) throws SQLException {
        return new Order(
                resultSet.getInt("id")
                ,  resultSet.getInt("qty")
        ,new Product(resultSet.getInt("product_id"))
        ,new Customer(resultSet.getInt("customer_id"))
        ,new ShoppingCard(resultSet.getInt("shopping_card_id")));
    }

    @Override
    public Customer login(String username, String password) throws SQLException {
        String sql="select * from customer where username=? and password=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return createCustomer(resultSet);
        throw new NotFoundException("customer not found");
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getInt("id")
                ,resultSet.getString("username")
                ,resultSet.getString("password")
                ,resultSet.getString("address")
        );
    }
}
