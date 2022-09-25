package com.java.advertproject.Controller;

import com.java.advertproject.Dto.AdvertDto;
import com.java.advertproject.Model.Advert;
import com.java.advertproject.Service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @PutMapping("/update")
    public ResponseEntity<Advert> update(@RequestBody Advert advert){
        advertService.updateAdvert(advert);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(advert);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Advert>> getUserAdverts(Principal principal){
        return ResponseEntity.accepted().body(advertService.findUserAdverts(principal));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdvertDto>> getAll(@RequestParam(value = "page",defaultValue = "0")int page){
        return  ResponseEntity.ok(advertService.getAll(page));
    }
}
