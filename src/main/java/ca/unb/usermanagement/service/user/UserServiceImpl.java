package ca.unb.usermanagement.service.user;

import ca.unb.usermanagement.model.User;
import ca.unb.usermanagement.payload.request.DeleteRequest;
import ca.unb.usermanagement.repository.UserRepository;
import ca.unb.usermanagement.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(DeleteRequest deleteRequest) {

        User user = userRepository.findByUsername(deleteRequest.getUsername()).orElse(null);
        if (user == null) {
            throw new NoSuchElementException();
        }
        userRepository.deleteById(user.getId());
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
