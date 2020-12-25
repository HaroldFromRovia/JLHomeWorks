package ru.itis.kpfu.mongo.repositories.interfaces;

import ru.itis.kpfu.mongo.models.Game;

import java.util.Optional;

public interface CrudRepository<T,E> {

    Optional<T> save(T object);

    Optional<T> find(T object);
    Optional<T> findById(E id);

}
