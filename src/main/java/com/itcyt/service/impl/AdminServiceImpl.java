package com.itcyt.service.impl;

import com.itcyt.mapper.AdminMapper;
import com.itcyt.pojo.Admin;
import com.itcyt.service.AdminService;
import com.itcyt.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class AdminServiceImpl implements AdminService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Admin login(String username, String password) {
        SqlSession sqlSession = factory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = mapper.login(username, password);
        sqlSession.close();
        return admin;
    }

    @Override
    public boolean register(Admin admin) {
        SqlSession sqlSession = factory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.register(admin);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
