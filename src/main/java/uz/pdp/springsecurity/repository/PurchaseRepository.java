package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
