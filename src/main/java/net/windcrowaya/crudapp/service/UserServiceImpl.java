package net.windcrowaya.crudapp.service;

import net.windcrowaya.crudapp.dao.UserDao;
import net.windcrowaya.crudapp.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return this.userDao.getUser(id);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return this.userDao.listUsers();
    }
}
