package ru.itis.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shop.Order;
import ru.itis.shop.Purchase;

import java.util.List;

public interface PurchasesRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByBuyerId(Long buyerId);
    Purchase findByBuyerIdAndItemId(Long buyerId, Long itemId);
    List<Purchase> findAllByOrder(Order order);
    Purchase findByBuyerIdAndItemIdAndStatusEquals(Long buyerId, Long itemId, Enum status);

}
