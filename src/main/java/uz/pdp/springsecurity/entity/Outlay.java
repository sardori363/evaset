package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;
import uz.pdp.springsecurity.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Outlay extends AbsEntity {

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OutlayCategory outlayCategory;

    private double totalSum;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Branch branch;

    @CreatedBy
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User spender;

    private String description;

    private Date date;


}
