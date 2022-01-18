package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.PaymentMethod;

public interface PayMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    boolean existsByType(String type);
}
