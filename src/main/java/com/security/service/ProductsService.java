package com.security.service;

import com.security.dto.ProductDTO;
import com.security.entity.Products;
import com.security.repository.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    /**
     * ModelMapper to automate mapping process from entity to DTO and DTO to entity.
     */
    private final ModelMapper modelMapper = new ModelMapper();

    public String addMultipleProducts(List<ProductDTO> productDTOList) {
        if(!productDTOList.isEmpty()){
            List<Products> productsList = productDTOList.stream().map(product -> convertToEntity(product)).toList();
            return productsRepository.saveAll(productsList)
                    .stream().map(Products::getName)
                    .toList() + " added successfully";
        }
        return "Some Exception occurred while adding List of Products";
    }

    public String addProduct(ProductDTO productDTO){
        if(Objects.nonNull(productDTO)){
           return  productsRepository.save(convertToEntity(productDTO)).getName() + " Added successfully";
        }
        return "Some Exception occurred while adding Product";
    }

    public List<ProductDTO> viewAllProducts(){
        return productsRepository.findAll().stream().map(product -> convertToDTO(product)).toList();
    }


    public ProductDTO convertToDTO(Products products){
        return modelMapper.map(products, ProductDTO.class);
    }

    public Products convertToEntity(ProductDTO productDTO){
        return modelMapper.map(productDTO, Products.class);
    }


//    public Products convertToProduct(ProductDTO productDTO){
//        return Products.builder()
//                .name(productDTO.getName())
//                .price(productDTO.getPrice())
//                .quantity(productDTO.getQuantity())
//                .dateReceived(productDTO.getDateReceived())
//                .description(productDTO.getDescription())
//                .category(productDTO.getCategory())
//                .supplier(productDTO.getSupplier())
//                .isActive(productDTO.isActive())
//                .build();
//    }
}
