package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.PaymentStatus;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus , Integer> {

    boolean existsByStatus(String status);
}
