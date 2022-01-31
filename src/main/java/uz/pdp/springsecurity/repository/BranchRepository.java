package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Branch;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch,Integer> {

    List<Branch> findAllByBusiness_Id(Integer business_id);
}
