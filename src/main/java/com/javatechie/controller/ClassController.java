package com.javatechie.controller;

import com.javatechie.config.Constant;
import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.request.ClassNewRequest;
import com.javatechie.model.request.DetailRequest;
import com.javatechie.model.request.UpdateStatusRequest;
import com.javatechie.model.response.DetailClassInfoResponse;
import com.javatechie.model.response.ListClassInfoResponse;
import com.javatechie.service.ClassService;
import com.javatechie.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/class")
@CrossOrigin
public class ClassController {
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
    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public BaseResponse addNew(@RequestBody ClassNewRequest classNewRequest) {
        return classService.addNew(classNewRequest,this.getUser());
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public ListClassInfoResponse listClass() {
        return classService.listStudent(this.getUser());
    }

    @PostMapping("/updateStatus")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public BaseResponse updateStatus(@RequestBody UpdateStatusRequest studentInfo) {
        return classService.updateStatus(studentInfo,this.getUser());
    }

    @PostMapping("/detail")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public DetailClassInfoResponse detail(@RequestBody DetailRequest request) {
        return classService.detail(request);
    }
}
