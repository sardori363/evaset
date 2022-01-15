package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
