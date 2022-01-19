package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.springsecurity.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "branches")
@AllArgsConstructor
@NoArgsConstructor
public class Branch extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @OneToOne
    private Address address;
}
