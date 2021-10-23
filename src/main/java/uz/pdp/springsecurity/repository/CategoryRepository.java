package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.springsecurity.entity.Category;
import uz.pdp.springsecurity.entity.Region;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
