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
public class PurchaseDto {
    @NotNull(message = "required line")
    private Integer dealerId;
    @NotNull(message = "required line")
    private Integer seller;
    @NotNull(message = "required line")
    private Integer purchaseStatusId;
    @NotNull(message = "required line")
    private Integer paymentStatusId;
    @NotNull(message = "required line")
    private Integer branchId;
    private Date date;
    private String description;
    private double deliveryPrice;
    @NotNull(message = "required line")
    private List<PurchaseProductDto> purchaseProductsDto;
}
