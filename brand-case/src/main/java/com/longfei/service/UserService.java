package com.longfei.service;

import com.longfei.pojo.People;

public interface UserService {


    People selectByName(String username , String password);

    boolean register(People people);


    boolean verify(People people);


}
