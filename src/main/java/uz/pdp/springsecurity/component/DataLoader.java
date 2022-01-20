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
import uz.pdp.springsecurity.repository.PayMethodRepository;
import uz.pdp.springsecurity.repository.PaymentStatusRepository;
import uz.pdp.springsecurity.repository.RoleRepository;
import uz.pdp.springsecurity.repository.UserRepository;
import uz.pdp.springsecurity.util.Constants;


import java.util.Arrays;
import java.util.List;


import static uz.pdp.springsecurity.enums.Permissions.*;

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

    @Autowired
    PayMethodRepository payMethodRepository;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            Permissions[] permissions = Permissions.values();

            Role admin = roleRepository.save(new Role(Constants.ADMIN, Arrays.asList(permissions)));

            Role manager = roleRepository.save(new Role(
                    Constants.MANAGER,
                    Arrays.asList(permissions)
            ));

            Role employee = roleRepository.save(new Role(
                    Constants.EMPLOYEE,
                    Arrays.asList(UPLOAD_MEDIA,
                            DOWNLOAD_MEDIA,
                            VIEW_MEDIA_INFO,
                            DELETE_MEDIA,
                            ADD_BRAND,
                            EDIT_BRAND,
                            VIEW_BRAND,
                            DELETE_BRAND,
                            ADD_CURRENCY,
                            EDIT_CURRENCY,
                            VIEW_CURRENCY,
                            DELETE_CURRENCY,
                            ADD_MEASUREMENT,
                            EDIT_MEASUREMENT,
                            VIEW_MEASUREMENT,
                            DELETE_MEASUREMENT,

                            ADD_TRADE,
                            EDIT_TRADE,
                            VIEW_MY_TRADE,
                            DELETE_MY_TRADE,

                            ADD_PAY_METHOD,
                            EDIT_PAY_METHOD,
                            VIEW_PAY_METHOD,
                            DELETE_PAY_METHOD,
                            ADD_PAY_STATUS,
                            EDIT_PAY_STATUS,
                            VIEW_PAY_STATUS,
                            DELETE_PAY_STATUS,
                            EDIT_MY_PROFILE,
                            VIEW_PRODUCT)
            ));

            userRepository.save(new User(
                    "Admin",
                    "Admin",
                    "admin",
                    passwordEncoder.encode("adminjon123"),
                    admin,
                    true
            ));
            userRepository.save(new User(
                    "Manager",
                    "manager",
                    "manager",
                    passwordEncoder.encode("manager123"),
                    manager,
                    true
            ));

            userRepository.save(new User(
                    "Employee",
                    "employee",
                    "employee",
                    passwordEncoder.encode("employee123"),
                    employee,
                    true
            ));

        }
        List<PaymentStatus> all = paymentStatusRepository.findAll();
        if (all.isEmpty()) {
            paymentStatusRepository.save(new PaymentStatus(
                    "To'langan"
            ));

            paymentStatusRepository.save(new PaymentStatus(
                    "Qisman to'langan"
            ));

            paymentStatusRepository.save(new PaymentStatus(
                    "To'lanmagan"
            ));
        }
        List<PaymentMethod> all1 = payMethodRepository.findAll();
        if (all1.isEmpty()) {
            payMethodRepository.save(new PaymentMethod(
                    "Naqd"
            ));

            payMethodRepository.save(new PaymentMethod(
                    "UzCard"
            ));

            payMethodRepository.save(new PaymentMethod(
                    "Humo"
            ));
        }

    }

}
