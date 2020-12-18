package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.Competence;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    @RestResource(path = "byName", rel="competenceName")
    Optional<Competence> findByName(String name);

    @RestResource(path = "byId", rel="competenceId")
    List<Competence> findAllByIdIn(List<Long> ids);

}
