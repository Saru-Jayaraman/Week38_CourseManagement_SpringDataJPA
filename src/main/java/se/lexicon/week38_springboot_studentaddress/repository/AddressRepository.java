package se.lexicon.week38_springboot_studentaddress.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.week38_springboot_studentaddress.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
