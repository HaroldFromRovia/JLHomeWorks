package ru.itis.kpfu.querydsl.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.kpfu.querydsl.models.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
