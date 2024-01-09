package com.longfei.service.impl;

import com.longfei.mapper.UserMapper;
import com.longfei.pojo.People;
import com.longfei.service.UserService;
import com.longfei.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();




    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */

    public People selectByName(String username , String password){
        //调用BrandMapper.selectAll()

        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4. 调用方法
        People people = mapper.select(username,password);

        sqlSession.close();

        return people;
    }




    /**
     * 注册方法
     */


    public boolean register(People people){
        //调用BrandMapper.selectAll()

        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        People people1 = mapper.selectByUsername(people.getUsername());

        if (people1 == null ){
            mapper.add(people);
            sqlSession.commit();
        }

        sqlSession.close();
        return people1 == null ;


    }


    public boolean verify(People people){

        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        People people2 = mapper.selectByUsername(people.getUsername());


        if (people2 != null ){
            //存在相同用户名字
            return true;
        }
        //存在不同用户名字

        sqlSession.close();

        return false;



    }


}
