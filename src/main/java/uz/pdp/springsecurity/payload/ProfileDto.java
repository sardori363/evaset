package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    @NotNull(message = "required line")
    private String firstName;
    @NotNull(message = "required line")
    private String lastName;
    @NotNull(message = "required line")
    private String userName;
    @NotNull(message = "required line")
    private String password;
    @NotNull(message = "required line")
    private String prePassword;
}
