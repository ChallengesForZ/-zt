package com.itbiye.service;

import com.itbiye.pojo.Goods;
import com.itbiye.pojo.PageBean;

public interface GoodsService {
    void add(Goods goods);

    PageBean<Goods> list(Integer pageNum, Integer pageSize, Integer warehouseId, String state);

    Goods findById(Integer id);

    void update(Goods goods);

    void deleteById(Integer id);
}
