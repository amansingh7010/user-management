package ca.unb.usermanagement.model;

public class SuperUser extends User{
    protected static SuperUser superUser;
    private static final String USERNAME = "SuperUser";
    private static final String EMAIL = "SuperUser@superuser.superuser";
    private static final String PASSWORD = "SuperUser";

    private SuperUser(String name, String email, String password) {
        super(name, email, password);
        addRole(UserRole.SUPERUSER);
    }

    public static SuperUser getInstance(){
        if (superUser == null) {
            superUser = new SuperUser(USERNAME, EMAIL, PASSWORD);
        }
        return superUser;
    }
}
