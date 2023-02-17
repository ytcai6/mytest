package com.itcyt.service;

import com.itcyt.pojo.Brand;
import com.itcyt.pojo.PageBean;

import java.util.List;

public interface BrandService {

    /**
     *查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加数据
     * @param brand
     */
    void add(Brand brand);

    void updateById(Brand brand);


    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(int[] ids);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize,Brand brand);

    void deleteById(int id);

}
