package com.itbiye.mapper;

import com.itbiye.pojo.Expenditure;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author youmu
 */
@Mapper
public interface ExpenditureMapper {

    @Insert("insert into expenditure(trade_name,expenditure,create_user,remark,create_time,update_time) " +
            "values(#{tradeName},#{expenditure},#{createUser},#{reMark},#{createTime},#{updateTime})")
    void add(Expenditure expenditure);

    @Select("select * from expenditure where create_user = #{userId}")
    List<Expenditure> list(Integer userId);

    @Select("select * from expenditure where id = #{id}")
    Expenditure findById(Integer id);

    @Update("update expenditure set trade_name=#{tradeName},expenditure=#{expenditure},remark=#{reMark},update_time=#{updateTime} where id=#{id}")
    void update(Expenditure expenditure);

    @Delete("delete from expenditure where id=#{id}")
    void deleteById(Integer id);
}
