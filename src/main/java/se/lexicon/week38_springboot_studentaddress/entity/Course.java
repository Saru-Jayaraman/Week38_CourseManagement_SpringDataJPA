package se.lexicon.week38_springboot_studentaddress.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "students")
@Entity(name = "courseDetails")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100)
    private String courseName;

    @Setter
    @OneToMany(mappedBy = "course")
    private Set<Student> students = new HashSet<>();

    @Setter
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @ToString.Exclude
    @JoinTable(
            name = "courses_instructors",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private Set<Instructor> instructors = new HashSet<>();

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setCourse(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setCourse(null);
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
        Set<Course> courses = instructor.getCourses();
        courses.add(this);
        instructor.setCourses(courses);
    }

    public void removeInstructor(Instructor instructor) {
        instructors.remove(instructor);
        Set<Course> courses = instructor.getCourses();
        courses.remove(this);
        instructor.setCourses(courses);
    }
}
