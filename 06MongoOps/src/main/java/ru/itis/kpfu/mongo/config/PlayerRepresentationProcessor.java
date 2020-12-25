package ru.itis.kpfu.mongo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.mongo.controllers.api.PlayerController;
import ru.itis.kpfu.mongo.models.Game;
import ru.itis.kpfu.mongo.models.Player;
import ru.itis.kpfu.mongo.services.interfaces.GameService;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class PlayerRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Player>> {

    private final GameService gameService;

    @Override
    public EntityModel<Player> process(EntityModel<Player> model) {

        var player = model.getContent();
        var allGames = gameService.findAll();
        var differences = allGames
                .stream()
                .filter(game -> !player.getGames().contains(game))
                .collect(Collectors.toList());

        for (Game notPurchasedGame : differences) {
            model.add(linkTo(methodOn(PlayerController.class)
                    .buy(player.getId(), notPurchasedGame.getId())).withRel("buy"));
        }

        return model;
    }
}
