package ca.unb.usermanagement.service.auth;

import ca.unb.usermanagement.payload.request.LoginRequest;
import ca.unb.usermanagement.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticate(LoginRequest loginRequest);

    ResponseEntity<?> logout();

    ResponseEntity<?> registerUser(SignupRequest signupRequest);
}
