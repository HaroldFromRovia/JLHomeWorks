package ru.itis.kpfu.mongo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.kpfu.mongo.models.Game;
import ru.itis.kpfu.mongo.models.Player;
import ru.itis.kpfu.mongo.services.interfaces.GameService;
import ru.itis.kpfu.mongo.services.interfaces.PlayerService;

import java.util.*;

@Controller
@Slf4j
public class DefaultController {

    private final GameService gameService;
    private final PlayerService playerService;
    private final List<Game> gameList;
    private final List<Player> playerList;

    public DefaultController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.playerList = new ArrayList<>();
        this.gameList = new ArrayList<>();

        var tags1 = new ArrayList<>(Arrays.asList("FPS", "GOTY"));
        var tags2 = new ArrayList<>(Arrays.asList("Hard as fuck", "GOTY"));
        var tags3 = new ArrayList<>(Arrays.asList("Cyberpunk", "GOTY"));

        var game1 = Game.builder()
                .averageRating(7.0)
                .price(1399.0)
                .tags(tags1)
                .name("Cyberpunk 2049")
                .build();

        var game2 = Game.builder()
                .averageRating(9.5)
                .price(1599.0)
                .tags(tags2)
                .name("Sekiro Shadows Die Twice")
                .build();

        var game3 = Game.builder()
                .averageRating(9.3)
                .price(1999.5)
                .tags(tags3)
                .name("Ghostrunner")
                .build();

        gameList.add(game1);
        gameList.add(game2);
        gameList.add(game3);

        var player1 = Player.builder()
                .nickname("UristMcEmhead")
                .games(gameList)
                .login("Bentos")
                .build();

        var player2 = Player.builder()
                .nickname("A_Platypus_F")
                .games(Collections.singletonList(game3))
                .login("linar240001")
                .build();

        playerList.add(player1);
        playerList.add(player2);
    }

    @GetMapping("/save/games")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Game>> saveGames() {
        log.info("Saving games" + gameList.get(0).toString() + " via MongoDriver");
        gameService.saveGameByDriver(gameList.get(0));

        log.info("Saving games" + gameList.get(1).toString() + " via MongoTemplate");
        gameList.set(1, gameService.saveGameByTemplate(gameList.get(1)));
        log.info("Saving games" + gameList.get(2).toString() + " via MongoTemplate");
        gameList.set(2, gameService.saveGameByTemplate(gameList.get(2)));

        return ResponseEntity.of(Optional.of(gameList));
    }

    @GetMapping("/save/players")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Player>> savePlayers() {
        playerService.saveGameByJPA(playerList.get(0));
        log.info("Saving player " + playerList.get(0) + " via JPA");
        playerService.saveGameByJPA(playerList.get(1));
        log.info("Saving player " + playerList.get(1) + " via JPA");

        return ResponseEntity.of(Optional.of(playerList));
    }
}
