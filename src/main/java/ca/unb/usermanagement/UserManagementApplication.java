package ca.unb.usermanagement;

import ca.unb.usermanagement.model.EUserRole;
import ca.unb.usermanagement.model.UserRole;
import ca.unb.usermanagement.repository.UserRoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserManagementApplication {

    private final UserRoleRepository userRoleRepository;

    public UserManagementApplication(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            for (EUserRole role : EUserRole.values()) {
                if (!userRoleRepository.findByName(role).isPresent()) {
                    userRoleRepository.save(new UserRole(role));
                }
            }
        };
    }

}
