package ir.maktab.shop.service.product;

import ir.maktab.shop.entity.Product;
import ir.maktab.shop.repository.product.ProductRepository;
import ir.maktab.shop.service.ShopService;

import java.sql.SQLException;
import java.util.List;

public class ProductService extends ShopService<Product, ProductRepository> {

    private ProductRepository productRepository;

    public ProductService() {
        super(new ProductRepository());
        productRepository=new ProductRepository();
    }

    public void findByCategory(int id) throws SQLException {
            List<Product> byCategory = productRepository.findByCategory(id);
            for (Product product : byCategory) {
                System.out.println(product.toString());
            }
    }
}
