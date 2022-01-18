package uz.pdp.springsecurity.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.TradeDTO;

import javax.validation.Valid;

@RequestMapping("api/trade")
public interface TradeController {

    @GetMapping
    ApiResponse<?> getAll();


    @GetMapping("/{id}")
    ApiResponse<TradeDTO> get(@PathVariable Integer id);


    @PostMapping
    ApiResponse<?> create(@RequestBody @Valid TradeDTO tradeDTO);


    @PutMapping("/{id}")
    ApiResponse<?> edit(@PathVariable Integer id, @RequestBody @Valid TradeDTO tradeDTO);


    @DeleteMapping("/{id}")
    ApiResponse<?> delete(@PathVariable Integer id);

}
