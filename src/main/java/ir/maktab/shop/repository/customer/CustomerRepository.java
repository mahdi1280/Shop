package ir.maktab.shop.repository.customer;

import ir.maktab.shop.customeexception.NotFoundException;
import ir.maktab.shop.entity.Customer;
import ir.maktab.shop.entity.Order;
import ir.maktab.shop.repository.session.MySession;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class CustomerRepository implements CustomerInterface {

    @Override
    public int save(Customer customer) throws SQLException {

        Session session = MySession.getSession();
        session.beginTransaction();
        Long id=(Long) session.save(customer);
        session.getTransaction().commit();
        return Math.toIntExact(id);
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Customer findById(int id) throws SQLException {

        Session session = MySession.getSession();
        Customer customer = session.find(Customer.class, id);
        if (customer==null)
            throw new NotFoundException("customer not found");
        return customer;
    }

    @Override
    public List<Order> findShoppingCardByUserId(int id) throws SQLException {
        Session session = MySession.getSession();
        return session.createNativeQuery("select * from orders where customer_id=:id;")
                .setParameter("id", id).getResultList();
    }

    @Override
    public Customer login(String username, String password) throws SQLException {

        Session session = MySession.getSession();
        List<Customer> list = session.createQuery("select c from Customer c where c.username=:username and c.password=:password")
                .setParameter("username", username)
                .setParameter("password", password)
                .list();
        if(list==null || list.size()==0)
        throw new NotFoundException("customer not found");
        return list.get(0);
    }
}
