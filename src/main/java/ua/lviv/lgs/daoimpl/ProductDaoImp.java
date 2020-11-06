package ua.lviv.lgs.daoimpl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.utils.ConnectiolnalUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao {

    private static String READ_ALL = "select * from product";
    private static String CREATE = "insert into product(name, description,price)   values(?,?,?)";
    private  static String READ_BY_ID = "select * from product where id=?";
    private  static String UPDATE_BY_ID = "update product set name=?,description = ? ,price=?, where id=?";
    private static  String DELETE_BY_ID = "delete from product  where id=?";

    private static Logger LOGGER = Logger.getLogger(ProductDaoImp.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    public ProductDaoImp() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        connection = ConnectiolnalUtils.openConnection();

    }

    @Override
    public Product create(Product product) {
        try {
            preparedStatement = connection.prepareStatement(CREATE , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setDouble(3,product.getPrise());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }

    @Override
    public Product read(Integer id) {

        Product product =null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Integer productid = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Double prise = resultSet.getDouble("price");
            product = new Product(productid,name,description,prise);

        }catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setDouble(3,product.getPrise());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
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
    public List<Product> readAll()  {
        List<Product>productRecords = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer productid = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Double prise = resultSet.getDouble("price");
                productRecords.add(new Product(productid,name,description,prise));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return productRecords;
    }
}
