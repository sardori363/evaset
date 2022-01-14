package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Outlay;

public interface OutlayRepository extends JpaRepository<Outlay,Integer> {
}
