package ir.maktab.shop.service;

import ir.maktab.shop.repository.BaseRepository;

import java.sql.SQLException;
import java.util.List;

public abstract class ShopService<E,R extends BaseRepository<E>> {

    private R r;

    public ShopService(R r) {
        this.r = r;
    }

    public int save(E e) throws SQLException {
       return r.save(e);
    }

    public void update(E e) throws SQLException {
        r.update(e);
    }

    public void findAll() throws SQLException {
        List<E> all = r.findAll();
        for (E e: all) {
            System.out.println(e.toString());
        }
    }

    public void delete(int id) throws SQLException {
        r.delete(id);
    }

    public E findById(int id) throws SQLException {
        E byId = r.findById(id);
        return byId;
    }
}
