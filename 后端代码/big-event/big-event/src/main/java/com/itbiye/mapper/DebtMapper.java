package com.itbiye.mapper;

import com.itbiye.pojo.Debt;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DebtMapper {

    @Insert("insert into debt(debt_name,amount,creditor,create_user,create_time,update_time) " +
            "values(#{debtName},#{amount},#{creditor},#{createUser},#{createTime},#{updateTime})")
    void add(Debt debt);

    @Select("select * from debt where id = #{id}")
    Debt findById(Integer id);

    @Update("update debt set debt_name=#{debtName},amount=#{amount},creditor=#{creditor},update_time=#{updateTime} where id=#{id}")
    void update(Debt debt);

    @Delete("delete from debt where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from debt where create_user = #{userId}")
    List<Debt> list(Integer userId);
}
