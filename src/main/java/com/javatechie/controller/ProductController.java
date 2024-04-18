package com.javatechie.controller;

import com.javatechie.config.Constant;
import com.javatechie.dto.Product;
import com.javatechie.entity.UserInfo;
import com.javatechie.model.base.ResponseData;
import com.javatechie.service.JwtService;
import com.javatechie.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    @PreAuthorize("hasAuthority('" + Constant.Role.ADMIN + "')")
    public ResponseEntity<ResponseData> welcome( HttpServletRequest http) {
        return ResponseEntity.ok()
                .body(new ResponseData<>().success("Welcome this endpoint is not secure",http));
    }



    @GetMapping("/all")
    @PreAuthorize("hasAuthority('" + Constant.Role.ADMIN + "')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + Constant.Role.ADMIN + "')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }


}
