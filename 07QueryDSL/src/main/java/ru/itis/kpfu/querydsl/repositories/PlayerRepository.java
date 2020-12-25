package ru.itis.kpfu.querydsl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.kpfu.querydsl.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
