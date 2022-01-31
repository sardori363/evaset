package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Outlay;

import java.util.Date;
import java.util.List;

public interface OutlayRepository extends JpaRepository<Outlay, Integer> {

    List<Outlay> findAllByDateIsBetweenAndBranch_Id(Date firs_date, Date second_date, Integer branch_id);

    List<Outlay> findAllByDateAndBranch_Id(Date firs_date, Integer branch_id);
}
