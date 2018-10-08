package com.god.seep.dao;

import com.god.seep.bean.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDao implements BaseDao<User> {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public Long save(User user) {
        getSession().save(user);
        return user.getId();
    }

    public void delete(Long id) {
        getSession().delete(load(id));
    }

    public void update(User user) {
        getSession().update(user);
    }

    public User load(Long id) {
        return getSession().load(User.class, id);
    }
}
