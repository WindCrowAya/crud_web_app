package net.windcrowaya.crudapp.dao;

import net.windcrowaya.crudapp.model.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
    User getUser(int id);

    List<User> listUsers();
}
