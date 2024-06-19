package com.itbiye.service.impl;


import com.itbiye.mapper.WarehouseMapper;
import com.itbiye.pojo.Warehouse;
import com.itbiye.service.WarehouseService;
import com.itbiye.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseServicelmpl implements WarehouseService {
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public void add(Warehouse warehouse) {
        warehouse.setCreateTime(LocalDateTime.now());
        warehouse.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        warehouse.setCreateUser(userId);
        warehouseMapper.add(warehouse);

    }

    @Override
    public List<Warehouse> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return warehouseMapper.list(userId);
    }

    @Override
    public Warehouse findById(Integer id) {
        Warehouse c = warehouseMapper.findById(id);
        return c;
    }

    @Override
    public void update(Warehouse warehouse) {
        warehouse.setUpdateTime(LocalDateTime.now());
        warehouse.update(warehouse);

    }

    @Override
    public void deleteById(Integer id) {
        warehouseMapper.deleteById(id);

    }
}
