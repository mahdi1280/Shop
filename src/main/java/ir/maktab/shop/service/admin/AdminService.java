package ir.maktab.shop.service.admin;

import ir.maktab.shop.entity.Admin;
import ir.maktab.shop.repository.admin.AdminRepository;
import ir.maktab.shop.service.UserService;

public class AdminService extends UserService<Admin, AdminRepository> {

    public AdminService() {
        super(new AdminRepository());
    }


}
