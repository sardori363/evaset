package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springsecurity.entity.Trade;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeHistoryDto {
    private double paidAmount;
    private Date paidDate;
    private Integer tradeId;
    private String description;
    private Integer paymentMethodId;
}
