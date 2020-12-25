package ru.itis.kpfu.mongo.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.SneakyThrows;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.mongo.models.Game;
import ru.itis.kpfu.mongo.repositories.interfaces.GameDriverRepository;

import java.util.Optional;

@Repository
public class GameDriverRepositoryImpl implements GameDriverRepository {

    private final MongoCollection<Document> gameCollection;
    private final ObjectMapper objectMapper;

    @Autowired
    public GameDriverRepositoryImpl(MongoDatabase mongoDatabase, ObjectMapper objectMapper) {
        this.gameCollection = mongoDatabase.getCollection("games");
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<Game> save(Game game) {

        var document = new Document();
        document.put("price", game.getPrice());
        document.put("averageRating", game.getAverageRating());
        document.put("name", game.getName());
        document.put("tags", game.getTags());

        var id = gameCollection.insertOne(document)
                .getInsertedId()
                .asObjectId()
                .toString();

        game.setId(id);

        return Optional.of(game);
    }

    @SneakyThrows
    @Override
    public Optional<Game> find(Game game) {

        var findQuery = new Document();
        findQuery
                .append("id", game.getId())
                .append("averageRating", game.getAverageRating())
                .append("name", game.getAverageRating())
                .append("price", game.getPrice())
                .append("tags", game.getTags());

        return checkIdentity(findQuery);
    }

    @SneakyThrows
    @Override
    public Optional<Game> findById(Long id) {

        var findQuery = new Document();
        findQuery
                .append("id", id);
        return checkIdentity(findQuery);
    }

    private Optional<Game> checkIdentity(Document findQuery) throws com.fasterxml.jackson.core.JsonProcessingException {
        if (gameCollection.countDocuments(findQuery) != 1)
            throw new IllegalArgumentException("Find more than one element in db");
        var docs = gameCollection.find(findQuery);

        var result = new Document();
        for (Document doc : docs) {
            result = doc;
        }

        return Optional.of(objectMapper.readValue(result.toJson(), Game.class));
    }
}
