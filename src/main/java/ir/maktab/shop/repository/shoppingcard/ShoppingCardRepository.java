package ir.maktab.shop.repository.shoppingcard;

import ir.maktab.shop.config.MyConnection;
import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.ShoppingCard;
import ir.maktab.shop.repository.BaseRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class ShoppingCardRepository implements ShoppingCardRepositoryInterFace {


    @Override
    public int save(ShoppingCard shoppingCard) throws SQLException {
        String sql="insert into shopping_card(date, payed, customer_id) values (?,?,?)";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql
        , Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDate(1,shoppingCard.getDate());
        preparedStatement.setBoolean(2,shoppingCard.isPayed());
        preparedStatement.setInt(3,shoppingCard.getCustomer().getId());
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public void update(ShoppingCard shoppingCard) throws SQLException {

    }

    @Override
    public List<ShoppingCard> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public ShoppingCard findById(int id) throws SQLException {
        String sql="select * from shopping_card where id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return createShoppingCard(resultSet);
    }

    @Override
    public ShoppingCard lastOneShippingCardByUserId(int id) throws SQLException {
       String sql="select * from shopping_card where shopping_card.customer_id = ? and payed = false;";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
           return createShoppingCard(resultSet);
        }
        int save = save(new ShoppingCard(0, Date.valueOf(LocalDate.now()), false, new Customer(id)));
        return findById(save);
    }

    private ShoppingCard createShoppingCard(ResultSet resultSet) throws SQLException {
        return new ShoppingCard(
                resultSet.getInt("id")
                ,resultSet.getDate("date")
                ,resultSet.getBoolean("payed")
                ,new Customer(resultSet.getInt("customer_id"))
        );
    }
}
