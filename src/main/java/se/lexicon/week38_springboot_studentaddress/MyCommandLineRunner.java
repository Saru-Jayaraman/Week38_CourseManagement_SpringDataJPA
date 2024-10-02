package se.lexicon.week38_springboot_studentaddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.week38_springboot_studentaddress.entity.Address;
import se.lexicon.week38_springboot_studentaddress.entity.Course;
import se.lexicon.week38_springboot_studentaddress.entity.Instructor;
import se.lexicon.week38_springboot_studentaddress.entity.Student;
import se.lexicon.week38_springboot_studentaddress.repository.AddressRepository;
import se.lexicon.week38_springboot_studentaddress.repository.CourseRepository;
import se.lexicon.week38_springboot_studentaddress.repository.InstructorRepository;
import se.lexicon.week38_springboot_studentaddress.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final InstructorRepository instructorRepository;

    private final AddressRepository addressRepository;

    @Autowired
    public MyCommandLineRunner(StudentRepository studentRepository, CourseRepository courseRepository, InstructorRepository instructorRepository, AddressRepository addressRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Address address1 = new Address("Street1", "Malmo", "123123");
        Address address2 = new Address("Street2", "Malmo", "123128");
        Course course1 = new Course("Java Developer");
        Course course2 = new Course(".Net Developer");
        Course course3 = new Course("Python");
        courseRepository.save(course3);
        Student savedStudent1 = studentRepository.save(new Student("Emily", "Johnson", "emilyjohnson@test.se", address1, course1));
        Student savedStudent2 = studentRepository.save(new Student("Harry", "Potter", "harrypotter@test.se", address2, course2));
        Instructor instructor1 = new Instructor("Negar");
        Instructor instructor2 = new Instructor("Mehrdad");

        // Many to Many - Course & Instructor
        Instructor savedInstructor1 = instructorRepository.save(instructor1);
        Instructor savedInstructor2 = instructorRepository.save(instructor2);
        // Add and remove in Course and Instructor
        course1.addInstructor(savedInstructor1);
        course1.addInstructor(savedInstructor2);
        course2.addInstructor(savedInstructor1);
//        course1.removeInstructor(savedInstructor2);
//        savedInstructor1.removeCourse(course1);

        System.out.println("------------------------------STUDENT REPOSITORY------------------------------");
        System.out.println("----------------------------------FIND BY ID----------------------------------");
        Optional<Student> studentById = studentRepository.findById(savedStudent1.getId());
        studentById.ifPresent(System.out::println);
        studentById.ifPresent(student -> System.out.println(student.getAddress().toString()));//Lazy load - Used getter to read later
        studentById.ifPresent(student -> System.out.println(student.getCourse().toString()));//Lazy load - Used getter to read later
        System.out.println();

        System.out.println("------------------------------FIND BY FIRST NAME-------------------------------");
        List<Student> studentByFirstName = studentRepository.findByFirstName(savedStudent1.getFirstName());
        studentByFirstName.forEach(System.out::println);
        System.out.println();

        System.out.println("-------------------------------FIND BY FULL NAME-------------------------------");
        List<Student> studentByFullName = studentRepository.findStudentByFullName(savedStudent1.getFirstName(), savedStudent1.getLastName());
        studentByFullName.forEach(System.out::println);
        System.out.println();

        System.out.println("-------------------------FIND BY FIRST NAME CONTAINING-------------------------");
        List<Student> studentByFirstNameContains = studentRepository.findByFirstNameContaining(savedStudent1.getFirstName());
        studentByFirstNameContains.forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------------FIND BY CREATED DATE AFTER---------------------------");
        List<Student> studentByAfterCreatedDate = studentRepository.findByCreatedDateAfter(LocalDateTime.now().minusDays(60));
        studentByAfterCreatedDate.forEach(System.out::println);
        System.out.println();

        System.out.println("------------------------------FIND BY STATUS TRUE------------------------------");
        List<Student> studentByStatusTrue = studentRepository.findByStatusTrue();
        studentByStatusTrue.forEach(System.out::println);
        System.out.println();

        System.out.println("---------------------------FIND BY EMAIL IGNORE CASE---------------------------");
        Optional<Student> studentByEmailIgnoreCase = studentRepository.findByEmailIgnoreCase(savedStudent1.getEmail().toUpperCase());
        studentByEmailIgnoreCase.ifPresent(System.out::println);
        System.out.println();

        System.out.println("------------------------------FIND BY COURSE NAME------------------------------");
        List<Student> studentByCourseName = studentRepository.findAllByCourse_CourseName(savedStudent1.getCourse().getCourseName());
        studentByCourseName.forEach(System.out::println);
        System.out.println();

        System.out.println("-------------------------------FIND BY COURSE ID-------------------------------");
        List<Student> studentByCourseId = studentRepository.findAllByCourse_Id(savedStudent1.getCourse().getId());
        studentByCourseId.forEach(System.out::println);
        System.out.println();

        System.out.println("-----------------------------UPDATE STATUS TO TRUE-----------------------------");
        studentRepository.updateStudentStatusToTrue(savedStudent2.getId());
        System.out.println(savedStudent2);
        System.out.println();

        System.out.println("-------------------------------COURSE REPOSITORY-------------------------------");
        System.out.println("-----------------------------FIND BY INSTRUCTOR ID-----------------------------");
        List<Course> courseByInstructorId = courseRepository.findByInstructors_Id(savedInstructor1.getId());
        courseByInstructorId.forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------------FIND BY INSTRUCTOR ID JPQL---------------------------");
        List<Course> courseByInstructorIdJPQL = courseRepository.findCoursesByInstructorId(savedInstructor1.getId());
        courseByInstructorIdJPQL.forEach(System.out::println);
        System.out.println();


        System.out.println("------------------------------FIND BY STUDENT ID-------------------------------");
        Optional<Course> courseByStudent = courseRepository.findByStudents_Id(savedStudent1.getId());
        courseByStudent.ifPresent(System.out::println);
        System.out.println();

        System.out.println("-----------------------------FIND BY COURSE NAME ASC-----------------------------");
        List<Course> courseByOrderByCourseName = courseRepository.findAllByOrderByCourseNameAsc();
        courseByOrderByCourseName.forEach(System.out::println);
        System.out.println();

        System.out.println("---------------------------FIND BY STUDENTS FIRST NAME---------------------------");
        List<Course> courseByStudentsFirstName = courseRepository.findAllByStudents_FirstName(savedStudent1.getFirstName());
        courseByStudentsFirstName.forEach(System.out::println);
        System.out.println();

        System.out.println("-----------------------------FIND BY STUDENT IS NULL-----------------------------");
        List<Course> courseByStudentNull = courseRepository.findByStudentIsNull();
        courseByStudentNull.forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------------------ADDRESS REPOSITORY-------------------------------");
        System.out.println("--------------------------------FIND BY STUDENT ID-------------------------------");
        Optional<Address> addressByStudentId = addressRepository.findAddressByStudent_Id(savedStudent1.getId());
        addressByStudentId.ifPresent(System.out::println);
        System.out.println();

        System.out.println("--------------------------------FIND BY COURSE ID--------------------------------");
        List<Address> addressByCourseId = addressRepository.findAddressByStudent_Course_Id(course1.getId());
        addressByCourseId.forEach(System.out::println);
        System.out.println();

        System.out.println("-------------------------------INSTRUCTOR REPOSITORY-----------------------------");
        System.out.println("--------------------------------FIND BY STUDENT ID-------------------------------");
        List<Instructor> instructorsByStudentId = instructorRepository.findAllByStudentId(savedStudent2.getId());
        instructorsByStudentId.forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------------------FIND BY COURSE ID--------------------------------");
        List<Instructor> instructorsByCourseId = instructorRepository.findAllByCourseId(course2.getId());
        instructorsByCourseId.forEach(System.out::println);
        System.out.println();
    }
}