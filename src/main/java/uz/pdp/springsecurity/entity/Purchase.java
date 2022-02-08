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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends AbsEntity {
    @OneToOne
    private Supplier dealer;

    @OneToOne
    private User seller;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ExchangeStatus purchaseStatus;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Branch branch;

    private Date date;

    private String description;

    private double deliveryPrice;

    private double totalSum;

    @OneToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PurchaseProduct> purchaseProductList;

}
