package se.lexicon.week38_springboot_studentaddress.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
@ToString
@Entity(name = "studentDetails")
//@Table(name = "")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@GeneratedValue(strategy = GenerationType.IDENTITY) --> AutoIncrement - Integer, Long
    @Column(updatable = false)
    private String id;

    @Column(nullable = false, length = 50)
    @Setter
    private String firstName;

    @Column(nullable = false, length = 50)
    @Setter
    private String lastName;

    @Column(nullable = false, unique = true)
    @Setter
    private String email;

    @Setter
    private boolean status;

    private LocalDateTime createdDate;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
//    @ToString.Exclude
    private Address address;

    @Setter
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn
//    @ToString.Exclude
    private Course course;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = true;
        this.createdDate = LocalDateTime.now();
    }

    public Student(String firstName, String lastName, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.status = true;
        this.createdDate = LocalDateTime.now();
    }

    public Student(String firstName, String lastName, String email, Address address, Course course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.course = course;
        this.status = true;
        this.createdDate = LocalDateTime.now();
    }
}
