package se.lexicon.week38_springboot_studentaddress.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "courses")
@Entity(name = "instructorDetails")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String instructorName;

    @Setter
    @ManyToMany(mappedBy = "instructors")
    private Set<Course> courses = new HashSet<>();
    public Instructor(String instructorName) {
        this.instructorName = instructorName;
    }

    public void addCourse(Course course) {
        courses.add(course);
        Set<Instructor> instructors = course.getInstructors();
        instructors.add(this);
        course.setInstructors(instructors);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        Set<Instructor> instructors = course.getInstructors();
        instructors.remove(this);
        course.setInstructors(instructors);
    }
}
