package ru.itis.shop.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Integer totalPrice;
    private List<CartPurchaseDto> purchaseDtoList;
}
