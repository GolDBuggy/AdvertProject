package com.java.advertproject.Controller;

import com.java.advertproject.Model.Advert;
import com.java.advertproject.Service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @PostMapping("/create")
    public ResponseEntity<Advert> create(@RequestBody Advert advert, Principal principal){
        advertService.createAdvert(advert,principal);
        return ResponseEntity.ok(advert);
    }
}
