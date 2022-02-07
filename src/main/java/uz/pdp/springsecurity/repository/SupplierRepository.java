package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    @Query(value = "select * from supplier s where s.business_id =1",nativeQuery = true)
    List<Supplier> findAllByBusinessId(Integer businessId);
}
