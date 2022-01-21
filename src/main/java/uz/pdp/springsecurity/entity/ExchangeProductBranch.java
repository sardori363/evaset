package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.springsecurity.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeProductBranch extends AbsEntity {

    @OneToOne
    private Branch shippedBranch;

    @ManyToOne
    private Branch receivedBranch;

    private Date exchangeDate;

    private String description;

    @OneToMany
    private List<ExchangeProduct> exchangeProduct;

    @OneToOne
   private ExchangeStatus exchangeStatus;
}
