package se.lexicon.week38_springboot_studentaddress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.week38_springboot_studentaddress.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

}
