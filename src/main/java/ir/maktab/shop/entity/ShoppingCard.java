package ir.maktab.shop.entity;

import java.sql.Date;

public class ShoppingCard {

    private int id;
    private Date date;
    private boolean payed;
    private Customer customer;

    public ShoppingCard(int id) {
        this.id = id;
    }

    public ShoppingCard(int id, Date date, boolean payed, Customer customer) {
        this.id = id;
        this.date = date;
        this.payed = payed;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ShoppingCard{" +
                "id=" + id +
                ", date=" + date +
                ", payed=" + payed +
                '}';
    }
}
