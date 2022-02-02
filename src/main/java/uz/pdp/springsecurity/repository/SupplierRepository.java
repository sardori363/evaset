package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    List<Supplier> findAllByBranch_Id(Integer branch_id);

    @Query(value = "select * from supplier inner join branches b on b.business_id = ?1",nativeQuery = true)
    List<Supplier> findAllByBusinessId(Integer businessId);
}
