package ru.itis.kpfu.mongo.services.interfaces;

import org.springframework.stereotype.Service;
import ru.itis.kpfu.mongo.models.Game;
import ru.itis.kpfu.mongo.models.Player;

import java.util.List;

@Service
public interface PlayerService {

    void saveGameByJPA(Player player);

    List<Player> findAllByNickname(String nickname);

    Player buy(Player player,Game game);
    Player buy(String playerId,String gameId);

}
