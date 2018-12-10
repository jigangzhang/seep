package com.god.seep.service;

import com.god.seep.bean.User;
import com.god.seep.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements BaseService<User> {
    @Resource
    private UserDao userDao;

    public Long save(User user) {
        return userDao.save(user);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User load(Long id) {
        return userDao.load(id);
    }
}
