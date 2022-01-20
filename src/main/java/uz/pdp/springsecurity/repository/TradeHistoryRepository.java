package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.TradeHistory;

import java.util.List;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory,Integer> {
    List<TradeHistory> findAllByTrade_Id(Integer trade_id);

    void deleteAllByTrade_Id(Integer trade_id);
}
