package com.itbiye.mapper;


import com.itbiye.pojo.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {


    @Insert("insert into goods(goods_name,warehouse_Id,state,create_user,create_time,update_time) " +
            "values(#{goodsName},#{warehouseId},#{state},#{createUser},#{createTime},#{updateTime})")
    void add(Goods goods);


    List<Goods> list(Integer userId, Integer warehouseId, String state);

    @Select("select * from goods where id = #{id}")
    Goods findById(Integer id);

    @Update("update goods set goods_name=#{goodsName},state=#{state},update_time=#{updateTime} where id=#{id}")
    void update(Goods goods);

    @Delete("delete from goods where id=#{id}")
    void deleteById(Integer id);
}
