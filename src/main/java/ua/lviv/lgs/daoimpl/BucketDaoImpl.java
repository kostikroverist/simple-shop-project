package ua.lviv.lgs.daoimpl;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.utils.ConnectiolnalUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class BucketDaoImpl implements BucketDao {

    private static String READ_ALL = "select * from bucket";
    private static String CREATE = "insert into bucket(user_id, product_id,purchaseDate)   values(?,?,?)";
    private  static String READ_BY_ID = "select * from bucket where id=?";
    private static  String DELETE_By_ID = "delete from bucket where id=?";

    private static Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;
    public BucketDaoImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    connection = ConnectiolnalUtils.openConnection();

    }

    @Override
    public Bucket create(Bucket bucket) {

        try {
            preparedStatement = connection.prepareStatement(CREATE , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,bucket.getUser_id());
            preparedStatement.setInt(2,bucket.getProduct_id());
            preparedStatement.setDate(3,new Date(bucket.getPurchaseDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            bucket.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
        }


        return bucket;
    }
    @Override
    public Bucket read(Integer id) throws SQLException {
        Bucket bucket =null;
       try {
           preparedStatement = connection.prepareStatement(READ_BY_ID);
           preparedStatement.setInt(1,id);
           ResultSet resultSet = preparedStatement.executeQuery();
           resultSet.next();
           Integer bucketid = resultSet.getInt("id");
           Integer userId = resultSet.getInt("user_id");
           Integer productId = resultSet.getInt("product_id");
           java.util.Date purchaseDate = resultSet.getDate("purchaseDate");
           bucket = new Bucket(bucketid,userId,productId,purchaseDate);

       }catch (SQLException e) {
           LOGGER.error(e);
       }
          return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        throw new IllegalStateException("there is no  update for bucket");
    }

    @Override
    public void delete(Integer id)  {
        try {
            preparedStatement = connection.prepareStatement(DELETE_By_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Bucket> readAll()  {
        List<Bucket>bucketRecords = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer bucketid = resultSet.getInt("id");
                Integer userId = resultSet.getInt("user_id");
                Integer productId = resultSet.getInt("product_id");
                java.util.Date purchaseDate = resultSet.getDate("purchaseDate");
                bucketRecords.add(new Bucket(bucketid,userId,productId,purchaseDate));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return bucketRecords;
    }


}
