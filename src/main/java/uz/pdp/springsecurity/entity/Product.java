package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.springsecurity.entity.template.AbsEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double quantity;

    private long barcode;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Brand brand;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Measurement measurement;

    private Double minQuantity;

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Attachment> photo;

    private double buyPrice;

    private double salePrice;

    private double tax;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Branch branch;

    private Date expireDate;

    private Date dueDate;

    private boolean active = true;

}
