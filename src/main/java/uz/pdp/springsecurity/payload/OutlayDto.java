package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutlayDto {
    @NotNull(message = "required line")
    private Integer outlayCategoryId;
    @NotNull(message = "required line")
    private double totalSum;
    @NotNull(message = "required line")
    private Integer branchId;
    @NotNull(message = "required line")
    private Integer spenderId;
    @NotNull(message = "required line")
    private String description;
    private Date date;
}
