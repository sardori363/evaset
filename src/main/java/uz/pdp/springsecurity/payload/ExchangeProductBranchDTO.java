package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeProductBranchDTO {

    private Integer shippedBranchId;

    private Integer receivedBranchId;

    private Date exchangeDate;

    private String description;

    private Integer exchangeStatusId;

    private List<ExchangeProductDTO> exchangeProductDTOS;

    private Integer businessId;
}
