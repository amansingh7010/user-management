package ca.unb.usermanagement.service.user;

import ca.unb.usermanagement.payload.request.DeleteRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> deleteUser(DeleteRequest deleteRequest);

    ResponseEntity<?> getUsers();
}
