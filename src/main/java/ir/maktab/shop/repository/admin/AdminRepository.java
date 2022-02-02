package ir.maktab.shop.repository.admin;

import ir.maktab.shop.config.MyConnection;
import ir.maktab.shop.customeexception.NotFoundException;
import ir.maktab.shop.entity.Admin;
import ir.maktab.shop.repository.user.UserInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements UserInterface<Admin> {

    @Override
    public int save(Admin admin) throws SQLException {
        String sql="insert into admin(username, password, nationalcode) values (?,?,?);";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql
        , Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,admin.getUsername());
        preparedStatement.setString(2,admin.getPassword());
        preparedStatement.setString(3,admin.getNationalCode());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public void update(Admin admin) throws SQLException {
        String sql="update admin set username=? , password=? ,nationalcode=? where id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,admin.getUsername());
        preparedStatement.setString(2,admin.getPassword());
        preparedStatement.setString(3,admin.getNationalCode());
        preparedStatement.setInt(4,admin.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        String sql="select * from admin;";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Admin> admins=new ArrayList<>();
        while (resultSet.next()){
           admins.add(createAdmin(resultSet));
        }
        return admins;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql="delete from admin where id=?;";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public Admin findById(int id) throws SQLException {
        String sql="select * from admin where id=?;";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return createAdmin(resultSet);
        throw new NotFoundException("not found admin");
    }

    @Override
    public Admin login(String username, String password) throws SQLException {
        String sql="select * from admin where username=? and password=?;";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return createAdmin(resultSet);
        throw new NotFoundException("not found Admin Exception");
    }


    private Admin createAdmin(ResultSet resultSet) throws SQLException {
        return new Admin(
                resultSet.getInt("id")
                ,resultSet.getString("username")
                ,resultSet.getString("password")
                ,resultSet.getString("nationalcode")
        );
    }
}
