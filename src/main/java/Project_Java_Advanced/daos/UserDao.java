package Project_Java_Advanced.daos;

import Project_Java_Advanced.services.ConnectionUtil;
import Project_Java_Advanced.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

public class UserDao implements CRUD <User> {

    private Connection connection;
    private Logger log=Logger.getLogger(UserDao.class);
    public static final String select_users="select * from users";
    public static final String select_by_id="select * from users where id=?";
    public static final String select_by_email="select * from users where user_email=?";
    public static final String insert="insert into users(user_email,user_name,user_surname,user_role,user_password) values(?,?,?,?,?)";
    public static final String delete="delete from users where id=?";
    public static final String update="update users set user_email=?,user_name=?,user_surname=?,user_role=?,user_password=? where id=?";

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

    public Optional<User> selectByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select_by_email);
            preparedStatement.setObject(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(User.of(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting user by email= " + email);
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

        String msg=String.format("Will be inserted new user with email=%s,firstName=%s,surname=%s,role=%s and " +
                "password=%s",user.getEmail(),user.getFirstName(),user.getSurname(),user.getRole(),user.getPassword());
        log.debug(msg);

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1,user.getEmail());
        preparedStatement.setObject(2,user.getFirstName());
        preparedStatement.setObject(3,user.getSurname());
        preparedStatement.setObject(4,user.getRole());
        preparedStatement.setObject(5,user.getPassword());

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
            preparedStatement.setObject(5,user.getPassword());
            preparedStatement.setObject(6,user.getId());

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
