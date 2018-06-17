package service;

import service.validators.UserValidator;
import dao.UserDAO;
import model.User;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserBLL {
   static UserValidator v=new UserValidator();

    public static ArrayList<User> getClients() {
        return UserDAO.getusers();
    }
    public static int insertClient(User user) {
        v.validate(user);
        return UserDAO.insert(user);
    }


    public static User findByUsernameAll(String username){
        User st = UserDAO.findByUsernameAll(username);
        if (st == null) {
            throw new NoSuchElementException("The user with name =" + username + " was not found!");
        }
        return st;
    }


    public static void update( User user) {
        v.validate(user);
        UserDAO.update( user);
    }

    public static int isLogged(){
        return UserDAO.isLogged();

    }
    public static String isLoggedUser(){
        return  UserDAO.isLoggedUser();
    }
    public  static void logare(String username){


        UserDAO.logare(username);
    }
    public static boolean isLoyal(int id){
        return UserDAO.isLoyal(id);
    }

    public static  void delogare(){
        UserDAO.delogare();
    }
    public static void makeLoyal(String username){
        UserDAO.makeLoyal(username);
    }

}

