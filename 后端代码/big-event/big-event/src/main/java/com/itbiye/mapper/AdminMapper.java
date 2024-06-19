package com.itbiye.mapper;

import com.itbiye.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where admin_id=#{adminId}")
    Admin findByUserName(String adminId);

    @Insert("insert into admin(admin_id,password,create_time,update_time)" +
            " values(#{adminId},#{password},now(),now())")
    void add(String adminId, String password);


    @Update("update admin set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);

    @Update("update admin set admin_name=#{adminName},update_time=#{updateTime} where id=#{id}")
    void update(Admin admin);
}
