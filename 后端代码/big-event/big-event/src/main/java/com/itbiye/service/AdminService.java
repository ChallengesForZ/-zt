package com.itbiye.service;

import com.itbiye.pojo.Admin;

public interface AdminService {
    Admin findByAdminId(String adminId);

    void register(String adminId, String password);

    void updatePwd(String newPwd);

    void update(Admin admin);
}
