package uz.pdp.springsecurity.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.springsecurity.entity.PaymentMethod;
import uz.pdp.springsecurity.entity.PaymentStatus;
import uz.pdp.springsecurity.entity.Role;
import uz.pdp.springsecurity.entity.User;
import uz.pdp.springsecurity.enums.Permissions;
import uz.pdp.springsecurity.repository.PaymentStatusRepository;
import uz.pdp.springsecurity.repository.RoleRepository;
import uz.pdp.springsecurity.repository.UserRepository;
import uz.pdp.springsecurity.util.Constants;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PaymentStatusRepository paymentStatusRepository;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            Permissions[] permissions = Permissions.values();
            Role admin = roleRepository.save(new Role(
                    Constants.ADMIN,
                    Arrays.asList(permissions)
            ));

            userRepository.save(new User(
                    "Admin",
                    "Admin",
                    "admin",
                    passwordEncoder.encode("adminjon123"),
                    admin,
                    true
            ));

        }
        List<PaymentStatus> all = paymentStatusRepository.findAll();
        if (all.isEmpty()){
            paymentStatusRepository.save(new PaymentStatus(
                    "To'langan"
            ) );

            paymentStatusRepository.save(new PaymentStatus(
                    "Qisman to'langan"
            ) );

            paymentStatusRepository.save(new PaymentStatus(
                    "To'lanmagan"
            ) );
        }
    }
}
