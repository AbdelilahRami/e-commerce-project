package com.shopping.application.models;

import lombok.*;
import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String password;
    @Column
    private String phone;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    private Set<Product> products;

}
