package com.longfei.mapper;

import com.longfei.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll1();

    /*
     * 查询所有
     * @return
     * */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /*
     * 添加数据
     * @param brand
     * */

    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);


    /**
     * 删除
     */
    @Delete("delete from tb_brand where id=#{id}")
    void deleteById(int id);

    /*
    * 批量删除
    * */

    void deleteByIds(@Param("ids")int[] ids);


    /*
    * 分页查询
    * begin:从哪开始查
    * size:查询几条数据
    * */
    @Select("select * from tb_brand limit #{begin} , #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /*
    * 查询总记录数
    * */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();


    /*
     * 按条件分页查询
     * begin:从哪开始查
     * size:查询几条数据
     * */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);

    /*
     * 查询总记录数
     * */

    int selectTotalCountByCondition(Brand brand);

    //修改数据
    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    @ResultMap("brandResultMap")
    void update(Brand brand);




}