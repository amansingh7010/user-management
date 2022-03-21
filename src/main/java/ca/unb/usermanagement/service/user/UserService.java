package ca.unb.usermanagement.service.user;

import ca.unb.usermanagement.model.User;
import ca.unb.usermanagement.payload.request.DeleteRequest;

import java.util.List;

public interface UserService {
    void deleteUser(DeleteRequest deleteRequest);

    List<User> getUsers();
}
