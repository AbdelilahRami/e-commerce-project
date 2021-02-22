package com.shopping.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddresseDto {

    private String postalCode;
    private String address;
    private String city;
}
