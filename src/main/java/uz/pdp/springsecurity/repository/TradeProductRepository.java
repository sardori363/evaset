package uz.pdp.springsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Trade;
import uz.pdp.springsecurity.entity.TradeProduct;

public interface TradeProductRepository extends JpaRepository<TradeProduct, Integer> {

}
