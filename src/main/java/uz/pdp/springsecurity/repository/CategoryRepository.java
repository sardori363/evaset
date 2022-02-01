package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.Category;
import uz.pdp.springsecurity.entity.Trade;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findAllByBranch_Id(Integer branch_id);

    @Query(value = "select * from Trade t inner join branches b on b.business_id = ?1",nativeQuery = true)
    List<Trade> findAllByBusinessId(Integer businessId);
}
