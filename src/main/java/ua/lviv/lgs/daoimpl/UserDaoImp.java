package ua.lviv.lgs.daoimpl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConnectiolnalUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {
    private static String READ_ALL = "select * from user";
    private static String CREATE = "insert into user(email, first_Name,last_Name,role,password)   values(?,?,?,?,?)";
    private  static String READ_BY_ID = "select * from user where id=?";
    private  static  String READ_BY_EMAIL = "select  * from user where email=?";
    private  static String UPDATE_BY_ID = "update user set email=?,first_Name = ? ,last_Name=?, role=?,password=? where id=?";
    private static  String DELETE_BY_ID = "delete from user  where id=?";

    private static Logger LOGGER = Logger.getLogger(UserDaoImp.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    public UserDaoImp() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        connection = ConnectiolnalUtils.openConnection();

    }
    @Override
    public User create(User user){

        try {
            preparedStatement = connection.prepareStatement(CREATE , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setString(4,user.getRole());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while(rs.next()){
                user.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }


        return user;
    }

    @Override
    public User read(Integer id) {
        User user =null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Integer userId = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("first_Name");
            String lastName = resultSet.getString("last_Name");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");
            user = new User(userId,email,firstName,lastName,role,password);

        }catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setString(4,user.getRole());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setInt(6,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }


        return user;
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Override
    public List<User> readAll()  {
        List<User>userRead = new ArrayList<>();


        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer userId = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_Name");
                String lastName = resultSet.getString("last_Name");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                userRead.add(new User(email,firstName,lastName,role,password));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return userRead;
    }

    @Override
    public User getUserByEmail(String email) {
        User user =null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_EMAIL);

            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Integer userId = resultSet.getInt("id");
            String firstName = resultSet.getString("first_Name");
            String lastName = resultSet.getString("last_Name");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");
            user = new User( userId,email,firstName,lastName,role,password);

        }catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }
}
