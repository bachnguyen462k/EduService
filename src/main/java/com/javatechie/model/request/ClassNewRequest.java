package com.javatechie.model.request;

import com.javatechie.dto.DateScheduleDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ClassNewRequest {
    private String id;
    private String tenLop;
    private String giaoVienDay;
    private String troGiang;

    private Date ngayBatDau;

    private Date ngayKetThuc;
    private List<DateScheduleDto> dateSchedule = new ArrayList<DateScheduleDto>();
    private String giaTien="";
}
