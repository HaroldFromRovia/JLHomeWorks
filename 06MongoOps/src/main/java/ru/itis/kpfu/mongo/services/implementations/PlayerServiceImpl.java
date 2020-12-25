package ru.itis.kpfu.mongo.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.mongo.models.Game;
import ru.itis.kpfu.mongo.models.Player;
import ru.itis.kpfu.mongo.repositories.interfaces.GameTemplateRepository;
import ru.itis.kpfu.mongo.repositories.interfaces.PlayerRepository;
import ru.itis.kpfu.mongo.services.interfaces.PlayerService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final GameTemplateRepository gameTemplateRepository;

    @Override
    public void saveGameByJPA(Player player) {
        playerRepository.save(player);
    }

    @Override
    public List<Player> findAllByNickname(String nickname) {
        return playerRepository.findAllByNickname(nickname);
    }

    @Override
    public Player buy(Player player, Game gameToBuy) {

        var optionalGame = gameTemplateRepository.find(gameToBuy);
        var game = optionalGame.orElseThrow(IllegalArgumentException::new);

        player.getGames().add(game);
        return playerRepository.save(player);
    }

    @Override
    public Player buy(String playerId, String gameId) {
        var optionalGame = gameTemplateRepository.findById(gameId);
        var game = optionalGame.orElseThrow(IllegalArgumentException::new);
        var optionalPlayer = playerRepository.findById(playerId);

        var player = optionalPlayer.orElseThrow(IllegalArgumentException::new);
        player.getGames().add(game);

        return playerRepository.save(player);
    }

}
