package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    @NotNull(message = "required line")
    private String name;
    @NotNull(message = "required line")
    private Integer businessId;
}
