package uz.pdp.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.springsecurity.entity.template.AbsEntity;
import uz.pdp.springsecurity.enums.Permissions;


import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data


public class User extends AbsEntity implements UserDetails {


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    //yoqilgan
    private boolean enabled = false;
    //muddati tugamagan
    private boolean accountNonExpired = true;
    //qulflanmagan
    private boolean accountNonLocked = true;
    //Foydalanuvchining hisob ma'lumotlari (parol) muddati tugaganligini ko'rsatadi.
    private boolean credentialsNonExpired = true;


    public User(String firstName, String lastName, String username, String password, Role role, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permissions> permissions = this.role.getPermissions();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Permissions permission : permissions) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthorities;
    }


}

