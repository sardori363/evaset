package uz.pdp.springsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.Trade;

import java.util.Date;
import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
    List<Trade> findAllByTrader_Id(Integer trader_id);

    List<Trade> findAllByBranch_Id(Integer branch_id);

    List<Trade> findAllByCustomer_Id(Integer customer_id);

    List<Trade> findAllByPaymentStatus_Id(Integer paymentStatus_id);

    List<Trade> findAllByPayMethod_Id(Integer payMethod_id);

    List<Trade> findAllByAddress_Id(Integer address_id);

    void deleteByTrader_Id(Integer trader_id);

    void deleteAllByTrader_Id(Integer trader_id);

    List<Trade> findAllByPayDateIsBetweenAndBranch_Id(Date payDate, Date payDate2, Integer branch_id);


    @Query(value = "SELECT * FROM Trade t WHERE DATE(t.pay_date) = ?1", nativeQuery = true)
    List<Trade> findTradeByOneDate(java.sql.Date date);

    @Query(value = "select * from Trade t inner join branches b on t.branch_id = b.id where b.business_id = ?1",nativeQuery = true)
    List<Trade> findAllByBusinessId(Integer businessId);

}
