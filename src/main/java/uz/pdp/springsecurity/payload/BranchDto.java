package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springsecurity.entity.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private String name;
    private Integer addressId;
}
