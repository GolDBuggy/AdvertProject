package com.java.advertproject.Service;

import com.java.advertproject.Dto.AdvertDto;
import com.java.advertproject.Model.Advert;
import com.java.advertproject.Repository.AdvertRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepo advertRepo;
    private final UserService userService;
    private final ModelMapper modelMapper;

    private static String DEFAULT_STATUS="Passive";


    public void createAdvert(Advert advert, Principal principal){
        advert.setStatus(DEFAULT_STATUS);
        advert.setCreatedTime(LocalDate.now());
        advert.setUser(userService.getByEmail(principal.getName()));
        advertRepo.save(advert);
    }

    public void updateAdvert(Advert advert) {
        advert.setUpdatedTime(LocalDate.now());
        advertRepo.save(advert);
    }

    public List<Advert> findUserAdverts(Principal principal) {
        return advertRepo.findAdvertsByUser(userService.getByEmail(principal.getName()));
    }

    public List<AdvertDto> getAll() {
        return advertRepo.findAll().stream().map(a -> modelMapper.map(a,AdvertDto.class)).collect(Collectors.toList());
    }
}
