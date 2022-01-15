package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String userName, Integer id);
}
