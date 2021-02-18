package com.shopping.application.models;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    @Column
    private String postalCode;
    @Column
    private String address;
    @Column
    private String city;

}
