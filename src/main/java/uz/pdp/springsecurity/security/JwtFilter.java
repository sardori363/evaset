package uz.pdp.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.springsecurity.service.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        //REQUESTDAN TOKENNI OLISH
        String token = httpServletRequest.getHeader("Authorization");

        //TOKEN BORLIGINI VA TOKENNING BOSHLANISHI BEARER BO'LISHINI TEKSHIRYAPMIZ
        if (token != null && token.startsWith("Bearer")) {

            //AYNAN TOKENNI O'ZINI QIRQIB OLDIK
            token = token.substring(7);

            //TOKENNI VALIDATSIYADAN O'TKAZDIK (TOKEN BUZILMAGNALIGINI, MUDDATI O'TMAGANLIGINI VA H.K)
            boolean validateToken = jwtProvider.validateToken(token);
            if (validateToken) {

                //TOKENNI ICHIDAN USERNAME NI OLDIK
                String username = jwtProvider.getUsernameFromToken(token);

                //USERNAME ORQALI USERDETAILSNI OLDIK
                UserDetails userDetails = authService.loadUserByUsername(username);

                //USERDETAILS ORQALI AUTHENTICATION YARATIB OLDIK
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());

//                System.out.println(SecurityContextHolder.getContext().getAuthentication());

                //SISTEMAGA KIM KIRGANLIGINI O'RNATDIK
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

//                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
