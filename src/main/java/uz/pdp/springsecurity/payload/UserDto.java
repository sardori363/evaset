package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "required line")
    private String firstName;
    @NotNull(message = "required line")
    private String lastName;
    @NotNull(message = "required line")
    private String username;
    @NotNull(message = "required line")
    private String password;
    @NotNull(message = "required line")
    private Integer roleId;
    @NotNull(message = "required line")
    private Integer branchId;
    @NotNull(message = "required line")
    private Integer businessId;
    @NotNull(message = "required line")
    private Boolean enabled;

    private Integer photoId;
}
