package com.javatechie.model.response;

import com.javatechie.entity.AdminInfo;
import com.javatechie.entity.StudentInfo;
import com.javatechie.entity.TeacherInfo;
import com.javatechie.model.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse extends BaseResponse {
    private String token ="";
    private TeacherInfo teacherInfo = new TeacherInfo();
    private StudentInfo studentInfo = new StudentInfo();
    private AdminInfo adminInfo = new AdminInfo();

    public LoginResponse(String token) {
        this.token = token;
    }
    public LoginResponse() {

    }
}
