package ir.maktab.shop.entity;

public class Admin extends User{

    private String nationalCode;

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
