package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.PurchaseProduct;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct,Integer> {
}
