package ir.maktab.shop.repository.product;

import ir.maktab.shop.entity.Product;
import ir.maktab.shop.repository.BaseRepository;

import java.util.List;

public interface ProductInterface extends BaseRepository<Product> {

    List<Product> findByCategory(int categoryId);
}
