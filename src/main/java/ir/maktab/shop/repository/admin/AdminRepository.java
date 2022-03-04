package ir.maktab.shop.repository.admin;

import ir.maktab.shop.config.MyConnection;
import ir.maktab.shop.customeexception.NotFoundException;
import ir.maktab.shop.entity.Admin;
import ir.maktab.shop.repository.session.MySession;
import ir.maktab.shop.repository.user.UserInterface;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements UserInterface<Admin> {

    @Override
    public int save(Admin admin) throws SQLException {
        Session session = MySession.getSession();
        session.beginTransaction();
        Long id=(Long)session.save(admin);
        session.getTransaction().commit();
        return Math.toIntExact(id);
    }

    @Override
    public void update(Admin admin) throws SQLException {
        Session session = MySession.getSession();
        session.beginTransaction();
        session.update(admin);
        session.getTransaction().commit();
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        Session session = MySession.getSession();
       return session.createQuery("select a from Admin a").getResultList();
    }

    @Override
    public void delete(int id) throws SQLException {
        Session session = MySession.getSession();
        Admin admin = session.find(Admin.class, id);
        session.delete(admin);
    }

    @Override
    public Admin findById(int id) throws SQLException {
        Session session = MySession.getSession();
        Admin admin = session.find(Admin.class, id);
        if(admin==null)
            throw new NotFoundException("not found admin");
        return admin;
    }

    @Override
    public Admin login(String username, String password) throws SQLException {
        Session session = MySession.getSession();
        List<Admin> list = session.createQuery("select a from Admin a where a.password=:password and a.username=:username")
                .setParameter("password", password)
                .setParameter("username", username)
                .list();
        if(list==null || list.size()==0)
            throw new NotFoundException("not found Admin Exception");
       return list.get(0);
    }
}
