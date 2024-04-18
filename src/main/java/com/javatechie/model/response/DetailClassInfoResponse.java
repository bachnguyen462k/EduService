package com.javatechie.model.response;

import com.javatechie.dto.DateScheduleDto;
import com.javatechie.entity.DateScheduleInfo;
import com.javatechie.entity.TeacherInfo;
import com.javatechie.model.base.BaseResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class DetailClassInfoResponse extends BaseResponse {
    private String id;
    private String tenLop;
    private String giaoVienDay;
    private String troGiang;
    private Date ngayTao;
    private String nguoiTao;
    private Date ngayCapNhat;
    private String nguoiCapNhat;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String trangThai;
    private List<DateScheduleInfo> dateSchedule = new ArrayList<DateScheduleInfo>();
    private TeacherInfo teacherInfo = new TeacherInfo();
    private TeacherInfo infoTroGiang = new TeacherInfo();
}
