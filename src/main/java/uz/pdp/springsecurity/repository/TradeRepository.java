package uz.pdp.springsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Trade;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
    List<Trade> findAllByTrader_Id(Integer trader_id);
    List<Trade> findAllByBranch_Id(Integer branch_id);
    List<Trade> findAllByCustomer_Id(Integer customer_id);
    List<Trade> findAllByPayDate(Date payDate);
    List<Trade> findAllByPaymentStatus_Id(Integer paymentStatus_id);
    List<Trade> findAllByPayMethod_Id(Integer payMethod_id);
    List<Trade> findAllByAddress_Id(Integer address_id);
    void deleteByTrader_Id(Integer trader_id);
    void deleteAllByTrader_Id(Integer trader_id);
}
