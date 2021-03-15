package com.shopping.application.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private AddressDto address;
}
