package ca.unb.usermanagement.model;

import java.util.HashSet;
import java.util.Set;

public class SuperUser extends User{
    protected static SuperUser superUser;
    private static final String USERNAME = "SuperUser";
    private static final String EMAIL = "SuperUser@superuser.superuser";
    private static final String PASSWORD = "SuperUser";

    private SuperUser(String name, String email, String password) {
        super(name, email, password);
    }

    	public static SuperUser getInstance(){
        if (superUser == null) {
            superUser = new SuperUser(USERNAME, EMAIL, PASSWORD);
            Set<UserRole> uRoleSet = new HashSet<>();
            uRoleSet.add(new UserRole(EUserRole.ROLE_SUPERUSER));
            superUser.setRoles(uRoleSet);
        }
        return superUser;
    }
}
