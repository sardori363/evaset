package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch,Integer> {
}
