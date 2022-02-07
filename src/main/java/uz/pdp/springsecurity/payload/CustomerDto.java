package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotNull(message = "required line")
    private String name;
    @NotNull(message = "required line")
    private String phoneNumber;
    @NotNull(message = "enter your telegram username")
    private String telegram;

    private Integer businessId;
}
