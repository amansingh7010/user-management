package ca.unb.usermanagement.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRegistry {
    protected static UserRegistry userRegistry;
    private ArrayList<User> userList;
    private AtomicInteger idProvider;
    private SuperUser superUser;

    private UserRegistry(){
        userList = new ArrayList<>();
        idProvider = new AtomicInteger(0);
    }
    
    public static UserRegistry getInstance(){
        if (userRegistry == null) {
            userRegistry = new UserRegistry();
            userRegistry.superUser = SuperUser.getInstance();
            userRegistry.addUser(userRegistry.superUser);
        }
        return userRegistry;
    }
        
    public void addUser(User toAdd){
        userList.add(toAdd);
    }
    public void removeUser(User toRemove){
        userList.remove(toRemove);
    }
    public ArrayList<User> getUserList(){
        return userList;        
    }

    public SuperUser getSuperUser(){
        return superUser;
    }
 
    public int setUserID() {
        return idProvider.getAndIncrement();
    }

}
