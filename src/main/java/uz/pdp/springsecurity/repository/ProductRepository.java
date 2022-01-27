package uz.pdp.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecurity.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);

    Optional<Product> findByIdAndBranch_Id(Integer id, Integer branch_id);

    Optional<Product> findByBarcodeAndBranch_Id(long barcode, Integer branch_id);

    List<Product> findAllByBarcode(long barcode);

    List<Product> findAllByCategory_Id(Integer category_id);

    List<Product> findAllByBrand_Id(Integer brand_id);

    List<Product> findAllByBranch_Id(Integer branch_id);
}
