package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Currency;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    List<Currency> findAllByBusiness_Id(Integer business_id);
}
