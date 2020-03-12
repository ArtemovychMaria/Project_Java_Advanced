package Project_Java_Advanced.services;

import Project_Java_Advanced.entities.User;

public class App 
{
    public static void main( String[] args )
    {
        UserService userService = UserService.getUserService();
//        System.out.println(userService.insert(new User(3,"hmura@mail",
//                "oksana","hmura","customer")));

//        User user=userService.selectByID(2);
//        user.setRole("customer");
//        userService.update(user);
//        userService.delete(3);
        System.out.println(userService.selectAll());
//        System.out.println(userService.selectByID(3));






    }
}
