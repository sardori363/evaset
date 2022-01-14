package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull(message = "required line")
    private String name;
    @NotNull(message = "required line")
    private long barcode;
    private Integer brandId;
    private Integer categoryId;
    @NotNull(message = "required line")
    private Integer MeasurementId;
    private Integer minQuantity;
    private List<Integer> photoId;
    @NotNull(message = "required line")
    private double buyPrice;
    private double salePrice;
    private double tax;
    @NotNull(message = "required line")
    private List<Integer> branchId;
    @NotNull(message = "required line")
    private Integer quantity;
}
