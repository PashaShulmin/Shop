package ru.itis.shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDto {
    private Long buyerId;
    private Long itemId;
    private Integer price;
    private Integer amount;
}
