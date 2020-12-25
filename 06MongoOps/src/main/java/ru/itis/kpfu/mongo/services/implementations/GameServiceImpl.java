package ru.itis.kpfu.mongo.services.implementations;

import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.BsonReader;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.mongo.models.Game;
import ru.itis.kpfu.mongo.repositories.interfaces.GameDriverRepository;
import ru.itis.kpfu.mongo.repositories.interfaces.GameTemplateRepository;
import ru.itis.kpfu.mongo.services.interfaces.GameService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameDriverRepository gameDriverRepository;
    private final GameTemplateRepository gameTemplateRepository;

    @Override
    public Game saveGameByTemplate(Game game) {
        return gameTemplateRepository.save(game).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public Game saveGameByDriver(Game game) {
        return gameDriverRepository.save(game).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Game> findAll() {
        return gameTemplateRepository.findAll();
    }
}
