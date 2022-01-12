package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
