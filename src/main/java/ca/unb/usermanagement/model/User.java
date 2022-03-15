package ca.unb.usermanagement.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Long userID;
    private String username;
    private String email;
    private String password;
    private Set<UserRole> roles;
    
    public User(String username, String email, String password){
        this.username  = username;
        this.email = email;
        this.password = password;
        roles = new HashSet<>();
    }

    public Long getUserID(){
        return userID;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username  = username;
    }

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public void addRole(UserRole toAdd){
        roles.add(toAdd);
    }
    public void removeRole(UserRole toRemove){
        roles.remove(toRemove);
    }
    public Set<UserRole> getRoles(){
        return roles;
    }

    public String rolesToString() {
        StringBuffer sb = new StringBuffer();
        if (roles != null) {
            roles.forEach(userRole -> sb.append(userRole).append(","));
            return sb.toString();
        }
        return "";
    }

}