package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Branch shippedBranch;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Branch receivedBranch;

    private Date exchangeDate;

    private String description;

    @OneToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ExchangeProduct> exchangeProduct;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ExchangeStatus exchangeStatus;
}
