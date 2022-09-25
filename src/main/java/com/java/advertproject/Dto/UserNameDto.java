package com.java.advertproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNameDto {
    private String firstName;
    private String lastName;
    private String phone;
}
