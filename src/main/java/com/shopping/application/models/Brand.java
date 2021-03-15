package com.shopping.application.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
@Setter
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String brandName;

    @OneToMany(mappedBy = "brandName", cascade = CascadeType.REMOVE)
    private Collection<Product> products;
}
