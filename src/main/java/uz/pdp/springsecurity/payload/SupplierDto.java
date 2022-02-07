package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {
    @NotNull(message = "required line")
    private String name;
    @NotNull(message = "required line")
    private String phoneNumber;
    @NotNull(message = "required line")
    private String telegram;
    @NotNull(message = "required line")
    private String supplierType;
    private Integer businessId;
}
