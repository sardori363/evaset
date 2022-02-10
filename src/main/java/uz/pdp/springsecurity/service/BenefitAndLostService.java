package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.BenefitAndLost;
import uz.pdp.springsecurity.entity.Outlay;
import uz.pdp.springsecurity.entity.Trade;
import uz.pdp.springsecurity.entity.TradeProduct;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.BenefitAndLostDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.OutlayRepository;
import uz.pdp.springsecurity.repository.PurchaseRepository;
import uz.pdp.springsecurity.repository.TradeRepository;

import java.text.ParseException;
import java.util.List;

@Service
public class BenefitAndLostService {
    @Autowired
    BranchRepository branchRepository;

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    OutlayRepository outlayRepository;

    public ApiResponse findBenefitLost(BenefitAndLostDto benefitAndLostDto) throws ParseException {

        List<Trade> allTrade = tradeRepository.findAllByPayDateIsBetweenAndBranch_Id(benefitAndLostDto.getFirstDate(), benefitAndLostDto.getSecondDate(), benefitAndLostDto.getBranchId());
        if (allTrade.isEmpty()) {
            return new ApiResponse("NOT FOUND", false, "NO INFORMATION WAS FOUND DURING THIS TIME");
        }

        double totalBuySum = 0;
        double totalSaleSum = 0;
        double otherExpenses = 0;
        for (Trade trade : allTrade) {

            for (TradeProduct tradeProduct : trade.getTradeProductList()) {
                totalBuySum += (tradeProduct.getProduct().getBuyPrice() * tradeProduct.getTradedQuantity());
                totalSaleSum += (tradeProduct.getProduct().getSalePrice() * tradeProduct.getTradedQuantity());
                otherExpenses += (tradeProduct.getProduct().getTax() * tradeProduct.getTradedQuantity());
            }

        }

        List<Outlay> allOptionalOutlay = outlayRepository.findAllByDateIsBetweenAndBranch_Id(benefitAndLostDto.getFirstDate(), benefitAndLostDto.getSecondDate(), benefitAndLostDto.getBranchId());
        for (Outlay outlay : allOptionalOutlay) {
            otherExpenses += outlay.getTotalSum();
        }

        BenefitAndLost benefitAndLost = new BenefitAndLost();

        benefitAndLost.setTotalBuySum(totalBuySum);
        benefitAndLost.setTotalSaleSum(totalSaleSum);
        benefitAndLost.setOtherExpenses(otherExpenses);


        if (((totalSaleSum - (totalBuySum + otherExpenses)) > 0)) {
            benefitAndLost.setBenefit(totalSaleSum - (totalBuySum + otherExpenses));
            benefitAndLost.setLost(0);
        } else {
            benefitAndLost.setLost(totalSaleSum - (totalBuySum + otherExpenses));
            benefitAndLost.setBenefit(0);
        }

        return new ApiResponse("FOUND", true, benefitAndLost);
    }

    public ApiResponse findBenefitAndLostByDate(BenefitAndLostDto benefitAndLostDto) throws ParseException {
        System.out.println(benefitAndLostDto.getFirstDate());
        List<Trade> allTrade = tradeRepository.findTradeByOneDate(benefitAndLostDto.getFirstDate());

        if (allTrade.isEmpty()) {
            return new ApiResponse("NOT FOUND", false, "NO INFORMATION WAS FOUND DURING THIS TIME");
        }

        double totalBuySum = 0;
        double totalSaleSum = 0;
        double otherExpenses = 0;
        for (Trade trade : allTrade) {
            for (TradeProduct tradeProduct : trade.getTradeProductList()) {
                totalBuySum += (tradeProduct.getProduct().getBuyPrice() * tradeProduct.getTradedQuantity());
                totalSaleSum += (tradeProduct.getProduct().getSalePrice() * tradeProduct.getTradedQuantity());
                otherExpenses += (tradeProduct.getProduct().getTax() * tradeProduct.getTradedQuantity());
            }

        }

        List<Outlay> allOptionalOutlay = outlayRepository.findAllByDateAndBranch_Id(benefitAndLostDto.getFirstDate(), benefitAndLostDto.getBranchId());
        if (!allOptionalOutlay.isEmpty()) {
            for (Outlay outlay : allOptionalOutlay) {
                otherExpenses += outlay.getTotalSum();
            }
        }

        BenefitAndLost benefitAndLost = new BenefitAndLost();

        benefitAndLost.setTotalBuySum(totalBuySum);
        benefitAndLost.setTotalSaleSum(totalSaleSum);
        benefitAndLost.setOtherExpenses(otherExpenses);


        if (((totalSaleSum - (totalBuySum + otherExpenses)) > 0)) {
            benefitAndLost.setBenefit(totalSaleSum - (totalBuySum + otherExpenses));
            benefitAndLost.setLost(0);
        } else {
            benefitAndLost.setLost(totalSaleSum - (totalBuySum + otherExpenses));
            benefitAndLost.setBenefit(0);
        }

        return new ApiResponse("FOUND", true, benefitAndLost);
    }


}
