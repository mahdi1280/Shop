package ir.maktab.shop.service.category;

import ir.maktab.shop.entity.Category;
import ir.maktab.shop.repository.category.CategoryRepository;
import ir.maktab.shop.service.ShopService;

public class CategoryService extends ShopService<Category, CategoryRepository> {

    public CategoryService() {
        super(new CategoryRepository());
    }

}
