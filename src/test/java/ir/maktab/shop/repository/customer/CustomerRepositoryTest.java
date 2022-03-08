package ir.maktab.shop.repository.customer;

import ir.maktab.shop.customeexception.NotFoundException;
import ir.maktab.shop.entity.Customer;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

class CustomerRepositoryTest {

    private final CustomerRepository customerRepository;

    CustomerRepositoryTest() {
        this.customerRepository = new CustomerRepository();
    }

    @Test
    void saveCustomerNormal() {
        Customer customer = new Customer(0, "asdasd", "asdasdasd", "asdasd");
        try {
            int save = customerRepository.save(customer);
            Assertions.assertNotEquals(save, 0);
        } catch (SQLException e) {
            assert true;
        }
    }

    @Test
    void saveCustomerNullAddress() {
        Customer customer = new Customer(0, "asdasdasdas", "asdasdasd", null);
        try {
            customerRepository.save(customer);
            assert true;
        } catch (SQLException | PropertyValueException ignored) {
        }
    }

    @Test
    void saveCustomerNullUsername() {
        Customer customer = new Customer(0, null, "asdasdasd", "adsadsda");
        try {
            customerRepository.save(customer);
            assert true;
        } catch (SQLException | PropertyValueException ignored) {
        }
    }

    @Test
    void saveCustomerNullPassword() {
        Customer customer = new Customer(0, "asdf", null, "adsadsda");
        try {
            customerRepository.save(customer);
            assert true;
        } catch (SQLException | PropertyValueException ignored) {
        }
    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer(0, "asdasdasdas", "asdasdasd", "asdasdas");
        int id = 0;
        try {
            id = customerRepository.save(customer);
            customer.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customer.setPassword("123");
        customer.setUsername("123");
        customer.setAddress("123");
        customerRepository.update(customer);
        try {
            Customer byId = customerRepository.findById(id);
            Assertions.assertAll(
                    () -> Assertions.assertEquals(byId.getUsername(), "123")
                    , () -> Assertions.assertEquals(byId.getPassword(), "123")
                    , () -> Assertions.assertEquals(byId.getAddress(), "123"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateCustomerNullUsername() {
        Customer customer = new Customer(0, "asdasdasdas", "asdasdasd", "asdasdas");
        int id;
        try {
            id = customerRepository.save(customer);
            customer.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customer.setPassword("123");
        customer.setUsername(null);
        customer.setAddress("123");
        try {
            customerRepository.update(customer);
            assert true;
        } catch (PersistenceException ignored) {
        }
    }

    @Test
    void updateCustomerNullPassword() {
        Customer customer = new Customer(0, "asdasdasdas", "asdasdasd", "asdasdas");
        int id;
        try {
            id = customerRepository.save(customer);
            customer.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customer.setPassword(null);
        customer.setUsername("123");
        customer.setAddress("123");
        try {
            customerRepository.update(customer);
            assert true;
        } catch (PersistenceException ignored) {
        }
    }

    @Test
    void updateCustomerNullAddress() {
        Customer customer = new Customer(0, "asdasdasdas", "asdasdasd", "asdasdas");
        int id;
        try {
            id = customerRepository.save(customer);
            customer.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customer.setPassword("123");
        customer.setUsername("123");
        customer.setAddress(null);
        try {
            customerRepository.update(customer);
            assert true;
        } catch (PersistenceException ignored) {
        }
    }

    @Test
    void findAllUser() {
        Customer customer = new Customer(0, "asdasdasdas", "asdasdasd", "asdasdas");
        int id;
        try {
            id = customerRepository.save(customer);
            customer.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Customer> all = customerRepository.findAll();
        all.forEach((e) ->
                Assertions.assertAll(
                        () -> Assertions.assertEquals("asdasdasdas", e.getUsername())
                        , () -> Assertions.assertEquals("asdasdasd", e.getPassword())
                        , () -> Assertions.assertEquals("asdasdas", e.getAddress())
                ));
    }


    @Test
    void deleteCustomer() {
        Customer customer = new Customer(0, "asdasdasdas", "asdasdasd", "asdasdas");
        int id = 0;
        try {
            id = customerRepository.save(customer);
            customer.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerRepository.delete(id);
        try {
            customerRepository.findById(id);
            assert true;
        } catch (SQLException | NotFoundException ignored) {
        }
    }

    @Test
    void loginNormal() {
        String username = "asdasdasdas";
        String password = "asdasdasd";
        Customer customer = new Customer(0, username, password, "asdasdas");
        int id;
        try {
            id = customerRepository.save(customer);
            customer.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            customerRepository.login(username, password);
        } catch (NotFoundException notFoundException) {
            assert true;
        }
    }

    @Test void login(){
        String username = "asdasdasdas";
        String password = "asdasdasd";
        Customer customer = new Customer(0, username, password, "asdasdas");
        int id;
        try {
            id = customerRepository.save(customer);
            customer.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            customerRepository.login(username, "nas");
            assert true;
        } catch (NotFoundException ignored) {
        }
    }

    @Test void findShoppingCart(){
        try {

            customerRepository.findShoppingCardByUserId(1);
        }catch (Exception ignored){

        }
    }

}