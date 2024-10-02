package se.lexicon.week38_springboot_studentaddress.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.week38_springboot_studentaddress.entity.Instructor;

import java.util.List;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
    //Find Instructors by Course ID
//    @Query(value = "select * from instructor_details i join courses_instructors ci on i.id = ci.instructor_id
//    join course_details c on c.id = ci.course_id where c.id = 1;", nativeQuery = true)
    @Query("select i from instructorDetails i join i.courses ci where ci.id = :courseId")
    List<Instructor> findAllByCourseId(Long courseId);

    //Find Instructors by Student Id
//    @Query(value = "select * from instructor_details i join courses_instructors ci on i.id = ci.instructor_id\n" +
//            "    join course_details c on c.id = ci.course_id where ci.course_id in\n" +
//            "    (select s.course_id from student_details s where s.id = :studentId)", nativeQuery = true)
    @Query("select i from instructorDetails i join i.courses ci join ci.students s where s.id = :studentId")
    List<Instructor> findAllByStudentId(String studentId);
}