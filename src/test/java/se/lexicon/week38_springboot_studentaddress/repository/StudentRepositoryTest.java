package se.lexicon.week38_springboot_studentaddress.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.week38_springboot_studentaddress.entity.Student;

import java.util.Optional;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    @Transactional
    public void testSaveAndFindById() {
        Student student = new Student("Emily", "Johnson", "emilyjohnson@test.se");
        Student savedStudent = studentRepository.save(student);
        Assertions.assertNotNull(savedStudent);
        Assertions.assertNotNull(savedStudent.getId());

        Optional<Student> studentOptional = studentRepository.findById(savedStudent.getId());
        Assertions.assertTrue(studentOptional.isPresent());
    }
}
