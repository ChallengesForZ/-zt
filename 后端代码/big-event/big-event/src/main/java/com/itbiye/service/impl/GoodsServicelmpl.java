package com.itbiye.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itbiye.mapper.GoodsMapper;
import com.itbiye.pojo.Goods;
import com.itbiye.pojo.PageBean;
import com.itbiye.service.GoodsService;
import com.itbiye.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServicelmpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void add(Goods goods) {
        //补充属性值
        goods.setCreateTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        goods.setCreateUser(userId);

        goodsMapper.add(goods);
    }

    @Override
    public PageBean<Goods> list(Integer pageNum, Integer pageSize, Integer warehouseId, String state) {
        //1.创建PageBean对象
        PageBean<Goods> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);

        //3.调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Goods> as = goodsMapper.list(userId,warehouseId,state);
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Goods> p = (Page<Goods>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Goods findById(Integer id) {
        Goods a = goodsMapper.findById(id);
        return a;
    }

    @Override
    public void update(Goods goods) {
        goods.setUpdateTime(LocalDateTime.now());
        goodsMapper.update(goods);

    }

    @Override
    public void deleteById(Integer id) {
        goodsMapper.deleteById(id);

    }
}
