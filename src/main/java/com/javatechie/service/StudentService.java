package com.javatechie.service;

import com.javatechie.entity.StudentInfo;
import com.javatechie.entity.UserInfo;
import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.base.Status;
import com.javatechie.model.request.DetailRequest;
import com.javatechie.model.request.StudentNewRequest;
import com.javatechie.model.request.UpdateStatusRequest;
import com.javatechie.model.response.ListStudentResponse;
import com.javatechie.repository.StudentInfoRepository;
import com.javatechie.utils.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentInfoRepository repository;

    public BaseResponse addUser(StudentNewRequest info, String username) {
        BaseResponse response = new BaseResponse();
        response.getStatus().setCode(Status.SUCCESS);
        StudentInfo info2 = new StudentInfo(info, username);
        if (StringUtils.isBlank(username)) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Người dùng không đúng");
            return response;
        }
        if (StringUtils.isNotBlank(info.getId())) {
            Optional<StudentInfo> data = repository.findById(Integer.parseInt(info.getId()));
            info2.setId(Integer.parseInt(info.getId()));
            if (data.isPresent()) {
                info2.setNguoiTao(data.get().getNguoiTao());
                info2.setNgayTao(data.get().getNgayTao());
                info2.setNguoiCapNhat(username);
                info2.setUpdatedAt(new Date());
                response.getStatus().setMessage("Cập nhật thành công");
            } else {
                response.getStatus().setCode(Status.FAIL);
                response.getStatus().setMessage("Id không tồn tại");
            }
        } else {
            info2.setNguoiTao(username);
            info2.setNgayTao(new Date());
            response.getStatus().setMessage("Thêm mới thành công");
        }
        repository.save(info2);
        return response;
    }

    public ListStudentResponse listStudent(String username) {
        ListStudentResponse response = new ListStudentResponse();
        response.getStatus().setCode(Status.SUCCESS);
        if (StringUtils.isBlank(username)) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Người dùng không đúng");
            return response;
        }
        List<StudentInfo> datas = repository.findAll();
        response.setDatas(datas);
        response.getStatus().setMessage("Truy vấn thành công");
        return response;
    }

    public BaseResponse updateStatus(UpdateStatusRequest info, String username) {
        BaseResponse response = new BaseResponse();
        response.getStatus().setCode(Status.SUCCESS);
        if (StringUtils.isBlank(username)) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Người dùng không đúng");
            return response;
        }
        Optional<StudentInfo> data = repository.findById(Integer.parseInt(info.getId()));
        if (data.isPresent()) {
            StudentInfo info2 = data.get();
            info2.setTrangThai(info.getStatus().getValue());
            info2.setNguoiCapNhat(username);
            info2.setUpdatedAt(new Date());
            repository.save(info2);
            response.getStatus().setMessage("Cập nhật thành công");
        } else {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Id không tồn tại");
        }
        return response;
    }

    public ListStudentResponse listStudentByClass(DetailRequest request) {
        ListStudentResponse response = new ListStudentResponse();
        response.getStatus().setCode(Status.SUCCESS);
        List<StudentInfo> datas = repository.findStudentInfoByLopHoc(request.getId());
        response.setDatas(datas);
        response.getStatus().setMessage("Truy vấn thành công");
        return response;
    }
}
