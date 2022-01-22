package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private Integer dealerId;
    private Integer purchaseStatusId;
    private Integer paymentStatusId;
    private Integer branchId;
    private Date date;
    private String description;
    private double deliveryPrice;
    private List<PurchaseProductDto> purchaseProductsDto;
}
