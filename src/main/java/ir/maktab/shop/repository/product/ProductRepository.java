package ir.maktab.shop.repository.product;

import ir.maktab.shop.config.MyConnection;
import ir.maktab.shop.customeexception.NotFoundException;
import ir.maktab.shop.entity.Category;
import ir.maktab.shop.entity.Product;
import ir.maktab.shop.repository.category.CategoryRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements ProductInterface {

    private final CategoryRepository categoryRepository = new CategoryRepository();

    @Override
    public int save(Product product) throws SQLException {
        String sql = "insert into product(name, description, category_id, qty, price) values \n" +
                "(?,?,?,?,?);";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql
                , Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setInt(3, product.getCategory().getId());
        preparedStatement.setInt(4, product.getQty());
        preparedStatement.setInt(5, product.getPrice());
        preparedStatement.execute();
        ResultSet preparedStatementGeneratedKeys = preparedStatement.getGeneratedKeys();
        preparedStatementGeneratedKeys.next();
        return preparedStatementGeneratedKeys.getInt(1);
    }

    @Override
    public void update(Product product) throws SQLException {
        String sql = "update product set qty = ? where id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql
                , Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, product.getQty());
        preparedStatement.setInt(2, product.getId());
        preparedStatement.execute();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql = "select * from product";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(createProduct(resultSet));
        }
        return products;
    }


    @Override
    public void delete(int id) {

    }

    @Override
    public Product findById(int id) throws SQLException {
        String sql = "select * from product where id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return createProduct(resultSet);
        throw new NotFoundException("product not found Exception");
    }

    @Override
    public List<Product> findByCategory(int categoryId) throws SQLException {
        String sql = "select * from product where category_id=?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, categoryId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(createProduct(resultSet));
        }
        return products;
    }

    @Override
    public List<Product> findByName(String text) throws SQLException {
        String sql = "select * from product where name like ?";
        PreparedStatement preparedStatement = MyConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, "%" + text + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(createProduct(resultSet));
        }
        return products;
    }


    private Product createProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getInt("id")
                , resultSet.getString("name")
                , resultSet.getString("description")
                , categoryRepository.findById(resultSet.getInt("category_id"))
                , resultSet.getInt("qty")
                , resultSet.getInt("price")
        );
    }
}
