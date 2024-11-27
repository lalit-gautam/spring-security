package com.security.controller;

import com.security.dto.ProductDTO;
import com.security.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Public End points.
 */

@RestController
@RequestMapping("/api/telnet")
public class ViewProductsController {

    @Autowired
    private ProductsService productsService;

    /** public url to view List of available products */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> viewAllProducts(){
        return new ResponseEntity<>(productsService.viewAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<ProductDTO> getProductByProductName(@PathVariable String name){
        return new ResponseEntity<>(productsService.getProductByProductName(name), HttpStatus.OK);
    }


    @GetMapping("/products/{category}")
    public ResponseEntity <List<ProductDTO>> getProductsByCategory(@PathVariable String category){
        return new ResponseEntity<>(productsService.getProductsByCategory(category), HttpStatus.OK);
    }

}
