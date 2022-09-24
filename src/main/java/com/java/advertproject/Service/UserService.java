package com.java.advertproject.Service;

import com.java.advertproject.Dto.UserDto;
import com.java.advertproject.Model.User;
import com.java.advertproject.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;

    private static String DEFAULT="ROLE_USER";

    public void save(UserDto userDto){
        checkUserPass(userDto.getPassword(),userDto.getRePass());
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user=modelMapper.map(userDto,User.class);
        user.setActivity(true);
        user.setRoles(DEFAULT);
        userRepo.save(user);
    }

    public User getByEmail(String email){
        return userRepo.findByEmail(email).get();
    }

    private void checkUserPass(String pass,String rePass){
        if(!pass.equals(rePass))
            throw new RuntimeException("Passwords must be equal!");
    }
}
