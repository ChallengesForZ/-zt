package com.itbiye.service.impl;

import com.itbiye.mapper.AdminMapper;
import com.itbiye.pojo.Admin;
import com.itbiye.service.AdminService;
import com.itbiye.utils.Md5Util;
import com.itbiye.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AdminServicelmpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin findByAdminId(String adminId) {
        Admin u = adminMapper.findByUserName(adminId);
        return u;
    }

    @Override
    public void register(String adminId, String password) {

        String md5String = Md5Util.getMD5String(password);
        //添加
        adminMapper.add(adminId,md5String);

    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        adminMapper.updatePwd(Md5Util.getMD5String(newPwd),id);

    }

    @Override
    public void update(Admin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.update(admin);

    }
}
