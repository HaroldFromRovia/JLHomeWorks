package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.jaboderzhateli.gradework.models.Faculty;

import java.util.Optional;

@RepositoryRestResource
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @RestResource(path = "byName", rel = "facultyName")
    Optional<Faculty> findByName(String name);

}
