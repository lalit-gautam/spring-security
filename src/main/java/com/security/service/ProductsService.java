package com.security.service;

import com.security.dto.*;
import com.security.entity.Products;
import com.security.repository.ProductsRepository;
import org.hibernate.query.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public ProductDTO getProductByProductName(String productName){
        return convertToDTO(productsRepository.findByName(productName));
    }

    public List<ProductDTO> getProductsByCategory(String productName){
        return productsRepository.findByCategory(productName).stream().map(this::convertToDTO).toList();
    }



    public OrderResponse processOrder(List<OrderRequest> orderRequests){

        List<OrderResponseItem> orderItems = new ArrayList<>();
        List<OrderError> ordersNotAvailable = new ArrayList<>();
        int totalItems = 0;
        BigDecimal overallTotalPrice = BigDecimal.ZERO;

        for(OrderRequest orderRequest : orderRequests){
            Products product = productsRepository.findByName(orderRequest.getProductName());

            if(Objects.isNull(product)){
                ordersNotAvailable.add(setProductNotAvailable(orderRequest.getProductName(), "The Requested Product is not Available"));
                continue;
            }

                if(product.getQuantity() >= orderRequest.getQuantity()){
                    product.setQuantity(product.getQuantity() - orderRequest.getQuantity());
                    BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(orderRequest.getQuantity()));

                    if(product.getQuantity() == 0){ // set product to inactive if quantity is 0
                        product.setActive(Boolean.FALSE);
                    }
                    productsRepository.save(product);
                    orderItems.add(setOrderResponse(orderRequest, totalPrice));

                    totalItems += orderRequest.getQuantity();
                    overallTotalPrice = overallTotalPrice.add(totalPrice);


                }else {
                    ordersNotAvailable.add(setProductNotAvailable(orderRequest.getProductName(),
                                        "The Requested Quantity is not available in Stock : Available Quantity : " + product.getQuantity()));
                }

        }

        return orderResponse(orderItems, ordersNotAvailable, totalItems, overallTotalPrice);

    }


    public OrderError setProductNotAvailable(String productName, String message){
        return OrderError.builder()
                .productName(productName)
                .message(message)
                .build();
    }

    public OrderResponseItem setOrderResponse(OrderRequest orderRequest, BigDecimal price){
        return OrderResponseItem.builder()
                .productName(orderRequest.getProductName())
                .quantity(orderRequest.getQuantity())
                .totalPrice(price)
                .build();
    }

    public OrderResponse orderResponse(List<OrderResponseItem> orderItems, List<OrderError> ordersNotAvailable, int totalItems, BigDecimal overallTotalPrice){
        return OrderResponse.builder()
                .items(orderItems)
                .errors(ordersNotAvailable)
                .totalItems(totalItems)
                .overallTotalPrice(overallTotalPrice)
                .build();

    }



    public ProductDTO convertToDTO(Products products){
        return modelMapper.map(products, ProductDTO.class);
    }

    public Products convertToEntity(ProductDTO productDTO){
        return modelMapper.map(productDTO, Products.class);
    }

}
