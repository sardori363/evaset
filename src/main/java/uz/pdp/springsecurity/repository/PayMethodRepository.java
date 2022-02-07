package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.PaymentMethod;

import java.util.List;

public interface PayMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    boolean existsByType(String type);

    List<PaymentMethod> findAllByBusiness_Id(Integer business_id);
}
