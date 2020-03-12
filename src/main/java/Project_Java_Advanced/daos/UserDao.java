package Project_Java_Advanced.daos;

import Project_Java_Advanced.services.ConnectionUtil;
import Project_Java_Advanced.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements CRUD <User> {

    private Connection connection;
    public static final String select_users="select * from users";
    public static final String select_by_id="select * from users where id=?";
    public static final String insert="insert into users(user_email,user_name,user_surname,user_role) values(?,?,?,?)";
    public static final String delete="delete from users where id=?";
    public static final String update="update users set user_email=?,user_name=?,user_surname=?,user_role=? where id=?";

    public UserDao() {
        this.connection = ConnectionUtil.getConnection();;
    }

    @Override
    public User selectById(int id) {
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(select_by_id);
        preparedStatement.setObject(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return User.of(resultSet);
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error");
    }
}

    @Override
    public List<User> selectAll(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(select_users);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error selecting");
        }

        List<User> users= new ArrayList<>();

        while (resultSet.next()){
            users.add(User.of(resultSet));
        }
        return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error");
        }
    }


    @Override
    public User insert(User user) {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1,user.getEmail());
        preparedStatement.setObject(2,user.getFirstName());
        preparedStatement.setObject(3,user.getSurname());
        preparedStatement.setObject(4,user.getRole());

        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        generatedKeys.next();

        user.setId(generatedKeys.getInt(1));

        return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting");
        }
    }


    @Override
    public void update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setObject(1,user.getEmail());
            preparedStatement.setObject(2,user.getFirstName());
            preparedStatement.setObject(3,user.getSurname());
            preparedStatement.setObject(4,user.getRole());
            preparedStatement.setObject(5,user.getId());

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
