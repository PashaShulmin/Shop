package ru.itis.shop.services;

import ru.itis.shop.dto.OrderDto;

import java.util.List;

public interface OrdersService {
    void makeOrder(Long accountId);
    List<OrderDto> getAccountOrders(Long accountId);
}