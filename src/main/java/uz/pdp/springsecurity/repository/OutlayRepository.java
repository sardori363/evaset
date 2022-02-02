package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.Outlay;

import java.util.Date;
import java.util.List;

public interface OutlayRepository extends JpaRepository<Outlay, Integer> {

    List<Outlay> findAllByDateIsBetweenAndBranch_Id(Date firs_date, Date second_date, Integer branch_id);

    List<Outlay> findAllByDateAndBranch_Id(Date firs_date, Integer branch_id);

    @Query(value = "SELECT * FROM outlay o WHERE DATE(o.date) = ?1", nativeQuery = true)
    List<Outlay> findAllByDate(java.sql.Date date);

    List<Outlay> findAllByBranch_Id(Integer branch_id);

    @Query(value = "select * from outlay inner join branches b on b.business_id = ?1",nativeQuery = true)
    List<Outlay> findAllByBusinessId(Integer businessId);

}
