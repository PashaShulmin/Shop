package ru.itis.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.shop.dto.OrderDto;
import ru.itis.shop.Order;
import ru.itis.shop.Purchase;
import ru.itis.shop.repositories.OrdersRepository;
import ru.itis.shop.repositories.PurchasesRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final PurchasesRepository purchasesRepository;
    private final OrdersRepository ordersRepository;
    private final PurchasesService purchasesService;

    @Transactional
    public void makeOrder(Long accountId) {
        List<Purchase> allPurchases = purchasesRepository.findAllByBuyerId(accountId);
        List<Purchase> purchases = new ArrayList<>();
        for (Purchase purchase : allPurchases){
            if (purchase.getStatus().equals(Purchase.Status.NOT_ORDERED)) {
                purchases.add(purchase);
            }
        }
        Integer totalPrice = purchases.stream().mapToInt(Purchase::getPrice).sum();

        Order newOrder = Order.builder()
                .totalPrice(totalPrice)
                .accountId(accountId)
                .purchases(purchases)
                .build();

        ordersRepository.save(newOrder);

        for (Purchase purchase : purchases) {
            purchase.setStatus(Purchase.Status.ORDERED);
            purchase.setOrder(newOrder);
            purchasesRepository.save(purchase);
        }
    }

    public List<OrderDto> getAccountOrders(Long accountId) {
        List<Order> orders = ordersRepository.findAllByAccountId(accountId);
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderDto orderDto = OrderDto.builder()
                    .id(order.getId())
                    .totalPrice(order.getTotalPrice())
                    .purchaseDtoList(purchasesService.getAllCartPurchaseDtosByOrder(order))
                    .build();

            orderDtos.add(orderDto);
        }

        return orderDtos;
    }
}

