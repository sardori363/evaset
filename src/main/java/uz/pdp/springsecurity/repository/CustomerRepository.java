package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
