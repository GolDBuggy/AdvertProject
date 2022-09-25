package com.java.advertproject.Service;

import com.java.advertproject.Dto.AdvertDto;
import com.java.advertproject.Dto.AdvertsDto;
import com.java.advertproject.Model.Advert;
import com.java.advertproject.Model.Approval;
import com.java.advertproject.Model.Url;
import com.java.advertproject.Repository.AdvertRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final ApprovalService approvalService;
    private final UrlService urlService;

    private static String DEFAULT_STATUS="Passive";

    public void createAdvert(Advert advert, Principal principal){
        advert=setValues(advert,principal);
        advertRepo.save(advert);
        approvalService.save(approval(advert));
        urlService.save(new Url(),advert);
    }

    private Advert setValues(Advert advert,Principal principal){
        advert.setStatus(DEFAULT_STATUS);
        advert.setCreatedTime(LocalDate.now());
        advert.setUser(userService.getByEmail(principal.getName()));
        return advert;
    }

    private Approval approval(Advert advert){
        Approval approval=Approval.builder().advert(advert).build();
        return approval;
    }

    public void updateAdvert(Advert advert) {
        advert.setUpdatedTime(LocalDate.now());
        advertRepo.save(advert);
    }

    public List<Advert> findUserAdverts(Principal principal) {
        return advertRepo.findAdvertsByUser(userService.getByEmail(principal.getName()));
    }

    public List<AdvertsDto> getAll(int page) {
        Pageable sortedByDate = PageRequest.of(page, 10, Sort.by("createdTime").descending());
        List<Advert> adverts=advertRepo.getAll(sortedByDate);
        return adverts.stream().map(a -> modelMapper.map(a, AdvertsDto.class)).collect(Collectors.toList());
    }

    public AdvertDto getById(long id) {
        Advert advert=advertRepo.findById(id).get();
        clickCount(advert.getUrl());
        return modelMapper.map(advert, AdvertDto.class);
    }

    private void clickCount(Url url){
        url.setClickCount(url.getClickCount()+1);
        urlService.update(url);
    }
}
