package ru.itis.shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartPurchaseDto {
    private Long id;
    private Long buyerId;
    private ItemDto itemDto;
    private Integer price;
    private Integer amount;
    private String status;
}
