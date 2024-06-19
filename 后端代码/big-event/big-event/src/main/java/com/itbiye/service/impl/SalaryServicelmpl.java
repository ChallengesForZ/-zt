package com.itbiye.service.impl;

import com.itbiye.mapper.SalaryMapper;
import com.itbiye.pojo.Salary;
import com.itbiye.service.SalaryService;
import com.itbiye.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class SalaryServicelmpl implements SalaryService {


    @Autowired
    private SalaryMapper salaryMapper ;

    @Override
    public void add(Salary salary) {
        salary.setCreateTime(LocalDateTime.now());
        salary.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        salary.setCreateUser(userId);
        salaryMapper.add(salary);
    }

    @Override
    public List<Salary> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return salaryMapper.list(userId);
    }

    @Override
    public Salary findById(Integer id) {
        Salary c = salaryMapper.findById(id);
        return c;
    }

    @Override
    public void update(Salary salary) {
        salary.setUpdateTime(LocalDateTime.now());
        salaryMapper.update(salary);
    }

    @Override
    public void deleteById(Integer id) {
        salaryMapper.deleteById(id);
    }
}
