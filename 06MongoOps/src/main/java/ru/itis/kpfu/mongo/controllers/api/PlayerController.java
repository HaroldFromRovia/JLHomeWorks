package ru.itis.kpfu.mongo.controllers.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.kpfu.mongo.services.interfaces.PlayerService;

@RepositoryRestController
@Controller
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @RequestMapping(value = "/players/{playerId}/buy/{gameId}", method = {RequestMethod.PUT, RequestMethod.GET})
    public ResponseEntity<?> buy(@PathVariable("playerId") String playerId, @PathVariable("gameId") String gameId) {
        return ResponseEntity.ok(EntityModel.of(playerService.buy(playerId, gameId)));
    }
}
