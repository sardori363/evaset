package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.ExchangeProductBranch;

import java.sql.Date;
import java.util.List;

public interface ExchangeProductBranchRepository extends JpaRepository<ExchangeProductBranch,Integer> {
    List<ExchangeProductBranch> findAllByExchangeDate(Date exchangeDate);
    List<ExchangeProductBranch> findAllByExchangeStatus_Id(Integer exchangeStatus_id);

    @Query(value = "select * from exchange_product_branch inner join branches b on b.business_id = ?1",nativeQuery = true)
    List<ExchangeProductBranch> findAllByBusinessId(Integer businessId);

}
