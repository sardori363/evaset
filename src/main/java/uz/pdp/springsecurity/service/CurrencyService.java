package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.Currency;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.CurrencyDto;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.CurrencyRepository;

import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    BranchRepository branchRepository;

    public ApiResponse add(CurrencyDto currencyDto) {
        Optional<Branch> optionalBranch = branchRepository.findById(currencyDto.getBranchId());
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("NOT FOUND BRANCH", false);
        }
        Currency currency = new Currency(
                currencyDto.getName(),
                currencyDto.getCurrentCourse(),
                optionalBranch.get()
        );
        currencyRepository.save(currency);
        return new ApiResponse("currency saved", true);
    }


    public ApiResponse edit(Integer id, CurrencyDto currencyDto) {
        if (!currencyRepository.existsById(id)) return new ApiResponse("currency not found", false);
        Currency currency = currencyRepository.getById(id);
        currency.setName(currencyDto.getName());
        currency.setCurrentCourse(currencyDto.getCurrentCourse());

        currencyRepository.save(currency);
        return new ApiResponse("currency edited", true);
    }

    public ApiResponse get(Integer id) {
        if (!currencyRepository.existsById(id)) return new ApiResponse("currency not found", false);
        return new ApiResponse("found", true, currencyRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch", true, currencyRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!currencyRepository.existsById(id)) return new ApiResponse("currency not found", false);
        currencyRepository.deleteById(id);
        return new ApiResponse("currency deleted", true);
    }

    public ApiResponse deleteAll() {
        currencyRepository.deleteAll();
        return new ApiResponse("currencies removed", true);
    }
}
