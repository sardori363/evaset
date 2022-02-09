package uz.pdp.springsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.TradeProduct;

import java.util.List;

public interface TradeProductRepository extends JpaRepository<TradeProduct, Integer> {
List<TradeProduct> findAllByProduct_Id(Integer product_id);
}
