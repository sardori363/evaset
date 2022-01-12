package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.springsecurity.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbsEntity {

    private String name;
    private long barcode;
    @OneToOne
    private Brand brandId;
    @ManyToOne
    private Category categoryId;
    @OneToOne
    private Measurement unitOfMeasurementId;
    private Integer minQuantity;
    @OneToMany
    private List<Attachment> photoId;
    private double buyPrice;
    private double salePrice;
    private double tax;
    @OneToMany
    private List<Branch> branchId;
    private Integer quantity;
}
