package com.itbiye.mapper;

import com.itbiye.pojo.Salary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SalaryMapper {

    @Insert("insert into salary(username,salary_month,salary,create_user,create_time,update_time) " +
            "values(#{username},#{salaryMonth},#{salary},#{CreateUser},#{createTime},#{updateTime})")
    void add(Salary salary);

    List<Salary> list(Integer userId);

    @Select("select * from salary where id = #{id}")
    Salary findById(Integer id);

    @Update("update salary set username=#{username},salary_month=#{salary_month},salary=#{salary},update_time=#{updateTime} where id=#{id}")
    void update(Salary salary);

    @Delete("delete from salary where id=#{id}")
    void deleteById(Integer id);
}
