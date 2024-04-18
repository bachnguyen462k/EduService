package com.javatechie.model.request;

import com.javatechie.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
public class UserNewRequest {

    private String name;
    private String email;
    private String password;
    private Role roles;

    private String idTeacher;
    private String idStudent;
}
