package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    List<Measurement> findAllByBranch_Id(Integer branch_id);
    List<Measurement> findAllByBranch_Business_Id(Integer business_id);
}
