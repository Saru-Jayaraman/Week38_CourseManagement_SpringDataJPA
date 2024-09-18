package se.lexicon.week38_springboot_studentaddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.week38_springboot_studentaddress.entity.Address;
import se.lexicon.week38_springboot_studentaddress.entity.Student;
import se.lexicon.week38_springboot_studentaddress.repository.AddressRepository;
import se.lexicon.week38_springboot_studentaddress.repository.StudentRepository;

import java.util.Optional;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        Address savedAddress = addressRepository.save(new Address("Street1", "Malmo", "123123"));
        Student savedStudent = studentRepository.save(new Student("Emily", "Johnson", "emilyjohnson@test.se"));
        System.out.println(savedStudent);
        savedStudent.setAddress(savedAddress);
        studentRepository.save(savedStudent);
        System.out.println(savedStudent);

        Optional<Address> addressOptional = addressRepository.findById(2L);
        System.out.println(addressOptional.get());
    }
}
