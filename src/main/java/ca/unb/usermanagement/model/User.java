package ca.unb.usermanagement.model;

import java.util.ArrayList;

public class User {
    private int userID;
    private String userName;
    private ArrayList<UserRole> roleList;
    private ArrayList<User> subordinateList;
    
    public User(String name){
        userID = UserRegistry.userRegistry.setUserID();
        userName  = name;
        roleList = new ArrayList<>();
        subordinateList = new ArrayList<>();
    }

    public int getUserID(){
        return userID;
    }

    public String getUserName(){
        return userName;
    }
    public void setUserName(String name){
        userName  = name;
    }

    public void addRole(UserRole toAdd){
        roleList.add(toAdd);
    }
    public void removeRole(UserRole toRemove){
        roleList.remove(toRemove);
    }
    public ArrayList<UserRole> getRoleList(){
        return roleList;
    }

    public void addSubordinate(User toAdd){
        subordinateList.add(toAdd);
    }
    public void removeSubordinate(User toRemove){
        subordinateList.remove(toRemove);
    }
    public ArrayList<User> getSubordinateList(){
        return subordinateList;
    }

}