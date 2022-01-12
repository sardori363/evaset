package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.springsecurity.entity.template.AbsEntity;


import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data


public class User extends AbsEntity {


    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phone;
    private boolean enabled;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;


}

