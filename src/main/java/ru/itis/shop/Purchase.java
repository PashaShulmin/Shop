package ru.itis.shop;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "purchases")
public class Purchase {
    public enum Status {
        NOT_ORDERED, ORDERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "item_id")
    private Long itemId;

    private Integer amount;

    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(value = EnumType.STRING)
    private Status status;
}
