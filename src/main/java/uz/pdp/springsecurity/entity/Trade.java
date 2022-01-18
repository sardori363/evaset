package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.springsecurity.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
class Trade extends AbsEntity {

    @OneToOne
    private Customer customer;

    @OneToOne
    private User trader;

    @OneToMany
    private List<Product> productList;

    private Date payDate;

    @OneToOne
    private Branch branch;

    @OneToOne
    private PaymentStatus paymentStatus;

    @OneToOne
    private PaymentMethod payMethod;

    private Double totalSum;

    private Double amountPaid;

    private Double loan;

    private Double productAmount;

    @OneToOne
    private Address address;
}
