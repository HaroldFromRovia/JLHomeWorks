package ru.itis.kpfu.mongo.services.interfaces;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.mongo.models.Game;

import java.util.List;

@Service
public interface GameService {

    Game saveGameByTemplate(Game game);
    Game saveGameByDriver(Game game);
    List<Game> findAll();

}
