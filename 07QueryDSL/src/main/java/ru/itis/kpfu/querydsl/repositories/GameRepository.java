package ru.itis.kpfu.querydsl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.kpfu.querydsl.models.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
