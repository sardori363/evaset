package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    List<Measurement> findAllByBusiness_Id(Integer business_id);
}
