package com.java.advertproject.Controller;

import com.java.advertproject.Model.User;
import com.java.advertproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }
}
