package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    Optional<Product> findByIdAndBranch_IdAndActiveTrue(Integer id, Integer branch_id);

    Optional<Product> findByBarcodeAndBranch_Id(long barcode, Integer branch_id);

    List<Product> findAllByBarcodeAndActiveTrue(long barcode);

    List<Product> findAllByCategory_IdAndActiveTrue(Integer category_id);

    List<Product> findAllByBrand_IdAndActiveTrue(Integer brand_id);

    List<Product> findAllByBranch_IdAndActiveTrue(Integer branch_id);

    List<Product> findAllByMeasurement_IdAndActiveTrue(Integer measurement_id);

    @Query(value = "select * from product p inner join branches b on p.branch_id = b.id where b.business_id = ?1 AND p.active =true", nativeQuery = true)
    List<Product> findAllByBusinessIdActiveTrue(Integer businessId);//tekshirib korish kere

    Optional<Product> findByBarcodeAndBranch_IdAndActiveTrue(long barcode, Integer branch_id);
}
