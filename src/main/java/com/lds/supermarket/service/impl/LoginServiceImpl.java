package com.lds.supermarket.service.impl;

import com.lds.supermarket.dao.UserDao;
import com.lds.supermarket.entity.User;
import com.lds.supermarket.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserBypas(User user) {
        return userDao.getUserByPas(user);
    }
}
