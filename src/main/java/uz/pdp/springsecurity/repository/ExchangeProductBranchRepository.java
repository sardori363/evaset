package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.ExchangeProductBranch;

import java.sql.Date;
import java.util.List;

public interface ExchangeProductBranchRepository extends JpaRepository<ExchangeProductBranch,Integer> {
    List<ExchangeProductBranch> findAllByExchangeDateAndBusiness_Id(Date exchangeDate, Integer business_id);

    List<ExchangeProductBranch> findAllByBusiness_Id(Integer business_id);

    List<ExchangeProductBranch> findAllByShippedBranch_Id(Integer shippedBranch_id);
    List<ExchangeProductBranch> findAllByReceivedBranch_Id(Integer receivedBranch_id);

    List<ExchangeProductBranch> findAllByExchangeStatus_IdAndBusiness_Id(Integer exchangeStatusId, Integer business_id);
}
