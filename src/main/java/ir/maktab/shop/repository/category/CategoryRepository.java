package ir.maktab.shop.repository.category;

import ir.maktab.shop.config.MyConnection;
import ir.maktab.shop.customeexception.NotFoundException;
import ir.maktab.shop.entity.Category;
import ir.maktab.shop.repository.BaseRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements BaseRepository<Category> {

    @Override
    public int save(Category category) throws SQLException {
        String sql="insert into category(title, description, category_id) values (?,?,?);";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql
        , Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,category.getTitle());
        preparedStatement.setString(2,category.getDescription());
        preparedStatement.setInt(3,category.getCategory() == null ? 0 : category.getCategory().getId());
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public void update(Category category) throws SQLException {
        String sql="update category set title=?, description=? ,category_id=? where id=?;";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,category.getTitle());
        preparedStatement.setString(2,category.getDescription());
        preparedStatement.setInt(3,category.getCategory() == null ? 0 : category.getCategory().getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public List<Category> findAll() throws SQLException {
        String sql="select * from category";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Category> categories=new ArrayList<>();
        while (resultSet.next()){
            categories.add(createCategory(resultSet));
        }
        return categories;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql="delete from category where id=? ";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public Category findById(int id) throws SQLException {
       String sql="select * from category where id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return createCategory(resultSet);
        throw new NotFoundException("category not found");
    }

    private Category createCategory(ResultSet resultSet) throws SQLException {
        return new Category(
                resultSet.getInt("id")
                ,resultSet.getString("title")
                ,resultSet.getString("description")
                ,new Category(resultSet.getInt("category_id"))
        );
    }
}
