package com.javatechie.model.response;

import com.javatechie.entity.StudentInfo;
import com.javatechie.model.base.BaseResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListStudentResponse extends BaseResponse {
    private List<StudentInfo> datas= new ArrayList<>();
}
