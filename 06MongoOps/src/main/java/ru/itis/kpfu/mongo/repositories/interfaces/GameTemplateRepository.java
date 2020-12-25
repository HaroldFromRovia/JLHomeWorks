package ru.itis.kpfu.mongo.repositories.interfaces;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.kpfu.mongo.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameTemplateRepository extends CrudRepository<Game, String>{

    List<Game> findByTagWherePriceLessOrRatingMore(String name, Double price, Double rating);
    List<Game> findAll();
}
