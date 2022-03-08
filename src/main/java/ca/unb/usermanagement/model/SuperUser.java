package ca.unb.usermanagement.model;

public class SuperUser extends User{
    protected static SuperUser superUser;
    private static final String USERNAME = "SuperUser";

    private SuperUser(String name) {
        super(name);
        addRole(UserRole.SUPERUSER);
    }

    public static SuperUser getInstance(){
        if (superUser == null) {
            superUser = new SuperUser(USERNAME);
        }
        return superUser;
    }
}
