package ru.itis.kpfu.mongo.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.mongo.models.Game;
import ru.itis.kpfu.mongo.repositories.interfaces.GameTemplateRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
@Repository
public class GameTemplateRepositoryImpl implements GameTemplateRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<Game> save(Game game) {
        return Optional.of(mongoTemplate.save(game));
    }

    @Override
    public Optional<Game> find(Game game) {
        return Optional.empty();
    }

    @Override
    public Optional<Game> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id,Game.class));
    }

    @Override
    public List<Game> findByTagWherePriceLessOrRatingMore(String tag, Double price, Double rating) {
        return mongoTemplate.find(new Query(
                where("tags").is(tag)
                        .andOperator(
                                where("price").lt(price)
                                        .orOperator(where("rating").gt(rating))
                        )), Game.class, "games");
    }

    @Override
    public List<Game> findAll() {
        return mongoTemplate.findAll(Game.class);
    }
}
