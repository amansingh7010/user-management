package ca.unb.usermanagement.service;

import org.springframework.http.ResponseEntity;

import ca.unb.usermanagement.payload.request.LoginRequest;
import ca.unb.usermanagement.payload.request.SignupRequest;

public interface AuthService {
	
	ResponseEntity<?> authenticate(LoginRequest loginRequest);
	ResponseEntity<?> logout();
	ResponseEntity<?> registerUser(SignupRequest signupRequest);
}
