package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.Purchase;

import java.sql.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAllByPurchaseStatus_Id(Integer purchaseStatus_id);
    List<Purchase> findAllByPaymentStatus_Id(Integer paymentStatus_id);
    List<Purchase> findAllByBranch_Id(Integer branch_id);
    List<Purchase> findAllByDate(Date date);
    List<Purchase> findAllByTotalSum(double totalSum);
    List<Purchase> findAllByDealer_Id(Integer dealer_id);

    @Query(value = "select * from purchase inner join branches b on b.business_id = ?1",nativeQuery = true)
    List<Purchase> findAllByBusinessId(Integer businessId);

}
