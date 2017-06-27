package net.windcrowaya.crudapp.dao;

import net.windcrowaya.crudapp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println("addUser UDI before " + user.getCreatedDate());
        session.persist(user);
        System.out.println("addUser UDI after " + user.getCreatedDate());
        logger.info("User successfully added. User details: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println("updateUser UDI before " + user.getCreatedDate());
        session.update(user);
        System.out.println("updateUser UDI after " + user.getCreatedDate());
        logger.info("User successfully updated. User details: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);

        if (user != null)
            session.delete(user);
        logger.info("User successfully removed by id. User details: " + user);
    }

    @Override
    public User getUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        logger.info("User successfully loaded by id. User details: " + user);
        System.out.println("getUser UDI " + user.getCreatedDate());
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();

        for (User user: userList)
            logger.info("User list: " + user);

        return userList;
    }
}
