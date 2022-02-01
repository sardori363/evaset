package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.OutlayCategory;

import java.util.List;

public interface OutlayCategoryRepository extends JpaRepository<OutlayCategory,Integer> {
    List<OutlayCategory> findAllByBranch_Id(Integer branch_id);

    @Query(value = "select * from outlay_category inner join branches b on b.business_id = ?1",nativeQuery = true)
    List<OutlayCategory> findAllByBusinessId(Integer businessId);
}
