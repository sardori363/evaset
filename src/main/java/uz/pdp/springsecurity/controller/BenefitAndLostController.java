package uz.pdp.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springsecurity.annotations.CheckPermission;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BenefitAndLostDto;
import uz.pdp.springsecurity.service.BenefitAndLostService;

import java.text.ParseException;

@RestController
@RequestMapping("api/benefit-lost")
public class BenefitAndLostController {
    @Autowired
    BenefitAndLostService benefitAndLostService;

    /**
     * IKKTA VAQT ORASIDAGI FOYDA VA ZARARLARNI OLIB CHIQISH
     * @param benefitAndLostDto
     * @return
     * @throws ParseException
     */
    @CheckPermission("VIEW_BENEFIT_AND_LOST")
    @PostMapping
    public HttpEntity<?> find(@RequestBody BenefitAndLostDto benefitAndLostDto) throws ParseException {
        ApiResponse apiResponse = benefitAndLostService.findBenefitLost(benefitAndLostDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * BITTA SANADAGI FOYDA VA ZARARLARNI OLIB CHIQISH
     * @param benefitAndLostDto
     * @return
     * @throws ParseException
     */
    @CheckPermission("VIEW_BENEFIT_AND_LOST")
    @PostMapping("/one-date")
    public HttpEntity<?> findOneDate(@RequestBody BenefitAndLostDto benefitAndLostDto) throws ParseException {
        ApiResponse apiResponse = benefitAndLostService.findBenefitAndLostByDate(benefitAndLostDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
