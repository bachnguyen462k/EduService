package com.javatechie.controller;

import com.javatechie.config.Constant;
import com.javatechie.entity.UserInfo;
import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.request.AuthRequest;
import com.javatechie.exception.BusinessException;
import com.javatechie.model.base.ResponseData;
import com.javatechie.model.request.UpdateStatusRequest;
import com.javatechie.model.request.UserNewRequest;
import com.javatechie.model.response.LoginResponse;
import com.javatechie.service.JwtService;
import com.javatechie.service.LoginService;
import com.javatechie.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {


    @Autowired
    private LoginService loginService;


    @Autowired
    private ProductService service;


    @Autowired
    private HttpServletRequest request;
    private static final String TOKEN_PREFIX = "Bearer ";


    @Autowired
    private JwtService jwtService;

    public  String getUser() {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            token = token.substring(7);
            return jwtService.extractUsername(token);
        }
        return null;
    }

    @PostMapping("/authen/login")
    public LoginResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest)  throws BusinessException {
        return loginService.login(authRequest);
    }

    @PostMapping("/api/v1/authen/new")
    @PreAuthorize("hasAuthority('" + Constant.Role.ADMIN + "')")
    public BaseResponse addNewUser(@RequestBody UserNewRequest userInfo) {
        return service.addUser(userInfo,this.getUser());
    }

    @PostMapping("/api/v1/authen/updateStatus")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN +"')")
    public BaseResponse updateStatus(@RequestBody UpdateStatusRequest studentInfo) {
        return service.updateStatus(studentInfo,this.getUser());
    }

    @PostMapping("/authen/change-password")
    public LoginResponse changePassword(@RequestBody AuthRequest authRequest)  throws BusinessException {
        return loginService.changePassword(authRequest,this.getUser());
    }
}
