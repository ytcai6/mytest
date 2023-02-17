package com.itcyt.service;

import com.itcyt.pojo.Brand;
import com.itcyt.pojo.PageBean;

public interface UserService {
    /**
     * 查询所有数据
     * @return
     */
    String selectAll();

    /**
     * 根据_id删除数据
     * @param _id
     */
    void deleteById(String _id);

    /**
     * 新增数据
     */
    void add(String user);

    /**
     * 编辑数据
     */
    void editById(String newUser);

    /**
     * 批量删除数据
     */
    void deleteByIds(String[] ids);

    PageBean selectByPageAndCondition(int currentPage, int pageSize, String user);
}
