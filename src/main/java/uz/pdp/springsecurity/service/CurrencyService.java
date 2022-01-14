package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Currency;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.CurrencyDto;
import uz.pdp.springsecurity.repository.CurrencyRepository;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public ApiResponse add(CurrencyDto currencyDto) {
        Currency currency = new Currency(
                currencyDto.getName(),
                currencyDto.getCurrentCourse()
        );
        currencyRepository.save(currency);
        return new ApiResponse("currency saved",true);
    }


    public ApiResponse edit(Integer id, CurrencyDto currencyDto) {
        if (!currencyRepository.existsById(id)) return new ApiResponse("currency not found",false);
        Currency currency = currencyRepository.getById(id);
        currency.setName(currencyDto.getName());
        currency.setCurrentCourse(currencyDto.getCurrentCourse());

        currencyRepository.save(currency);
        return new ApiResponse("currency edited",true);
    }

    public ApiResponse get(Integer id) {
        if (!currencyRepository.existsById(id)) return new ApiResponse("currency not found",false);
        return new ApiResponse("found",true,currencyRepository.findById(id).get());
    }

    public ApiResponse getAll() {
        return new ApiResponse("catch",true,currencyRepository.findAll());
    }

    public ApiResponse delete(Integer id) {
        if (!currencyRepository.existsById(id)) return new ApiResponse("currency not found",false);
        currencyRepository.deleteById(id);
        return new ApiResponse("currency deleted",true);
    }

    public ApiResponse deleteAll() {
        currencyRepository.deleteAll();
        return new ApiResponse("currencies removed",true);
    }
}
