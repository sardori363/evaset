package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotNull(message = "required line")
    private String city;
    @NotNull(message = "required line")
    private String district;
    @NotNull(message = "required line")
    private String street;
    @NotNull(message = "required line")
    private String home;
}
