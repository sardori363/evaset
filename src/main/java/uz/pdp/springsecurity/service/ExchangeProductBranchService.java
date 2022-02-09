package uz.pdp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.entity.*;
import uz.pdp.springsecurity.payload.ApiResponse;
import uz.pdp.springsecurity.payload.ExchangeProductBranchDTO;
import uz.pdp.springsecurity.payload.ExchangeProductDTO;
import uz.pdp.springsecurity.repository.BranchRepository;
import uz.pdp.springsecurity.repository.ExchangeProductBranchRepository;
import uz.pdp.springsecurity.repository.ExchangeStatusRepository;
import uz.pdp.springsecurity.repository.ProductRepository;

import java.sql.Date;
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
        return add(exchangeProductBranch, exchangeProductBranchDTO);
    }


    public ApiResponse edit(Integer id, ExchangeProductBranchDTO exchangeProductBranchDTO) {
        Optional<ExchangeProductBranch> optionalExchange = exchangeProductBranchRepository.findById(id);
        if (optionalExchange.isEmpty()) return new ApiResponse("Exchange not found", false);
        ExchangeProductBranch exchange = optionalExchange.get();
        return add(exchange, exchangeProductBranchDTO);
    }

    public ApiResponse add(ExchangeProductBranch exchangeProductBranch, ExchangeProductBranchDTO exchangeProductBranchDTO) {
        if ((exchangeProductBranchDTO.getShippedBranchId() == exchangeProductBranchDTO.getReceivedBranchId())) {
            return new ApiResponse("JO'NATISH UCHUN BOSHQA FILIALNI KIRITING", false);
        }

        /**
         * JO'NATUVCHI FILIALNI SAQLASH
         */
        Integer shippedBranchId = exchangeProductBranchDTO.getShippedBranchId();
        Optional<Branch> optionalBranch = branchRepository.findById(shippedBranchId);
        if (optionalBranch.isEmpty()) {
            return new ApiResponse("NOT FOUND SHIPPED BRANCH", false);
        }
        exchangeProductBranch.setShippedBranch(optionalBranch.get());

        /**
         * QABUL QILUVCHI FILILANI SET QILISH
         */
        Integer receivedBranch = exchangeProductBranchDTO.getReceivedBranchId();
        Optional<Branch> branchOptional = branchRepository.findById(receivedBranch);
        if (branchOptional.isEmpty()) {
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
            Optional<Product> exchangeProduct = productRepository.findByIdAndBranch_IdAndActiveTrue(productDTO.getProductExchangeId(), optionalBranch.get().getId());

            Product product = exchangeProduct.get();
            product.setQuantity(product.getQuantity() - productDTO.getExchangeProductQuantity());
            productRepository.save(product);

            Optional<Product> optionalProduct = productRepository.findByBarcodeAndBranch_IdAndActiveTrue(product.getBarcode(), receivedBranch);
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

                List<Attachment> attachments = product.getPhoto();

                product1.setPhoto(attachments);
                product1.setBuyPrice(product.getBuyPrice());
                product1.setSalePrice(product.getSalePrice());
                product1.setTax(product.getTax());
                product1.setQuantity(productDTO.getExchangeProductQuantity());

                product1.setBranch(branchOptional.get());


                productRepository.save(product1);
            }
        }
        Optional<ExchangeStatus> optionalExchangeStatus = exchangeStatusRepository.findById(exchangeProductBranchDTO.getExchangeStatusId());
        exchangeProductBranch.setExchangeStatus(optionalExchangeStatus.get());

        exchangeProductBranchRepository.save(exchangeProductBranch);

        return new ApiResponse("JO'NATILDI", true);
    }

    public ApiResponse getOne(Integer id) {
        if (exchangeProductBranchRepository.findById(id).isEmpty()) return new ApiResponse("not found", false);
        return new ApiResponse("found", true, exchangeProductBranchRepository.getById(id));
    }

    public ApiResponse deleteOne(Integer id) {
        if (exchangeProductBranchRepository.findById(id).isEmpty()) return new ApiResponse("not found", false);
        exchangeProductBranchRepository.deleteById(id);
        return new ApiResponse("deleted", true);
    }

    public ApiResponse getByDate(Date exchangeDate) {
        List<ExchangeProductBranch> allByExchangeDate = exchangeProductBranchRepository.findAllByExchangeDate(exchangeDate);
        return new ApiResponse("catch", true, allByExchangeDate);
    }

    public ApiResponse getByStatusId(Integer exchangeStatusId, Integer branch_id) {
        List<ExchangeProductBranch> allByExchangeStatus_id = exchangeProductBranchRepository.findAllByExchangeStatus_IdAndShippedBranch_Id(exchangeStatusId,branch_id);
        if (allByExchangeStatus_id.isEmpty()) return new ApiResponse("Not found", false);
        return new ApiResponse("found", true, allByExchangeStatus_id);
    }

    public ApiResponse getByBusinessId(Integer businessId) {
        List<ExchangeProductBranch> allByBusinessId = exchangeProductBranchRepository.findAllByBusinessId(businessId);
        if (allByBusinessId.isEmpty()) return new ApiResponse("not found",false);
        return new ApiResponse("found",true,allByBusinessId);
    }

    public ApiResponse getByShippedBranchId(Integer shippedBranch_id) {
        List<ExchangeProductBranch> allByShippedBranch_id = exchangeProductBranchRepository.findAllByShippedBranch_Id(shippedBranch_id);
        if (allByShippedBranch_id.isEmpty()) return new ApiResponse("NOT FOUND",false);
        return new ApiResponse("FOUND",true,allByShippedBranch_id);
    }

    public ApiResponse getByReceivedBranchId(Integer receivedBranch_id) {
        List<ExchangeProductBranch> allByShippedBranch_id = exchangeProductBranchRepository.findAllByReceivedBranch_Id(receivedBranch_id);
        if (allByShippedBranch_id.isEmpty()) return new ApiResponse("NOT FOUND",false);
        return new ApiResponse("FOUND",true,allByShippedBranch_id);
    }
}
