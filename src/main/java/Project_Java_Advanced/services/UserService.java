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

    public User insert(User user){
        return userDao.insert(user);
    }

    public User selectByID(int id){
        return userDao.selectById(id);
    }
    public Optional<User> selectByEmail(String email){
        return userDao.selectByEmail(email);
    }

    public Optional<User> selectByEmailAndPassword(String email, String password) {
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
