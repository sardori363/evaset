package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
