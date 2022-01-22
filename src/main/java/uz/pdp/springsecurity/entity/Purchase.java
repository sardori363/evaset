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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends AbsEntity {
    @OneToOne
    private Supplier dealer;

    @OneToOne
    private ExchangeStatus purchaseStatus;

    @OneToOne
    private PaymentStatus paymentStatus;

    @ManyToOne
    private Branch branch;

    private Date date;

    private String description;

    private double deliveryPrice;

    @OneToMany
    private List<PurchaseProduct> purchaseProductList;
}
