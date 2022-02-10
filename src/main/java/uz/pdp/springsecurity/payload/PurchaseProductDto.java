package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseProductDto {
    @NotNull(message = "required line")
    private Double purchasedQuantity;
    @NotNull(message = "required line")
    private Integer productPurchaseId;
}
