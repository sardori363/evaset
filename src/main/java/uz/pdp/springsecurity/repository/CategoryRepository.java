package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findAllByBranch_Id(Integer branch_id);

    @Query(value = "select * from category inner join branches b on b.business_id = ?1",nativeQuery = true)
    List<Category> findAllByBusinessId(Integer businessId);
}
