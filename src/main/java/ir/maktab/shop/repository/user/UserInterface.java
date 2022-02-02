package ir.maktab.shop.repository.user;

import ir.maktab.shop.repository.BaseRepository;

public interface UserInterface<T> extends BaseRepository<T> {

    T login(String username,String password);
}
