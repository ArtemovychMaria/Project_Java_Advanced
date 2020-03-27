package Project_Java_Advanced.daos;

import Project_Java_Advanced.entities.Bucket;
import Project_Java_Advanced.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class BucketDao implements CRUD <Bucket>{
    private Connection connection;
    private static final Logger log=Logger.getLogger(BucketDao.class);
    public static final String select_buckets="select * from bucket";
    public static final String select_by_id="select * from bucket where id=?";
    public static final String insert="insert into bucket(user_id,product_id,purchase_date) values(?,?,?)";
    public static final String delete="delete from bucket where id=?";
    public static final String update="update bucket set user_id=?,product_id=?,purchase_date=? where id=?";

    public BucketDao() {
        this.connection = ConnectionUtil.getConnection();;
    }

    @Override
    public Bucket selectById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select_by_id);
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Bucket.of(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error");
        }
    }

    @Override
    public List<Bucket> selectAll() {
        try {
            Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(select_buckets);

        List<Bucket> buckets= new ArrayList<>();

        while (resultSet.next()){
            buckets.add(Bucket.of(resultSet));
        }
        return buckets;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error selecting");
        }
    }


    @Override
    public Bucket insert(Bucket bucket) {

        String msg=String.format("Will be inserted bucket with " +
                "userId=%d and productId=%d",bucket.getUserId(),bucket.getProductId());
        log.debug(msg);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1,bucket.getUserId());
        preparedStatement.setInt(2,bucket.getProductId());
        preparedStatement.setDate(3,new Date(bucket.getPurchaseDate().getTime()));

        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        generatedKeys.next();

        bucket.setId(generatedKeys.getInt(1));

        return bucket;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting");
        }
    }

    @Override
    public void update(Bucket bucket) {
        String msg=String.format("Will be updated bucket with id=%d",bucket.getId());
        log.debug(msg);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setObject(1,bucket.getUserId());
            preparedStatement.setObject(2,bucket.getProductId());
            preparedStatement.setObject(3,bucket.getPurchaseDate());
            preparedStatement.setObject(4,bucket.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Update error");
        }
    }

    @Override
    public void delete(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setObject(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting from database");
        }
    }
}
