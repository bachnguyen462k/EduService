package com.javatechie.service;

import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.request.AttendanceRequest;

public interface AttendanceService {
    BaseResponse attendanceNew(AttendanceRequest request, String user);
}
