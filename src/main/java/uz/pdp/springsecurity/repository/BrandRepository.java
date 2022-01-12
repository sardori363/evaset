package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
