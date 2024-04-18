package com.javatechie.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ClassInforNews {
    private String tenLop;
    private String giaoVienDay;
    private String troGiang;
    private Date ngayBatDau;
    private String status;
    private Date ngayKetThuc;
    private List<DateScheduleDto> dateScheduleDtoList = new ArrayList<DateScheduleDto>();
}
