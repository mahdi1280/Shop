package ir.maktab.shop.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin extends User{

    private String nationalCode;

    public Admin(int id, String username, String password, String nationalCode) {
        super(id, username, password);
        this.nationalCode = nationalCode;
    }
}
