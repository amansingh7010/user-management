package ca.unb.usermanagement.service.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ca.unb.usermanagement.model.EUserRole;
import ca.unb.usermanagement.model.User;
import ca.unb.usermanagement.model.UserRole;
import ca.unb.usermanagement.payload.request.SignupRequest;
import ca.unb.usermanagement.payload.response.MessageResponse;
import ca.unb.usermanagement.payload.response.Response;
import ca.unb.usermanagement.payload.response.UserInfoResponse;
import ca.unb.usermanagement.repository.UserRepository;
import ca.unb.usermanagement.repository.UserRoleRepository;
import ca.unb.usermanagement.security.services.UserDetailsImpl;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.encoder = encoder;
    }

    @Override
    public UserInfoResponse authenticate(UserDetailsImpl userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new Response().createUserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles);
    }

    @Override
    public MessageResponse logout() {
        return new Response().createMessageResponse("Logout success!");
    }

    @Override
    public MessageResponse saveUser(SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<UserRole> roles = new HashSet<>();
        if (strRoles == null) {
            UserRole userRole = userRoleRepository.findByName(EUserRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: UserRole not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                	case "superadmin":
	                    UserRole superAdminRole = userRoleRepository.findByName(EUserRole.ROLE_SUPERUSER)
	                            .orElseThrow(() -> new RuntimeException("Error: UserRole not found."));
	                    roles.add(superAdminRole);
	                    break;
                    case "admin":
                        UserRole adminRole = userRoleRepository.findByName(EUserRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: UserRole not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        UserRole modRole = userRoleRepository.findByName(EUserRole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: UserRole not found."));
                        roles.add(modRole);
                        break;
                    default:
                        UserRole userRole = userRoleRepository.findByName(EUserRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: UserRole not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new Response().createMessageResponse("User registered successfully!");
    }
}
