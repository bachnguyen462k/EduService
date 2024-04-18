package com.javatechie.model.request;

import com.javatechie.dto.AttendanceDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AttendanceRequest {
    private List<AttendanceDto> idStudents = new ArrayList<AttendanceDto>();
    private String idClass ="";
    private String dateSchedule ="";
}
