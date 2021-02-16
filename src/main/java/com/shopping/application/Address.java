package com.shopping.application;


import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String id;
    @Column
    private String postalCode;
    @Column
    private String address;

}
