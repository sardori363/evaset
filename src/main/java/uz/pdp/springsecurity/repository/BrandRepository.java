package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Brand;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    List<Brand> findAllByBranch_Id(Integer branch_id);
}
