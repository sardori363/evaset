package uz.pdp.springsecurity.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.enums.Permissions;
import uz.pdp.springsecurity.enums.SuperAdmin;
import uz.pdp.springsecurity.repository.*;
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

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ExchangeStatusRepository exchangeStatusRepository;

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {

        List<Business> allBusiness = businessRepository.findAll();
        Business business = null;
        if (allBusiness.isEmpty()) {
            business = new Business("Application", "Test Uchun");
            businessRepository.save(business);
        }
//------------------------------------------------------------------------------------------//
        List<Address> addresses = addressRepository.findAll();
        Address address = null;
        if (addresses.isEmpty()) {
            address = new Address(
                    "Tashkent",
                    "Shayxontuxur",
                    "Gulobod",
                    "1"
            );
            addressRepository.save(address);
        }
//------------------------------------------------------------------------------------------//

        List<Branch> allBranch = branchRepository.findAll();
        Branch branch = null;
        if (allBranch.isEmpty()) {
            branch = new Branch(
                    "Test Filial",
                    address,
                    business
            );
            branchRepository.save(branch);
        }
//------------------------------------------------------------------------------------------//

        if (initMode.equals("always")) {
            Permissions[] permissions = Permissions.values();
            SuperAdmin[] superAdmins = SuperAdmin.values();

            Role admin = roleRepository.save(new Role(Constants.ADMIN, Arrays.asList(permissions), business));

            Role superAdmin = roleRepository.save(new Role(Constants.SUPERADMIN, Arrays.asList(ADD_BUSINESS, EDIT_BUSINESS, VIEW_BUSINESS, DELETE_BUSINESS)));

            Role manager = roleRepository.save(new Role(
                    Constants.MANAGER,
                    Arrays.asList(
                            ADD_ADDRESS,
                            EDIT_ADDRESS,
                            VIEW_ADDRESS,
                            DELETE_ADDRESS,

                            UPLOAD_MEDIA,
                            DOWNLOAD_MEDIA,
                            VIEW_MEDIA_INFO,
                            DELETE_MEDIA,

                            ADD_BRAND,
                            EDIT_BRAND,
                            VIEW_BRAND,
                            DELETE_BRAND,

                            ADD_CATEGORY,
                            EDIT_CATEGORY,
                            VIEW_CATEGORY,
                            DELETE_CATEGORY,

                            ADD_CURRENCY,
                            EDIT_CURRENCY,
                            VIEW_CURRENCY,
                            DELETE_CURRENCY,

                            ADD_CUSTOMER,
                            EDIT_CUSTOMER,
                            VIEW_CUSTOMER,
                            DELETE_CUSTOMER,

                            ADD_MEASUREMENT,
                            EDIT_MEASUREMENT,
                            VIEW_MEASUREMENT,
                            DELETE_MEASUREMENT,

                            ADD_OUTLAY,
                            EDIT_OUTLAY,
                            VIEW_OUTLAY,
                            DELETE_OUTLAY,

                            ADD_PRODUCT,
                            EDIT_PRODUCT,
                            VIEW_PRODUCT,
                            DELETE_PRODUCT,

                            ADD_ROLE,
                            EDIT_ROLE,
                            VIEW_ROLE,
                            DELETE_ROLE,

                            ADD_SUPPLIER,
                            EDIT_SUPPLIER,
                            VIEW_SUPPLIER,
                            DELETE_SUPPLIER,

                            ADD_USER,
                            EDIT_USER,
                            VIEW_USER,
                            DELETE_USER,
                            EDIT_MY_PROFILE,

                            ADD_TRADE,
                            EDIT_TRADE,
                            VIEW_TRADE,
                            DELETE_TRADE,
                            DELETE_MY_TRADE,
                            VIEW_MY_TRADE,

                            ADD_PAY_METHOD,
                            EDIT_PAY_METHOD,
                            VIEW_PAY_METHOD,
                            DELETE_PAY_METHOD,

                            ADD_PAY_STATUS,
                            EDIT_PAY_STATUS,
                            VIEW_PAY_STATUS,
                            DELETE_PAY_STATUS,

                            ADD_PURCHASE,
                            EDIT_PURCHASE,
                            VIEW_PURCHASE,
                            DELETE_PURCHASE,

                            ADD_EXCHANGE,
                            EDIT_EXCHANGE,
                            VIEW_EXCHANGE,
                            DELETE_EXCHANGE,

                            VIEW_BENEFIT_AND_LOST
                    ),
                    business
            ));

            Role employee = roleRepository.save(new Role(
                    Constants.EMPLOYEE,
                    Arrays.asList(UPLOAD_MEDIA,
                            DOWNLOAD_MEDIA,
                            VIEW_MEDIA_INFO,
                            VIEW_BRAND,
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
                            VIEW_PRODUCT

                    ),
                    business
            ));


            userRepository.save(new User(
                    "Admin",
                    "Admin",
                    "admin",
                    passwordEncoder.encode("123"),
                    admin,
                    true,
                    business,
                    branch
            ));

            userRepository.save(new User(
                    "SuperAdmin",
                    "Admin of site",
                    "superadmin",
                    passwordEncoder.encode("admin123"),
                    superAdmin,
                    true
            ));
            userRepository.save(new User(
                    "Manager",
                    "manager",
                    "manager",
                    passwordEncoder.encode("manager123"),
                    manager,
                    true,
                    business,
                    branch
            ));

            userRepository.save(new User(
                    "Employee",
                    "employee",
                    "employee",
                    passwordEncoder.encode("employee123"),
                    employee,
                    true,
                    business,
                    branch
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
        List<ExchangeStatus> exchangeStatusRepositoryAll = exchangeStatusRepository.findAll();
        if (exchangeStatusRepositoryAll.isEmpty()) {
            exchangeStatusRepository.save(new ExchangeStatus(
                    "Buyurtma berildi"
            ));

            exchangeStatusRepository.save(new ExchangeStatus(
                    "Kutilmoqda"
            ));

            exchangeStatusRepository.save(new ExchangeStatus(
                    "Qabul qilinndi"
            ));
        }

        List<PaymentMethod> all1 = payMethodRepository.findAll();
        if (all1.isEmpty()) {
            payMethodRepository.save(new PaymentMethod(
                    "Naqd",
                    business
            ));

            payMethodRepository.save(new PaymentMethod(
                    "UzCard",
                    business
            ));

            payMethodRepository.save(new PaymentMethod(
                    "Humo",
                    business
            ));
        }


        List<Currency> currencyList = currencyRepository.findAll();
        if (currencyList.isEmpty()) {
            currencyRepository.save(new Currency(
                    "DOLLAR",
                    10785.85,
                    business
            ));

            currencyRepository.save(new Currency(
                    "SO'M",
                    0,
                    business
            ));
        }

    }

}