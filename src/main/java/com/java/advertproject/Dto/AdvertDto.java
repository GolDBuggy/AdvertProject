package com.java.advertproject.Dto;

import com.java.advertproject.Model.Report;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertDto {

    private String title;
    private String description;
    private BigDecimal price;
    private UserNameDto user;
    private Date createdTime;
    private UrlDto url;
    private Report report;
}
