package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.TradeHistory;

import java.util.List;
import java.util.Optional;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Integer> {
    List<TradeHistory> findAllByTrade_Id(Integer trade_id);

    void deleteAllByTrade_Id(Integer trade_id);

    void deleteByTrade_Id(Integer trade_id);

    Optional<TradeHistory> findByIdAndTrade_Id(Integer id, Integer trade_id);

    Optional<TradeHistory> findByTrade_Id(Integer trade_id);

    List<TradeHistory> findAllByTrade_Branch_Id(Integer branch_id);

    List<TradeHistory> findAllByTrade_Branch_Business_Id(Integer business_id);
}
