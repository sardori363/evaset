package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull(message = "required line")
    private String name;

    @NotNull(message = "required line")
    private Double quantity;

    @NotNull(message = "required line")
    private long barcode;

    private Integer brandId;

    private Integer categoryId;

    @NotNull(message = "required line")

    private Integer measurementId;

    private List<Integer> photoIds;

    private Double minQuantity;

    @NotNull(message = "required line")

    private double buyPrice;

    private double salePrice;

    private double tax;

    @NotNull(message = "required line")
    private List<Integer> branchId;

    private Date expireDate;

    private Date dueDate;


}
