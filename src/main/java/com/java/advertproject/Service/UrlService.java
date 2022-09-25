package com.java.advertproject.Service;

import com.java.advertproject.Model.Advert;
import com.java.advertproject.Model.Url;
import com.java.advertproject.Repository.UrlRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepo urlRepo;
    private static String DEFAULT_LINK="http://localhost:8080/advert/";


    public void save(Url url, Advert advert){
        url.setAdvert(advert);
        url.setAdvertUrl(DEFAULT_LINK+advert.getId());
        urlRepo.save(url);
    }


    public void update(Url url) {
        urlRepo.save(url);
    }
}
