package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.Institute;

import java.util.Optional;

@RepositoryRestResource
public interface InstituteRepository extends JpaRepository<Institute, Long> {

    @RestResource(path = "byName", rel = "instituteName")
    Optional<Institute> findByName(String name);

}
