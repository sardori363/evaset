package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.ExchangeProductBranch;

import java.sql.Date;
import java.util.List;

public interface ExchangeProductBranchRepository extends JpaRepository<ExchangeProductBranch,Integer> {
    List<ExchangeProductBranch> findAllByExchangeStatus_Id(Integer exchangeStatus_id);
    List<ExchangeProductBranch> findAllByExchangeDate(Date exchangeDate);
}
