package com.itcyt.mapper;

import com.itcyt.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * 后台员工管理Mapper
 */
public interface AdminMapper {

    @Select("select * from admin where username=#{username} and password=#{password}")
    @ResultMap("adminResultMap")
    Admin login(@Param("username") String username, @Param("password") String password);

    @Insert("insert into admin values (null,#{username},#{password},#{phoneNumber},#{userPosition})")
    void register(Admin admin);
}
