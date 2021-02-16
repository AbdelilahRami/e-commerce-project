package com.shopping.application;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product-category")

public class ProductCategory {
    @Id
    private String id;
    @Column
    private String categoryName;

    @OneToMany(mappedBy = "productCategory")
    private Set<Product> products;
}
