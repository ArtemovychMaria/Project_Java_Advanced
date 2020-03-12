package Project_Java_Advanced.services;

import Project_Java_Advanced.daos.UserDao;
import Project_Java_Advanced.entities.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

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
