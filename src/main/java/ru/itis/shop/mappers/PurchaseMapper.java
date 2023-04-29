package ru.itis.shop.mappers;

import org.mapstruct.Mapper;
import ru.itis.shop.dto.PurchaseDto;
import ru.itis.shop.models.Purchase;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseDto toPurchaseDto(Purchase purchase);

    Purchase toPurchase(PurchaseDto purchaseDto);

    List<PurchaseDto> toPurchaseDtoList(List<Purchase> purchases);
}
