package testgenerator.bg.controllers;

import org.springframework.web.bind.annotation.*;
import testgenerator.bg.entity.dto.ProfileDTO;
import testgenerator.bg.services.UserService;

@RestController
@RequestMapping("/auth/profile")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/email={email}")
    @CrossOrigin
    public ProfileDTO getProfileInfo(@PathVariable String email){

        return userService.getProfileInfo(email);


    }



}
