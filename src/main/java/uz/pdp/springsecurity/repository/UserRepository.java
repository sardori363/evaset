package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);


    Optional<User> findByFirstName(String name);

    boolean existsByUsernameAndIdNot(String userName, Integer id);
}
