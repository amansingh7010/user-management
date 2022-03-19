package ca.unb.usermanagement.service.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ca.unb.usermanagement.model.EUserRole;
import ca.unb.usermanagement.model.User;
import ca.unb.usermanagement.model.UserRole;
import ca.unb.usermanagement.model.UserRegistry;
import ca.unb.usermanagement.payload.request.LoginRequest;
import ca.unb.usermanagement.payload.request.SignupRequest;
import ca.unb.usermanagement.payload.response.MessageResponse;
import ca.unb.usermanagement.payload.response.Response;
import ca.unb.usermanagement.security.jwt.JwtUtils;
import ca.unb.usermanagement.security.services.UserDetailsImpl;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;

	@Override
	public ResponseEntity<?> authenticate(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
			      .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			  SecurityContextHolder.getContext().setAuthentication(authentication);
			  UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			  ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
			  List<String> roles = userDetails.getAuthorities().stream()
			      .map(item -> item.getAuthority())
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
		UserRegistry userRegistry = UserRegistry.getInstance();

		if (userRegistry.existsByUsername(signUpRequest.getUsername())) {
		    return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		  }
		  if (userRegistry.existsByEmail(signUpRequest.getEmail())) {
		    return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		  }
		  // Create new user's account
		  User user = new User(signUpRequest.getUsername(),
		                       signUpRequest.getEmail(),
		                       encoder.encode(signUpRequest.getPassword()));
		  Set<String> strRoles = signUpRequest.getRole();
		  Set<UserRole> roles = new HashSet<>();
		  if (strRoles == null) {
			  UserRole userRole = new UserRole(EUserRole.ROLE_USER);
		    roles.add(userRole);
		  } else {
		    strRoles.forEach(role -> {
		      switch (role) {
		      case "admin":
		    	UserRole adminRole = new UserRole(EUserRole.ROLE_ADMIN);
		        roles.add(adminRole);
		        break;
		      case "mod":
		    	  UserRole modRole = new UserRole(EUserRole.ROLE_MODERATOR);
		        roles.add(modRole);
		        break;
		      default:
		    	  UserRole userRole = new UserRole(EUserRole.ROLE_USER);
		        roles.add(userRole);
		      }
		    });
		  }
		  user.setRoles(roles);
		  userRegistry.addUser(user);
		  return ResponseEntity.ok(new Response().createMessageResponse("User registered successfully!"));
	}

}
