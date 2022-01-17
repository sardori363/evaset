package uz.pdp.springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.springsecurity.enums.Permissions;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //foydalanuvchilarni vaqtincha ushlab turish


    //foydalanuvchilarni auth qilib berish role based
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("123")).roles("ADMIN")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
//                .and()
//                .withUser("operator").password(passwordEncoder().encode("12345")).roles("OPERATOR");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123")).authorities("READ_CATEGORY","ADD_CATEGORY","DELETE_CATEGORY")
                .and()
                .withUser("user").password(passwordEncoder().encode("user")).authorities("READ_CATEGORY")
                .and()
                .withUser("operator").password(passwordEncoder().encode("12345")).authorities("EDIT_CATEGORY","READ_CATEGORY");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                // slesh belgisini qoyish kk apidaan oldin
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/region/**").hasAnyRole("USER", "OPERATOR","ADMIN")
//                .antMatchers(HttpMethod.POST, "/api/region").hasAnyRole("OPERATOR","ADMIN")
//                .antMatchers("/api/**").hasRole("ADMIN")

                //permission based
//                .antMatchers(HttpMethod.GET,"/api/category").hasAuthority("READ_CATEGORY")
//                .antMatchers(HttpMethod.POST,"/api/category").hasAuthority("ADD_CATEGORY")
//                .antMatchers(HttpMethod.DELETE,"/api/category/*").hasAuthority("DELETE_CATEGORY")

                .antMatchers("/api/address").hasAnyRole(String.valueOf(Permissions.ADMIN))
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
