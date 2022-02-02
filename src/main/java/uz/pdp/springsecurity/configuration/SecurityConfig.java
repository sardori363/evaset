package uz.pdp.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.springsecurity.security.JwtFilter;
import uz.pdp.springsecurity.service.AuthService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthService authService;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // slesh belgisini qoyish kk apidaan oldin
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http
//                .cors()
//                .and()
//                .csrf().disable()
//                .exceptionHandling()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/",
//                        "/favicon.ico",
//                        "//*.png",
//                        "//*.gif",
//                        "//*.svg",
//                        "//*.jpg",
//                        "//*.html",
//                        "//*.css",
//                        "//*.js",
//                        "/swagger-ui.html",
//                        "/swagger-resources/",
//                        "/v2/",
//                        "/csrf",
//                        "/webjars/")
//                .permitAll()
//                .antMatchers("/api/auth/", "/api/attachment/download/").permitAll()
//                .antMatchers("/api/**")
//                .authenticated();
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
