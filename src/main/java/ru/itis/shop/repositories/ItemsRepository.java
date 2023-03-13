package ru.itis.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shop.Item;

public interface ItemsRepository extends JpaRepository<Item, Long> {
}
