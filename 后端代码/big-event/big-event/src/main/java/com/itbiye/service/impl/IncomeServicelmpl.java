package com.itbiye.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itbiye.mapper.IncomeMapper;
import com.itbiye.pojo.Income;
import com.itbiye.pojo.PageBean;
import com.itbiye.service.IncomeService;
import com.itbiye.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class IncomeServicelmpl implements IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;
    @Override
    public void add(Income income) {
        income.setCreateTime(LocalDateTime.now());
        income.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        income.setCreateUser(userId);

        incomeMapper.add(income);
    }

    @Override
    public PageBean<Income> list(Integer pageNum, Integer pageSize, Integer categoryId) {
        //1.创建PageBean对象
        PageBean<Income> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);

        //3.调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Income> as = incomeMapper.list(userId,categoryId);

        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Income> p = (Page<Income>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Income findById(Integer id) {
        Income c = incomeMapper.findById(id);
        return c;
    }

    @Override
    public void update(Income income) {
        income.setUpdateTime(LocalDateTime.now());
        incomeMapper.update(income);
    }

    @Override
    public void deleteById(Integer id) {
        incomeMapper.deleteById(id);

    }
}
