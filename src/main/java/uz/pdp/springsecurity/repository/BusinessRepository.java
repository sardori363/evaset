package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Business;

public interface BusinessRepository extends JpaRepository<Business,Integer> {

    boolean existsByName(String name);
}
