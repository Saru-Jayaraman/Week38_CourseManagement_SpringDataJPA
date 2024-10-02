package se.lexicon.week38_springboot_studentaddress.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.week38_springboot_studentaddress.entity.Address;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    //Find Address by Student ID
    Optional<Address> findAddressByStudent_Id(String student_id);

    //Find Address by Course ID
    List<Address> findAddressByStudent_Course_Id(Long student_course_id);
}