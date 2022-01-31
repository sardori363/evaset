package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitAndLostDto {
    private Date firstDate;
    private Date secondDate;
    private Integer branchId;
}
