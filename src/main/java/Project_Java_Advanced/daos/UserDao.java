package Project_Java_Advanced.daos;

import Project_Java_Advanced.EntityManagerUtils;
import Project_Java_Advanced.utils.ConnectionUtil;
import Project_Java_Advanced.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;

public class UserDao implements CRUD <User> {

    private Connection connection;
    private static final Logger log=Logger.getLogger(UserDao.class);
    public static final String select_users="select * from users";
    public static final String select_by_id="select * from users where id=?";
    public static final String select_by_email="select u from User u where u.email = :email ";
    public static final String insert="insert into users(user_email,user_name,user_surname,user_role,user_password) values(?,?,?,?,?)";
    public static final String delete="delete from users where id=?";
    public static final String update="update users set user_email=?,user_name=?,user_surname=?,user_role=?,user_password=? where id=?";

    public UserDao() {
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
        EntityManager entityManager=EntityManagerUtils.getEntityManager();

        entityManager.getTransaction().begin();
        User user= (User) entityManager.createQuery(select_by_email)
                .setParameter("email",email)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> selectAll(){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select_users);

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

        String msg=String.format("Will be inserted new user with email=%s,firstName=%s,surname=%s and " +
                "password=%s",user.getEmail(),user.getFirstName(),user.getSurname(),user.getPassword());
        log.debug(msg);
        log.info(msg);

        EntityManager entityManager= EntityManagerUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        return user;
    }

    private void fillStatementWithUpdateParameters(PreparedStatement preparedStatement,User user) {
        try {
        preparedStatement.setObject(1,user.getEmail());
        preparedStatement.setObject(2,user.getFirstName());
        preparedStatement.setObject(3,user.getSurname());
        preparedStatement.setObject(4,user.getRole());
        preparedStatement.setObject(5,user.getPassword());
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error");
        }
    }

    @Override
    public void update(User user) {
        String msg=String.format("Will be updated used with id=%d",user.getId());
        log.info(msg);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            fillStatementWithUpdateParameters(preparedStatement,user);
            preparedStatement.setObject(6,user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Update error");
        }
    }

    @Override
    public void delete(int id){
        String msg=String.format("Will be deleted used with id=%d",id);
        log.info(msg);
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
