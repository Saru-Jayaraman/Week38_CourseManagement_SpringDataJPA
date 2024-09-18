package se.lexicon.week38_springboot_studentaddress.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "createdDate")

@Entity(name = "studentDetails")
//@Table(name = "")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@GeneratedValue(strategy = GenerationType.IDENTITY) --> AutoIncrement - Integer, Long
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
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;

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
}
