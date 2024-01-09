package com.longfei.service;

import com.longfei.pojo.Brand;
import com.longfei.pojo.PageBean;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface BrandService {

    List<Brand> selectAll1();

    /*
     * 查询所有
     * @return
     * */
    List<Brand> selectAll();

    /*
    * 添加数据
    * @param brand
    * */
    void add(Brand brand);

    /**
     * 删除
     */
    void deleteById(int id);

    /**
     * 批量删除
     */

    void deleteByIds(int[] ids);

    /*
    * 分页查询
    * currentPage : 当前页码
    * pageSize : 每页展示条数
    * */
    PageBean<Brand> selectByPage(int currentPage , int pageSize);

    /*
    * 分页条件查询
    *
    * */
    PageBean<Brand> selectByPageAndCondition(int currentPage , int pageSize , Brand brand);

    //修改数据
    void update(Brand brand);



}
