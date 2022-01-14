package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
}
