package com.javatechie.model.response;

import com.javatechie.entity.ClassInfo;
import com.javatechie.model.base.BaseResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListClassInfoResponse extends BaseResponse {
    private List<ClassInfo> datas = new ArrayList<>();
}
