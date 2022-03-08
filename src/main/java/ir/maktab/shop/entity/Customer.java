package ir.maktab.shop.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends User {

    @Column(nullable = false)
    private String address;

    public Customer(int id){
        super(id);
    }

    public Customer(int id,String username,String password,String address){
        super(id,username,password);
        this.address=address;
    }
}
