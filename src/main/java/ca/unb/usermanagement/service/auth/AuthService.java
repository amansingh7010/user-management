package ca.unb.usermanagement.service.auth;

import ca.unb.usermanagement.payload.request.SignupRequest;
import ca.unb.usermanagement.payload.response.MessageResponse;
import ca.unb.usermanagement.payload.response.UserInfoResponse;
import ca.unb.usermanagement.security.services.UserDetailsImpl;

public interface AuthService {
    UserInfoResponse authenticate(UserDetailsImpl userDetails);

    MessageResponse logout();

    MessageResponse saveUser(SignupRequest signupRequest);
}
