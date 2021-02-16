package com.shopping.application;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String productName;
    @Column
    private String description;
    @Column
    private String brandName;
    @Column
    private Double price;
    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ProductCategory productCategory;


}
