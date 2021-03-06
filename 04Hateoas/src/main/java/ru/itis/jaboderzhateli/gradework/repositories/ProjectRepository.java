package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.jaboderzhateli.gradework.models.Project;

@RepositoryRestResource
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Transactional
    @RestResource(path = "byId", rel = "studentId")
    void deleteByStudent_Id(Long studentId);
}
