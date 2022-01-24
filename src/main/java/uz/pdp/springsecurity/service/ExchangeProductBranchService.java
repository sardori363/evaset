package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.Branch;
import uz.pdp.springsecurity.entity.ExchangeProductBranch;
import uz.pdp.springsecurity.entity.ExchangeStatus;
import uz.pdp.springsecurity.entity.Product;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ExchangeProductBranchDTO;
import uz.pdp.springsecurity.payload.ExchangeProductDTO;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.ExchangeProductBranchRepository;
import uz.pdp.springsecurity.repository.ExchangeStatusRepository;
import uz.pdp.springsecurity.repository.ProductRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeProductBranchService {

    @Autowired
    ExchangeProductBranchRepository exchangeProductBranchRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ExchangeStatusRepository exchangeStatusRepository;

    public ApiResponse create(ExchangeProductBranchDTO exchangeProductBranchDTO) {
        ExchangeProductBranch exchangeProductBranch = new ExchangeProductBranch();

        /**
         * JO'NATUVCHI FILIALNI SAQLASH
         */
        Integer shippedBranchId = exchangeProductBranchDTO.getShippedBranchId();
        Optional<Branch>optionalBranch = branchRepository.findById(shippedBranchId);
        if (!optionalBranch.isPresent()) {
            return new ApiResponse("NOT FOUND SHIPPED BRANCH", false);
        }
        exchangeProductBranch.setShippedBranch(optionalBranch.get());

        /**
         * QABUL QILUVCHI FILILANI SET QILISH
         */
        Integer receivedBranch = exchangeProductBranchDTO.getReceivedBranchId();
        Optional<Branch> branchOptional = branchRepository.findById(receivedBranch);
        if (!branchOptional.isPresent()) {
            return new ApiResponse("NOT FOUND RECEIVED BRANCH", false);
        }
        exchangeProductBranch.setReceivedBranch(branchOptional.get());

        /**
         * DATENI SAQLSH
         */
        if (exchangeProductBranchDTO.getExchangeDate() == null) {
            Date date = new Date(System.currentTimeMillis());
            exchangeProductBranch.setExchangeDate(date);
        } else {
            exchangeProductBranch.setExchangeDate(exchangeProductBranchDTO.getExchangeDate());
        }

        /**
         * DESCRIPTIONI SAQLSH
         */
        exchangeProductBranch.setDescription(exchangeProductBranchDTO.getDescription());

        /**
         * JO'NATILGAN PRODUCTNI SAQLASH
         */
        List<ExchangeProductDTO> exchangeProductDTOS = exchangeProductBranchDTO.getExchangeProductDTOS();
        for (ExchangeProductDTO productDTO : exchangeProductDTOS) {
            Optional<Product> exchangeProduct = productRepository.findByIdAndBranch_Id(productDTO.getProductExchangeId(), optionalBranch.get().getId());

            Product product = exchangeProduct.get();
            product.setQuantity(product.getQuantity() - productDTO.getExchangeProductQuantity());
            productRepository.save(product);

            Optional<Product> optionalProduct = productRepository.findByNameAndBranch_Id(product.getName(), receivedBranch);
            if (optionalProduct.isPresent()) {
                Product receiveProduct = optionalProduct.get();
                receiveProduct.setQuantity(receiveProduct.getQuantity() + productDTO.getExchangeProductQuantity());
                productRepository.save(receiveProduct);
            } else {
                Product product1 = new Product();
                product1.setName(product.getName());
                product1.setBrand(product.getBrand());
                product1.setBarcode(product.getBarcode());
                product1.setCategory(product.getCategory());
                product1.setMeasurement(product.getMeasurement());
                product1.setMinQuantity(product.getMinQuantity());
                product1.setPhoto(product.getPhoto());
                product1.setBuyPrice(product.getBuyPrice());
                product1.setSalePrice(product.getSalePrice());
                product1.setTax(product.getTax());
                product1.setQuantity(productDTO.getExchangeProductQuantity());

                List<Branch> list = new ArrayList<>();
                list.add(branchOptional.get());
                product1.setBranch(list);


                productRepository.save(product1);
            }
        }
        Optional<ExchangeStatus> optionalExchangeStatus = exchangeStatusRepository.findById(exchangeProductBranchDTO.getExchangeStatusId());
        exchangeProductBranch.setExchangeStatus(optionalExchangeStatus.get());

        exchangeProductBranchRepository.save(exchangeProductBranch);

        return new ApiResponse("JO'NATILDI", true);
    }


    public ApiResponse getAll() {
        return null;
    }

    public ApiResponse getOne(Integer id) {
        return null;
    }

    public ApiResponse edit(Integer id, ExchangeProductBranchDTO exchangeProductBranchDTO) {
        return null;
    }

    public ApiResponse deleteTrade(Integer id) {
        return null;
    }

    public ApiResponse deleteAll() {
        return null;
    }

    public ApiResponse getByBranchId(Integer branch_id) {
        return null;
    }

    public ApiResponse shippedByBranchId(Integer branch_id) {
        return null;
    }

    public ApiResponse getByStatusId(Integer paymentStatus_id) {
        return null;
    }
}
