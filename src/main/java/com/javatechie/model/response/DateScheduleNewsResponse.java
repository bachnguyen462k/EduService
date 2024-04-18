package com.javatechie.model.response;

import com.javatechie.dto.ClassInforNews;
import com.javatechie.model.base.BaseResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DateScheduleNewsResponse extends BaseResponse {
    List<ClassInforNews> datas= new ArrayList<>();
}
