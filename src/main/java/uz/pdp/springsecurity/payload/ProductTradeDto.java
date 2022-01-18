package uz.pdp.springsecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTradeDto {
    @NotNull(message = "required line")
    private String name;

    @NotNull(message = "required line")
    private List<Integer> productTradeIds;

}
