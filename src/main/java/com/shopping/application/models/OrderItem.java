package com.shopping.application.models;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal unitPrice;
    @Column
    private Integer quantity;
    @Column
    private UUID productId;
   /* @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;*/
}
