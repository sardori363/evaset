package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
