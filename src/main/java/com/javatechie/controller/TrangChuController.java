package com.javatechie.controller;

import com.javatechie.config.Constant;
import com.javatechie.model.response.DateScheduleNewsResponse;
import com.javatechie.model.response.ListClassInfoResponse;
import com.javatechie.service.ClassService;
import com.javatechie.service.DashboardService;
import com.javatechie.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin
public class TrangChuController {

    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private HttpServletRequest request;
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private ClassService classService;

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
    @GetMapping("/dateSchedule")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public DateScheduleNewsResponse dateSchedule() {
        return dashboardService.dateSchedule(this.getUser());
    }
}
