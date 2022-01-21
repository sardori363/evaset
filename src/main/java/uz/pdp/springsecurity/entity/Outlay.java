package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import uz.pdp.springsecurity.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Outlay extends AbsEntity {
    private String name;
    @OneToOne
    private OutlayCategory outlayCategory;

    private double totalSum;

    @ManyToMany
    private List<Branch> branch;

    @CreatedBy
    @ManyToOne
    private User spender;

    private String description;

    private Date date;

}
