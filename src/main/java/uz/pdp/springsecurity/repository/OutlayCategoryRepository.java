package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.OutlayCategory;

public interface OutlayCategoryRepository extends JpaRepository<OutlayCategory,Integer> {
}
