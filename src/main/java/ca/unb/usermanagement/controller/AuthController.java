package ca.unb.usermanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.unb.usermanagement.payload.request.LoginRequest;
import ca.unb.usermanagement.payload.request.SignupRequest;
import ca.unb.usermanagement.payload.response.MessageResponse;
import ca.unb.usermanagement.repository.UserRepository;
import ca.unb.usermanagement.security.jwt.JwtUtils;
import ca.unb.usermanagement.security.services.UserDetailsImpl;
import ca.unb.usermanagement.service.auth.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;

    @Autowired
    AuthService authService;
    
    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    	ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(authService.authenticate(userDetails));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
    	ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(authService.logout());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    	 if (userRepository.existsByUsername(signUpRequest.getUsername())) {
             return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
         }
    	 
    	 if (userRepository.existsByEmail(signUpRequest.getEmail())) {
    		 return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    	 }
         
    	 return ResponseEntity.ok(authService.saveUser(signUpRequest));
    }
}
