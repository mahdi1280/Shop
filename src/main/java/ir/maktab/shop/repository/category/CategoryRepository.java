package ir.maktab.shop.repository.category;

import ir.maktab.shop.entity.Category;
import ir.maktab.shop.repository.BaseRepository;

import java.util.List;

public class CategoryRepository implements BaseRepository<Category> {

    @Override
    public int save(Category category) {
        System.out.println("category save");
        return 0;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Category findById(int id) {
        return null;
    }
}
