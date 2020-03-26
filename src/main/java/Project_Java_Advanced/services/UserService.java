package Project_Java_Advanced.services;

import Project_Java_Advanced.daos.UserDao;
import Project_Java_Advanced.entities.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private UserDao userDao;
    private static UserService userService;

    private UserService(){
        this.userDao=new UserDao();
    }

    public static UserService getUserService(){
        if(userService==null){
            userService=new UserService();
        }
        return userService;
    }

    public User insert(String email,String firstName,String lastName,String pasword){
        return userDao.insert(User.builder()
        .setEmail(email)
        .setFirstName(firstName)
        .setSurname(lastName)
        .setPassword(pasword)
        .build());
    }

    public User getByID(int id){
        return userDao.selectById(id);
    }
    public Optional<User> getByEmail(String email){
        return userDao.selectByEmail(email);
    }

    public Optional<User> getByEmailAndPassword(String email, String password) {
        return userDao.selectByEmail(email).filter(user -> user.getPassword().equals(password));
    }

    public List<User> selectAll(){
        return userDao.selectAll();
    }

    public void update(User user){
        userDao.update(user);
    }

    public void  delete(int id){
        userDao.delete(id);
    }
}
