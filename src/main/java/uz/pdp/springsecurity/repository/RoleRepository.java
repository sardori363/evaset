package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    boolean existsByName(String name);


    Optional<Role> findByName(String name);
}
