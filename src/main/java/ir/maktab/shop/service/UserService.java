package ir.maktab.shop.service;

import ir.maktab.shop.entity.User;
import ir.maktab.shop.repository.BaseRepository;
import ir.maktab.shop.repository.user.UserInterface;

import java.sql.SQLException;

public abstract class UserService<E extends User,R extends BaseRepository<E>> extends ShopService<E,R>{

    private R r;

    public UserService(R r) {
        super(r);
        this.r=r;
    }

    public E login(String username,String password) throws SQLException {
        UserInterface<E> userInterface = (UserInterface<E>) r;
        return userInterface.login(username, password);
    }
}
