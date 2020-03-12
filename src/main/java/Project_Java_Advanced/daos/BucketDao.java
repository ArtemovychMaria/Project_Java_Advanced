package Project_Java_Advanced.daos;

import Project_Java_Advanced.entities.Bucket;
import Project_Java_Advanced.services.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDao implements CRUD <Bucket>{
    private Connection connection;
    public static final String select_buckets="select * from buckets";
    public static final String select_by_id="select * from buckets where id=?";
    public static final String insert="insert into buckets(user_id,product_id,purchase_date) values(?,?,?)";
    public static final String delete="delete from buckets where id=?";
    public static final String update="update buckets set user_id=?,product_id=?,purchase_date=? where id=?";

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
        Statement statement = null;
        try {
            statement = connection.createStatement();
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

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1,bucket.getUserId());
        preparedStatement.setObject(2,bucket.getProductId());
        preparedStatement.setObject(3,bucket.getPurchaseDate());

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
