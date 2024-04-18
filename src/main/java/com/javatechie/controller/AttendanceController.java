package com.javatechie.controller;

import com.javatechie.config.Constant;
import com.javatechie.exception.BusinessException;
import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.request.AttendanceRequest;
import com.javatechie.service.AttendanceService;
import com.javatechie.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/attendance")
@CrossOrigin
public class AttendanceController {
    @Autowired
    private HttpServletRequest request;
    private static final String TOKEN_PREFIX = "Bearer ";



    @Autowired
    private AttendanceService attendanceService;

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

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public BaseResponse attendanceNew(@RequestBody AttendanceRequest request)  throws BusinessException {
        return attendanceService.attendanceNew(request,this.getUser());
    }
}
