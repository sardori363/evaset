package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    boolean existsByUsernameAndIdNot(String userName, Integer id);

    List<User> findAllByRole_Id(Integer role_id);

    List<User> findAllByBusiness_Id(Integer business_id);

    List<User> findAllByBranch_Id(Integer branch_id);

}