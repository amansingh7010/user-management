package ca.unb.usermanagement.controller;

import ca.unb.usermanagement.model.User;
import ca.unb.usermanagement.payload.request.DeleteRequest;
import ca.unb.usermanagement.payload.response.Response;
import ca.unb.usermanagement.payload.response.UserInfoResponse;
import ca.unb.usermanagement.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteRequest deleteRequest) {
        
        try {
            userService.deleteUser(deleteRequest);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(new Response().createMessageResponse("User deleted successfully!"));
    }

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        
        List<User> users = userService.getUsers();

        List<UserInfoResponse> responses = new ArrayList<UserInfoResponse>();

        for (User user : users) {
            responses.add(
                new UserInfoResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRoles().stream().map((r) -> r.getName().toString()).toList())
            );
        }
        
        return ResponseEntity.ok().body(new Response().createListResponse(responses)); 
    }
}
