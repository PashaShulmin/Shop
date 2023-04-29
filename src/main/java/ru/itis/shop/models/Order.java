package ru.itis.shop.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    @Column(name = "total_price")
    private Integer totalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<Purchase> purchases;
}
