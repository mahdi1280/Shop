package ir.maktab.shop.repository.admin;

import ir.maktab.shop.entity.Admin;
import ir.maktab.shop.repository.user.UserInterface;

import java.util.List;

public class AdminRepository implements UserInterface<Admin> {

    @Override
    public int save(Admin admin) {
        String sql="insert into admin";
        Co
    }

    @Override
    public void update(Admin admin) {

    }

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Admin findById(int id) {
        return null;
    }

    @Override
    public Admin login(String username, String password) {
        return null;
    }
}
