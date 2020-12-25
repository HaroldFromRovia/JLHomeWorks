package ru.itis.kpfu.mongo.repositories.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.kpfu.mongo.models.Player;

import java.util.List;

@RepositoryRestResource
public interface PlayerRepository extends MongoRepository<Player, String> {

    @RestResource
    List<Player> findAllByNickname(String nickname);



}
