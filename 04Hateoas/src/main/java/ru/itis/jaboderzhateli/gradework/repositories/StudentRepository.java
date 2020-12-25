package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.*;

import java.util.List;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByCompetencesIn(List<StudentCompetence> competences);

    List<Student> findAllByCompetencesInAndInstituteId(List<Competence> competences, Long instituteId);

    List<Student> findAllByCompetencesInAndFacultyId(List<Competence> competences, Long facultyId);

    List<Student> findAllByCompetencesInAndInstituteIdAndFacultyId(List<Competence> competences, Long instituteId, Long facultyId);

    List<Student> findAllByInstituteIdAndFacultyId(Long instituteId, Long facultyId);

    @RestResource(path = "byInstitute", rel = "instituteId")
    List<Student> findAllByInstituteId(Long instituteId);

    @RestResource(path = "byInstitutePaged", rel = "instituteId")
    Page<Student> findAllByInstituteId(Long institute_id, Pageable pageable);

    @RestResource(path = "byFaculty", rel = "facultyId")
    List<Student> findAllByFacultyId(Long facultyId);
}
