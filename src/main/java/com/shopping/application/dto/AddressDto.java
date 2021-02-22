package com.shopping.application.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String postalCode;
    private String address;
    private String city;
}
