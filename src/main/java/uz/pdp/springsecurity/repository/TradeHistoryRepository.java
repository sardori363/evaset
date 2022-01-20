package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.TradeHistory;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory,Integer> {
}
