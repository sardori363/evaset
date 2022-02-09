package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.ExchangeProduct;

public interface ExchangeProductRepository extends JpaRepository<ExchangeProduct,Integer> {
}
