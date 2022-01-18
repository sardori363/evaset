package uz.pdp.springsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
