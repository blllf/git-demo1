package com.longfei.mapper;

import com.longfei.pojo.People;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {


    /**
     * 根据用户名和密码查询用户对象
     * @param username
     * @param password
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    People select(@Param("username")String username,@Param("password")String password);



    /**
     * 增加用户
     *
     */
    @Insert("insert into user values(null,#{username},#{password})")
    void add(People people);

    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    People selectByUsername(String username);




}
