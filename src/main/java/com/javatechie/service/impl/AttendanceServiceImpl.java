package com.javatechie.service.impl;

import com.javatechie.dto.AttendanceDto;
import com.javatechie.entity.AttendanceInfo;
import com.javatechie.entity.ClassInfo;
import com.javatechie.entity.DateScheduleInfo;
import com.javatechie.entity.PriceInfo;
import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.base.Status;
import com.javatechie.model.request.AttendanceRequest;
import com.javatechie.repository.AttendanceInforepository;
import com.javatechie.repository.ClassInfoRepository;
import com.javatechie.repository.DateScheduleInfoRepository;
import com.javatechie.repository.PriceInfoRepository;
import com.javatechie.service.AttendanceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private ClassInfoRepository classInfoRepository;

    @Autowired
    private DateScheduleInfoRepository dateScheduleInfoRepository;

    @Autowired
    private PriceInfoRepository priceInfoRepository;

    @Autowired
    private AttendanceInforepository attendanceInforepository;

    @Override
    public BaseResponse attendanceNew(AttendanceRequest request, String user) {
        BaseResponse response = new BaseResponse();
        response.getStatus().setCode(Status.SUCCESS);
        String idClass = request.getIdClass();
        List<AttendanceDto> idStudents = request.getIdStudents();
        String idDateSch = request.getDateSchedule();

        // van tin thong tin class
        Optional<ClassInfo> classData = classInfoRepository.findById(Integer.parseInt(idClass));
        if (!classData.isPresent()) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Mã Lớp không tồn tại");
            return response;
        }
        ClassInfo classInfo = classData.get();
        // lay thong tin gia tien
        PriceInfo priceData = priceInfoRepository.findByLopHoc(idClass);
        // lay ca hoc
        Optional<DateScheduleInfo> dateScheduleInfo = dateScheduleInfoRepository.findById(Integer.parseInt(idDateSch));
        if (!dateScheduleInfo.isPresent()) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Mã Ca không tồn tại");
            return response;
        }
        DateScheduleInfo dateData = dateScheduleInfo.get();
        List<AttendanceInfo> datas = new ArrayList<>();
        for (AttendanceDto student : idStudents) {
            AttendanceInfo data = new AttendanceInfo();
            data.setNgayDiemDanh(new Date());
            data.setNguoiDiemDanh(user);
            data.setCaHoc(dateData.getCaHoc());
            data.setLopHoc(String.valueOf(classInfo.getId()));
            data.setMaHocSinh(student.getIdStudent());
            data.setGiaTien(priceData.getGiaTien());
            data.setThuHoc(dateData.getNgayHoc());
            data.setIdDateSchedule(String.valueOf(dateData.getId()));
            data.setStudentName(student.getStudentName());
            data.setStatus(student.getStatus());
            datas.add(data);
        }
        attendanceInforepository.saveAll(datas);
        response.getStatus().setMessage("Điểm danh thành công");
        return response;
    }
}
