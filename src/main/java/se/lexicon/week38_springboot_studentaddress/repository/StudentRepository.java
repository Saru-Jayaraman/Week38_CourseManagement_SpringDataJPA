package se.lexicon.week38_springboot_studentaddress.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.week38_springboot_studentaddress.entity.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    //1. select * from student where first_name = ?
    List<Student> findByFirstName(String firstName);

    //2.JPQL query
    @Query("select s from studentDetails s where s.firstName = :firstName and s.lastName = :lastName")
    List<Student> findStudentByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    //Find Students by their name containing a certain Name
    List<Student> findByFirstNameContaining(String name);

    //Find Student by creation date after a certain date
    List<Student> findByCreatedDateAfter(LocalDateTime createDate);

    //Find Students by status true
    //select * from student where status = true;
    List<Student> findByStatusTrue();

    //Find Student by Email (case-sensitive)
    Optional<Student> findByEmailIgnoreCase(String email);

    //Update the Student status to true by Student ID
    @Modifying
    @Query("update studentDetails st set st.status = true where st.id = :studentId")
    void updateStudentStatusToTrue(String studentId);

    //Find Students by Course ID
    List<Student> findAllByCourse_Id(Long courseId);

    //Find Students by the Course Name
    List<Student> findAllByCourse_CourseName(String courseName);
}