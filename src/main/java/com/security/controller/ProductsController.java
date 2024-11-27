package com.security.controller;

import com.security.dto.ProductDTO;
import com.security.service.ProductsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secure")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    /** accessible by admin, user */
    @GetMapping("/users/welcome")
    public ResponseEntity<String> welcomePage(){
        return ResponseEntity.ok("Welcome to TELNET PRODUCTS");
    }

    /** admin -- to add list of products.*/
    @PostMapping("/admin/product/add-product-List")
    public ResponseEntity<String> addMultipleProducts(@RequestBody List<ProductDTO> productDTOList) {
        return new ResponseEntity<>(productsService.addMultipleProducts(productDTOList), HttpStatus.CREATED);
    }

    /** admin -- to add a single product.*/
    @PostMapping("/admin/product/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productsService.addProduct(productDTO), HttpStatus.CREATED);
    }

    /** accessible by  admin */
    @GetMapping("/admin/info") // users, admin
    public ResponseEntity<String> userInformation() {
        return ResponseEntity.ok("User information can be viewed by admin");
    }

}
