package com.itbiye.service.impl;

import com.itbiye.mapper.DebtMapper;
import com.itbiye.pojo.Debt;
import com.itbiye.service.DebtService;
import com.itbiye.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DebtServicelmpl implements DebtService {

    @Autowired
    private DebtMapper debtMapper ;
    @Override
    public void add(Debt debt) {
        debt.setCreateTime(LocalDateTime.now());
        debt.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        debt.setCreateUser(userId);
        debtMapper.add(debt);

    }



    @Override
    public Debt findById(Integer id) {
        Debt c = debtMapper.findById(id);
        return c;
    }

    @Override
    public void update(Debt debt) {
        debt.setUpdateTime(LocalDateTime.now());
        debtMapper.update(debt);

    }

    @Override
    public void deleteById(Integer id) {
        debtMapper.deleteById(id);

    }

    @Override
    public List<Debt> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return debtMapper.list(userId);
    }
}
