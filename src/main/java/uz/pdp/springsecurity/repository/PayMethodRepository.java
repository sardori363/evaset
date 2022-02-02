package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.PaymentMethod;

import java.util.List;

public interface PayMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    boolean existsByType(String type);

    List<PaymentMethod> findAllByBranch_Id(Integer branch_id);

    List<PaymentMethod> findAllByBranch_Business_Id(Integer business_id);
}
