package com.javatechie.service;

import com.javatechie.model.request.AuthRequest;
import com.javatechie.exception.BusinessException;
import com.javatechie.model.response.LoginResponse;
import org.springframework.transaction.annotation.Transactional;

public interface LoginService {
    @Transactional(rollbackFor = {BusinessException.class})
    LoginResponse login(AuthRequest authRequest) throws BusinessException;

    LoginResponse changePassword(AuthRequest authRequest,String user);
}
