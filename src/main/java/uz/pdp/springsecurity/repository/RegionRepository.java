package uz.pdp.springsecurity.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import uz.pdp.springsecurity.entity.Region;

import java.util.List;

@RepositoryRestResource(path = "region")
public interface RegionRepository extends JpaRepository<Region, Integer> {

    //roliga kiritadi
    @PreAuthorize(value = "hasAnyRole('ADMIN','OPERATOR')")
    @Override
    <S extends Region> S save(S entity);

    @PreAuthorize(value = "hasRole('ADMIN')")
    @Override
    void deleteById(Integer integer);


}
