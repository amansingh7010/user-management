package ca.unb.usermanagement.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRegistry {
    protected static UserRegistry userRegistry;
    private Set<User> users;
    private AtomicInteger idProvider;
    private SuperUser superUser;

    private UserRegistry() {
        users = new HashSet<>();
        idProvider = new AtomicInteger(0);
    }

    public static UserRegistry getInstance() {
        if (userRegistry == null) {
            userRegistry = new UserRegistry();
            userRegistry.superUser = SuperUser.getInstance();
            userRegistry.addUser(userRegistry.superUser);
        }
        return userRegistry;
    }

    public void addUser(User toAdd) {
        users.add(toAdd);
    }

    public void removeUser(User toRemove) {
        users.remove(toRemove);
    }

    public Set<User> getUsers() {
        return users;
    }

    public SuperUser getSuperUser() {
        return superUser;
    }

    public int setUserID() {
        return idProvider.getAndIncrement();
    }

}
