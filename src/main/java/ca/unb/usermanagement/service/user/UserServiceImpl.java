package ca.unb.usermanagement.service.user;

import ca.unb.usermanagement.model.EUserRole;
import ca.unb.usermanagement.model.User;
import ca.unb.usermanagement.model.UserRole;
import ca.unb.usermanagement.payload.request.LoginRequest;
import ca.unb.usermanagement.payload.request.SignupRequest;
import ca.unb.usermanagement.payload.request.DeleteRequest;
import ca.unb.usermanagement.payload.response.MessageResponse;
import ca.unb.usermanagement.payload.response.Response;
import ca.unb.usermanagement.repository.UserRepository;
import ca.unb.usermanagement.repository.UserRoleRepository;
import ca.unb.usermanagement.security.jwt.JwtUtils;
import ca.unb.usermanagement.security.services.UserDetailsImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public ResponseEntity<?> deleteUser(DeleteRequest deleteRequest) {

        User user = userRepository.findByUsername(deleteRequest.getUsername())
            .orElse(null);

        if ( user == null) {
            return ResponseEntity.notFound().build();
            
        }
        else{
            userRepository.deleteById(user.getId());
            return ResponseEntity.ok(new Response().createMessageResponse("User deleted successfully!"));
        }
    }
}
