package com.itcyt.service;

import com.itcyt.pojo.Admin;

public interface AdminService {
    /**
     * 登录检查功能
     * @param username
     * @param password
     */
    Admin login(String username, String password);

    boolean register(Admin admin);
}
