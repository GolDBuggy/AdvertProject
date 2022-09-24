package com.java.advertproject.Service;

import com.java.advertproject.Model.User;
import com.java.advertproject.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    public void save(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setActivity(true);
        userRepo.save(user);
    }
}
