package com.java.advertproject.Service;

import com.java.advertproject.Model.Advert;
import com.java.advertproject.Repository.AdvertRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepo advertRepo;
    private final UserService userService;


    public void createAdvert(Advert advert, Principal principal){
        advert.setCreatedTime(LocalDate.now());
        advert.setUser(userService.getByEmail(principal.getName()));
        advertRepo.save(advert);
    }
}
