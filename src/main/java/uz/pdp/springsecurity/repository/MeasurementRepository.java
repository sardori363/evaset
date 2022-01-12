package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
