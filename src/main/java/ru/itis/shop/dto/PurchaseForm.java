package ru.itis.shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseForm {
    private Long itemId;
    private Integer amount;
}
