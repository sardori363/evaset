package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.PaymentStatus;

import java.util.List;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus , Integer> {

    boolean existsByStatus(String status);

    List<PaymentStatus> findAllByBusiness_Id(Integer business_id);
}
