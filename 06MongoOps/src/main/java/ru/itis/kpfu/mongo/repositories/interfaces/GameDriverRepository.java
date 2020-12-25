package ru.itis.kpfu.mongo.repositories.interfaces;

import ru.itis.kpfu.mongo.models.Game;
import ru.itis.kpfu.mongo.models.Player;

import java.util.Optional;

public interface GameDriverRepository extends CrudRepository<Game, Long>{

}
