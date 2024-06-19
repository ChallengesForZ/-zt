package com.itbiye.mapper;

import com.itbiye.pojo.Income;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IncomeMapper {
    @Insert("insert into income(category_id,trade_name,profit,create_user,remark,create_time,update_time) " +
            "values(#{categoryId},#{tradeName},#{profit},#{createUser},#{reMark},#{createTime},#{updateTime})")
    void add(Income income);

    List<Income> list(Integer userId, Integer categoryId);

    @Select("select * from income where id = #{id}")
    Income findById(Integer id);

    @Update("update income set category_id=#{categoryId},trade_name=#{tradeName},profit=#{profit},remark=#{reMark},update_time=#{updateTime} where id=#{id}")
    void update(Income income);

    @Delete("delete from income where id=#{id}")
    void deleteById(Integer id);
}
