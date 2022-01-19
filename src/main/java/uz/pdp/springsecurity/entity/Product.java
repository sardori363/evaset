package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
    private Integer quantity;

    @Column(nullable = false,unique = true)
    private long barcode;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Category category;

    @OneToOne
    private Measurement measurement;

    private Integer minQuantity;

    @OneToMany
    private List<Attachment> photo;

    private double buyPrice;

    private double salePrice;

    private double tax;

    @OneToMany
    private List<Branch> branch;

    private Date expireDate;

    private Date dueDate;

}
