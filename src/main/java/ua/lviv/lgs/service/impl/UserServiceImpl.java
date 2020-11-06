package ua.lviv.lgs.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.daoimpl.UserDaoImp;
import ua.lviv.lgs.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);
    private  static UserService userServiceImpl;
    private UserDao userDao;

    public UserServiceImpl(){
        try {
            userDao = new UserDaoImp();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException| SQLException e) {
            LOGGER.error(e);
        }
    }

    public static UserService getUserService(){

        if (userServiceImpl == null){
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public User create(User user) throws SQLException {
        return userDao.create(user);
    }

    @Override
    public User read(Integer id) throws SQLException {
        return userDao.read(id);
    }

    @Override
    public User update(User user) throws SQLException {
        return userDao.update(user);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        userDao.delete(id);
    }

    @Override
    public List<User> readAll() throws SQLException {
        return userDao.readAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
