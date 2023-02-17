package com.itcyt.service;

import com.itcyt.pojo.PageBean;

public interface OrderService {
    String selectAll();

    void add(String order);

    void editById(String order);

    void deleteById(String _id);

    void deleteByIds(String[] ids);

    PageBean selectByPageAndCondition(int currentPage, int pageSize, String order);
}
