package com.shopping.application.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(length = 16)
    private UUID id;
    @Column
    private String productName;
    @Column
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Brand brandName;
    @Column
    private Double price;
    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ProductCategory productCategory;


}
