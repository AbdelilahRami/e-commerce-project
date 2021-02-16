package com.shopping.application;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

    @Id
    private String id;
    @Column
    private String postalCode;

    @Column
    private String address;

    @OneToOne(mappedBy = "address")
    private User user;


}
