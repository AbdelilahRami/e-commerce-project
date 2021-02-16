package com.shopping.application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class ProductCategory {
    @Id
    private String id;
    @Column
    private String categoryName;

    @OneToMany(mappedBy = "productCategory")
    private Set<Product> products;
}
