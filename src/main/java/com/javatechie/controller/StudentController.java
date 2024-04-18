package com.javatechie.controller;

import com.javatechie.config.Constant;
import com.javatechie.entity.StudentInfo;
import com.javatechie.entity.UserInfo;
import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.request.DetailRequest;
import com.javatechie.model.request.StudentNewRequest;
import com.javatechie.model.request.UpdateStatusRequest;
import com.javatechie.model.response.ListStudentResponse;
import com.javatechie.service.JwtService;
import com.javatechie.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService service;

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
    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public BaseResponse addNewUser(@RequestBody StudentNewRequest studentInfo) {
        return service.addUser(studentInfo,this.getUser());
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public ListStudentResponse listStudent() {
        return service.listStudent(this.getUser());
    }

    @PostMapping("/updateStatus")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public BaseResponse updateStatus(@RequestBody UpdateStatusRequest studentInfo) {
        return service.updateStatus(studentInfo,this.getUser());
    }

    @GetMapping("/listByClass")
    @PreAuthorize("hasAnyAuthority('" + Constant.Role.ADMIN + "','"
            + Constant.Role.TEACHER + "')")
    public ListStudentResponse listStudentByClass(DetailRequest request) {
        return service.listStudentByClass(request);
    }
}
