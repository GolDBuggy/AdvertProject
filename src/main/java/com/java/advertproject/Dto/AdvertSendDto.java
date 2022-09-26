package com.java.advertproject.Dto;

import com.java.advertproject.Model.Advert;
import com.java.advertproject.Model.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertSendDto {
    private long id;
    private String title;
    private String description;
    private BigDecimal price;
    private UserMailDto user;
    private Date createdTime;
    private UrlDto url;
    private Report report;
}
