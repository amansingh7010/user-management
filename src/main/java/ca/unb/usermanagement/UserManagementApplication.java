package ca.unb.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import ca.unb.usermanagement.model.UserRegistry;

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);

		//System.out.println("Number of UserRegistry users: " + UserRegistry.getInstance().getUsers().size());
	}

}
