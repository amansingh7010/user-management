package ca.unb.usermanagement.service.user;

import ca.unb.usermanagement.model.User;
import ca.unb.usermanagement.payload.request.DeleteRequest;
import ca.unb.usermanagement.payload.response.Response;
import ca.unb.usermanagement.payload.response.UserInfoResponse;
import ca.unb.usermanagement.repository.UserRepository;
import ca.unb.usermanagement.repository.UserRoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
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

    @Override
    public ResponseEntity<?> getUsers() {

        List<User> users = userRepository.findAll();

        List<UserInfoResponse> responses = new ArrayList<UserInfoResponse>();

        for (User user : users) {
            responses.add(
                new UserInfoResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRoles().stream().map((r) -> r.toString()).toList())
            );
        }
        
        return ResponseEntity.ok().body(new Response().createListResponse(responses));
    }
}
