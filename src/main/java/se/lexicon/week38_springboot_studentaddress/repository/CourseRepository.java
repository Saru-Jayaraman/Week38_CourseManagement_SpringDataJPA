package se.lexicon.week38_springboot_studentaddress.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.week38_springboot_studentaddress.entity.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    //1.Find Course by Instructor ID
    List<Course> findByInstructors_Id(Long instructorId);

    //2.SQL query
//    @Query(value = "select * from course_details c join courses_instructors ci on c.id = ci.course_id join instructor_details i on ci.instructor_id = i.id where i.id = :instructorId", nativeQuery = true)

    //3.JPQL
    @Query("select c from courseDetails c inner join c.instructors instructors where instructors.id = :instructorId")
    List<Course> findCoursesByInstructorId(Long instructorId);

    //Find Course by Student ID
    Optional<Course> findByStudents_Id(String studentId);

    //Find all Courses ordered by Course Name
    List<Course> findAllByOrderByCourseNameAsc();

    //Find all Courses by Student's Firstname
    List<Course> findAllByStudents_FirstName(String students_firstName);

    //Find all courses with no enrolled Students
    @Query(value = "select * from course_details where id not in(select course_id from student_details)", nativeQuery = true)
    List<Course> findByStudentIsNull();
}