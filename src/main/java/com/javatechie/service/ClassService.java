package com.javatechie.service;

import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.request.ClassNewRequest;
import com.javatechie.model.request.DetailRequest;
import com.javatechie.model.request.UpdateStatusRequest;
import com.javatechie.model.response.DetailClassInfoResponse;
import com.javatechie.model.response.ListClassInfoResponse;

public interface ClassService {
    BaseResponse addNew(ClassNewRequest classNewRequest, String user);

    ListClassInfoResponse listStudent(String user);

    BaseResponse updateStatus(UpdateStatusRequest studentInfo, String user);

    DetailClassInfoResponse detail(DetailRequest request);
}
