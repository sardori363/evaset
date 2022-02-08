package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.springsecurity.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trade extends AbsEntity {

    @OneToOne
    private Customer customer;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User trader;

    @Transient
    @OneToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn
    private List<TradeProduct> tradeProductList;

    private Date payDate;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Branch branch;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PaymentStatus paymentStatus;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PaymentMethod payMethod;

    private Double totalSum;

    private Double amountPaid;

    private Double loan;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Currency currency;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address address;
}