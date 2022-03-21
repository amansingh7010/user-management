package ca.unb.usermanagement.service.auth;

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
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new Response().createUserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @Override
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new Response().createMessageResponse("Logout success!"));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
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
        return ResponseEntity.ok(new Response().createMessageResponse("User registered successfully!"));
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
