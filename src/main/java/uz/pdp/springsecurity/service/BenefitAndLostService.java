package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.BenefitAndLost;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BenefitAndLostDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.PurchaseRepository;
import uz.pdp.springsecurity.repository.TradeRepository;

@Service
public class BenefitAndLostService {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    public ApiResponse findBenefitLost(BenefitAndLostDto benefitAndLostDto) {

        BenefitAndLost benefitAndLost = new BenefitAndLost();
        return null;
    }
}
