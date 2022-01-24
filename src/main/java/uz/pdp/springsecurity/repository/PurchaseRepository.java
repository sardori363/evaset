package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Purchase;

import java.sql.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAllByDealer_Id(Integer dealer_id);
    List<Purchase> findAllByPurchaseStatus_Id(Integer purchaseStatus_id);
    List<Purchase> findAllByPaymentStatus_Id(Integer paymentStatus_id);
    List<Purchase> findAllByBranch_Id(Integer branch_id);
    List<Purchase> findAllByDate(Date date);
    List<Purchase> findAllByTotalSum(double totalSum);
}
