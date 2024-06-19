package com.itbiye.service;

import com.itbiye.pojo.Warehouse;

import java.util.List;

public interface WarehouseService {
    void add(Warehouse warehouse);

    List<Warehouse> list();

    Warehouse findById(Integer id);

    void update(Warehouse warehouse);

    void deleteById(Integer id);
}
