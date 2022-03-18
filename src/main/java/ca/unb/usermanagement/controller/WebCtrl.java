package ca.unb.usermanagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class WebCtrl {

    @GetMapping({"/signup"})
    public String register() {
        return "signup";
    }

    @GetMapping({"/signin"})
    public String login() {
        return "signin";
    }

    @GetMapping({"/reports"})
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String reports() {
        return "reports";
    }

}
