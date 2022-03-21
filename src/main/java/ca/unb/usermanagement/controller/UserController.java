package ca.unb.usermanagement.controller;

import ca.unb.usermanagement.payload.request.DeleteRequest;
import ca.unb.usermanagement.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteRequest deleteRequest) {
        return userService.deleteUser(deleteRequest);
    }

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        return userService.getUsers();
    }
}
