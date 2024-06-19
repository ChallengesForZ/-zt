package com.itbiye.mapper;

import com.itbiye.pojo.Warehouse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WarehouseMapper {
    @Insert("insert into warehouse(warehouse_name,warehouse_alias,create_user,create_time,update_time) " +
            "values(#{warehouseName},#{warehouseAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Warehouse warehouse);

    @Select("select * from warehouse where create_user = #{userId}")
    List<Warehouse> list(Integer userId);

    @Update("update warehouse set warehouse_name=#{warehouseName},warehouse_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    Warehouse findById(Integer id);

    @Delete("delete from category where id=#{id}")
    void deleteById(Integer id);
}
