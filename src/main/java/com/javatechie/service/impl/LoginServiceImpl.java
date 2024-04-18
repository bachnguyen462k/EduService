package com.javatechie.service.impl;

import com.javatechie.config.Constant;
import com.javatechie.entity.*;
import com.javatechie.enums.Action;
import com.javatechie.model.base.Status;
import com.javatechie.model.request.AuthRequest;
import com.javatechie.exception.BusinessException;
import com.javatechie.model.response.LoginResponse;
import com.javatechie.repository.*;
import com.javatechie.service.JwtService;
import com.javatechie.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private AdminInfoRepository adminRepository;
    @Autowired
    private TeacherInfoRepository teacherRepository;
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ConfigRepository configRepository;

    @Override
    @Transactional(rollbackFor = {BusinessException.class})
    public LoginResponse login(AuthRequest authRequest) throws BusinessException {
        log.error("login method");
        LoginResponse response = new LoginResponse();
        response.getStatus().setCode(Status.SUCCESS);
        response.getStatus().setMessage("Đăng nhập thành công");
        Optional<UserInfo> userInfo = repository.findByName(authRequest.getUsername());
        if (userInfo.isEmpty()) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Tài khoản hoặc mật khẩu không đúng");
            return response;
        }


        UserInfo user = userInfo.get();
        if (null == user.getTrangThai() || !user.getTrangThai().equals(Action.ACTIVE.getValue())) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Tài khoản đã bị khóa");
            return response;
        }
        Optional<Config> config = configRepository.findByCode("SIZE_LOGIN_FAIL");
        int count = 0;
        if (config.isPresent()) {
            count = Integer.parseInt(config.get().getValue()) - (user.getLoginFail() + 1);
            if (user.getLoginFail() >= Integer.parseInt(config.get().getValue())) {
                user.setTrangThai(Action.LOCK.getValue());
                repository.save(user);
                response.getStatus().setCode(Status.FAIL);
                response.getStatus().setMessage("Tài khoản đã bị khóa do quá số lần đăng nhập sai.");
                return response;
            }
        }

        if (user.getRoles().equals(Constant.Role.ADMIN) && user.getIdAdmin() != null) {
            Optional<AdminInfo> adminInfo = adminRepository.findById(Integer.parseInt(user.getIdAdmin()));
            if (adminInfo.isPresent()) {
                response.setAdminInfo(adminInfo.get());
            }
        } else if (user.getRoles().equals(Constant.Role.TEACHER) && user.getIdTeacher() != null) {
            Optional<TeacherInfo> adminInfo = teacherRepository.findById(Integer.parseInt(user.getIdTeacher()));
            if (adminInfo.isPresent()) {
                response.setTeacherInfo(adminInfo.get());
            }
        } else if (user.getRoles().equals(Constant.Role.STUDENT) && user.getIdStudent() != null) {
            Optional<StudentInfo> adminInfo = studentInfoRepository.findById(Integer.parseInt(user.getIdStudent()));
            if (adminInfo.isPresent()) {
                response.setStudentInfo(adminInfo.get());
            }
        }
        boolean check = false;
        if (!authRequest.getUsername().equals(user.getName())) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Tài khoản không đúng");
            check = true;
        }
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Mật khẩu không đúng." +
                    "Bạn còn " + count + " lần đăng nhập");
            check = true;
        }
        if (!check) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                response.setToken(token);
                user.setLoginFail(0);
            } else {
                response.getStatus().setCode(Status.FAIL);
                response.getStatus().setMessage("Tài khoản hoặc mật khẩu không đúng");
            }
        } else {
            user.setLoginFail(user.getLoginFail() + 1);
        }
        user.setLastLogin(new Date());
        repository.save(user);
        log.error("login method END");
        return response;
    }

    @Override
    public LoginResponse changePassword(AuthRequest authRequest, String userName) {
        log.error("changePassword method");
        LoginResponse response = new LoginResponse();
        response.getStatus().setCode(Status.SUCCESS);
        Optional<UserInfo> userInfo = repository.findByName(userName);
        if (userInfo.isEmpty()) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Tài khoản hoặc mật khẩu không đúng");
            return response;
        }
        UserInfo user = userInfo.get();
        if (null == user.getTrangThai() || !user.getTrangThai().equals(Action.ACTIVE.getValue())) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Tài khoản đã bị khóa");
            return response;
        }
        if (!authRequest.getUsername().equals(user.getName())) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Tài khoản không đúng");
            return response;
        }
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Mật khẩu không đúng");
            return response;
        }
        if (authRequest.getPassword().equals(authRequest.getPasswordNew())) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Mật khẩu mới không được giống mật khẩu cũ");
            return response;
        }
        user.setPassword(passwordEncoder.encode(authRequest.getPasswordNew()));
        repository.save(user);
        response.getStatus().setMessage("Đổi mật khẩu thành công!");
        log.error("changePassword END");
        return response;
    }
}
