package nl.hu.bep.shopping.model;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class MyUser implements Principal{
    private String name;
    private String role;
    private String username;
    private String password;

    private static List<MyUser> alleUsers = new ArrayList<>();


    public MyUser(String name, String role, String username, String password){
        this.name=name;
        this.role=role;
        this.username=username;
        this.password=password;
        alleUsers.add(this);




    }

    public String getName() {
        return name;
    }
    public String getRole(){
        return role;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public static MyUser getUserByName(String name){
        for(MyUser user : alleUsers){
            if(user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }


    public static String validateLogin(String nM, String pW){
        for(MyUser user: alleUsers){
            if(user.getUsername().equals(nM) && user.getPassword().equals(pW)){
                return user.getRole();
                }
        }
        return null;




    }
}
