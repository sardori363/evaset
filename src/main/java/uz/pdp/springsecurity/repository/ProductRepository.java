package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springsecurity.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByBarcode(long barcode);

    Optional<Product> findByIdAndBranch_Id(Integer id, Integer branch_id);

    Optional<Product> findByBarcodeAndBranch_Id(long barcode, Integer branch_id);

    List<Product> findAllByBarcode(long barcode);

    List<Product> findAllByCategory_Id(Integer category_id);

    List<Product> findAllByBrand_Id(Integer brand_id);

    List<Product> findAllByBranch_Id(Integer branch_id);

    @Query(value = "select * from product inner join branches b on b.business_id = ?1",nativeQuery = true)
    List<Product> findAllByBusinessId(Integer businessId);

}
