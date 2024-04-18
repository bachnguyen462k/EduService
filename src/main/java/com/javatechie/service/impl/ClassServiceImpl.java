package com.javatechie.service.impl;

import com.javatechie.dto.DateScheduleDto;
import com.javatechie.entity.*;
import com.javatechie.enums.Action;
import com.javatechie.enums.DateSchedule;
import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.base.Status;
import com.javatechie.model.request.ClassNewRequest;
import com.javatechie.model.request.DetailRequest;
import com.javatechie.model.request.UpdateStatusRequest;
import com.javatechie.model.response.DetailClassInfoResponse;
import com.javatechie.model.response.ListClassInfoResponse;
import com.javatechie.repository.*;
import com.javatechie.service.ClassService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassInfoRepository repository;

    @Autowired
    private DateScheduleInfoRepository dateScheduleInfoRepository;
    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private TeacherInfoRepository teacherInfoRepository;

    @Autowired
    private PriceInfoRepository priceInfoRepository;

    @Override
    public BaseResponse addNew(ClassNewRequest info, String username) {
        BaseResponse response = new BaseResponse();
        response.getStatus().setCode(Status.SUCCESS);
        ClassInfo info2 = new ClassInfo(info, username);
        if (StringUtils.isBlank(username)) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Người dùng không đúng");
            return response;
        }
        if (StringUtils.isNotBlank(info.getId())) {
            Optional<ClassInfo> data = repository.findById(Integer.parseInt(info.getId()));
            info2.setId(Integer.parseInt(info.getId()));
            if (data.isPresent()) {
                info2.setNguoiTao(data.get().getNguoiTao());
                info2.setNgayTao(data.get().getNgayTao());
                info2.setNguoiCapNhat(username);
                info2.setNgayCapNhat(new Date());
                response.getStatus().setMessage("Cập nhật thành công");
            } else {
                response.getStatus().setCode(Status.FAIL);
                response.getStatus().setMessage("Id không tồn tại");
            }
        } else {
            info2.setStatus(Action.ACTIVE.getValue());
            info2.setNguoiTao(username);
            info2.setNgayTao(new Date());
            response.getStatus().setMessage("Thêm mới thành công");
        }
        info2 = repository.save(info2);
        if (info.getDateSchedule().size() > 0) {
            for (DateScheduleDto data : info.getDateSchedule()) {
                DateScheduleInfo dateSchedule = new DateScheduleInfo();
                if (StringUtils.isNotBlank(data.getId())) {
                    // neu co id thi cap nhat
                    Optional<DateScheduleInfo> values = dateScheduleInfoRepository.findById(Integer.parseInt(data.getId()));
                    {
                        if (values.isPresent()) {
                            dateSchedule = values.get();
                        }
                    }
                    dateSchedule.setNguoiCapNhat(username);
                    dateSchedule.setNgayCapNhat(new Date());
                } else {
                    // neu chua co id thi tao moi thoi gian hoc
                    dateSchedule.setNguoiTao(username);
                    dateSchedule.setNgayTao(new Date());
                    dateSchedule.setStatus(Action.ACTIVE.getValue());
                }
                dateSchedule.setIdLop(String.valueOf(info2.getId()));
                Optional<Config> config = configRepository.findByValue(String.valueOf(data.getValue()));
                Config thuHoc = config.get();
                dateSchedule.setNgayHoc(thuHoc.getMoTa());
                dateSchedule.setValueNgayHoc(data.getValue());
                dateSchedule.setThoiGianHoc(data.getThoiGian());
                dateSchedule.setCaHoc(data.getCaHoc());
                dateSchedule.setMoTa(data.getMoTa());
                dateScheduleInfoRepository.save(dateSchedule);
            }
        }
        PriceInfo priceInfo= priceInfoRepository.findByLopHoc(String.valueOf(info2.getId()));
        // save gia tien
        if (null ==priceInfo) {
            priceInfo = new PriceInfo();
            priceInfo.setNgayTao(new Date());
            priceInfo.setNguoitao(username);
            priceInfo.setGiaTien(new BigDecimal(info.getGiaTien()));
            priceInfo.setLopHoc(String.valueOf(info2.getId()));
        }else{
            priceInfo.setNgayCapNhat(new Date());
            priceInfo.setNguoiCapNhat(username);
            priceInfo.setGiaTien(new BigDecimal(info.getGiaTien()));
            priceInfo.setLopHoc(String.valueOf(info2.getId()));
        }
        priceInfoRepository.save(priceInfo);
        return response;
    }

    @Override
    public ListClassInfoResponse listStudent(String user) {
        ListClassInfoResponse response = new ListClassInfoResponse();
        response.getStatus().setCode(Status.SUCCESS);
        if (StringUtils.isBlank(user)) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Người dùng không đúng");
            return response;
        }
        List<ClassInfo> datas = repository.findAll();
        response.setDatas(datas);
        response.getStatus().setMessage("Truy vấn thành công");
        return response;
    }

    @Override
    public BaseResponse updateStatus(UpdateStatusRequest info, String user) {
        BaseResponse response = new BaseResponse();
        response.getStatus().setCode(Status.SUCCESS);
        if (StringUtils.isBlank(user)) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Người dùng không đúng");
            return response;
        }
        Optional<ClassInfo> data = repository.findById(Integer.parseInt(info.getId()));
        if (data.isPresent()) {
            ClassInfo info2 = data.get();
            info2.setStatus(info.getStatus().getValue());
            info2.setNguoiCapNhat(user);
            info2.setNgayCapNhat(new Date());
            repository.save(info2);
            response.getStatus().setMessage("Cập nhật thành công");
        } else {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Id không tồn tại");
        }
        return response;
    }

    @Override
    public DetailClassInfoResponse detail(DetailRequest request) {
        DetailClassInfoResponse response = new DetailClassInfoResponse();
        response.getStatus().setCode(Status.SUCCESS);
        Optional<ClassInfo> data = repository.findById(Integer.parseInt(request.getId()));
        if (data.isPresent()) {
            ClassInfo classInfo = data.get();
            response.setId(String.valueOf(classInfo.getId()));
            response.setTenLop(classInfo.getTenLop());
            response.setNgayBatDau(classInfo.getNgayBatDau());
            response.setNgayKetThuc(classInfo.getNgayKetThuc());
            response.setNgayTao(classInfo.getNgayTao());
            response.setNguoiTao(classInfo.getNguoiTao());
            response.setNgayCapNhat(classInfo.getNgayCapNhat());
            response.setNguoiCapNhat(classInfo.getNguoiCapNhat());
            response.setTroGiang(classInfo.getTroGiang());
            response.setTrangThai(classInfo.getStatus());
            Optional<UserInfo> userInfo = userInfoRepository.findByName(classInfo.getGiaoVienDay());
            if (userInfo.isPresent()) {
                UserInfo user = userInfo.get();
                Optional<TeacherInfo> teacherInfo = teacherInfoRepository.findById(Integer.parseInt(user.getIdTeacher()));
                response.setTeacherInfo(teacherInfo.get());
            }

            Optional<UserInfo> userInfo2 = userInfoRepository.findByName(classInfo.getGiaoVienDay());
            if (userInfo2.isPresent()) {
                UserInfo user = userInfo2.get();
                Optional<TeacherInfo> teacherInfo = teacherInfoRepository.findById(Integer.parseInt(user.getIdTeacher()));
                response.setInfoTroGiang(teacherInfo.get());
            }
            List<DateScheduleInfo> dateScheduleInfos = dateScheduleInfoRepository.findByIdLop(request.getId());
            response.setDateSchedule(dateScheduleInfos);
        } else {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Lớp không tồn tại");
        }
        return response;
    }
}
