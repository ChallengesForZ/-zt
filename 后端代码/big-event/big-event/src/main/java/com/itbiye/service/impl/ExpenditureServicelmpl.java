package com.itbiye.service.impl;

import com.itbiye.mapper.ExpenditureMapper;
import com.itbiye.pojo.Expenditure;
import com.itbiye.service.ExpenditureService;
import com.itbiye.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ExpenditureServicelmpl implements ExpenditureService {

    @Autowired
    private ExpenditureMapper expenditureMapper ;
    @Override
    public void add(Expenditure expenditure) {
        expenditure.setCreateTime(LocalDateTime.now());
        expenditure.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        expenditure.setCreateUser(userId);
        expenditureMapper.add(expenditure);
    }

    @Override
    public List<Expenditure> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return expenditureMapper.list(userId);
    }

    @Override
    public Expenditure findById(Integer id) {
        Expenditure c = expenditureMapper.findById(id);
        return c;
    }

    @Override
    public void update(Expenditure expenditure) {
        expenditure.setUpdateTime(LocalDateTime.now());
        expenditureMapper.update(expenditure);
    }

    @Override
    public void deleteById(Integer id) {
        expenditureMapper.deleteById(id);
    }
}
