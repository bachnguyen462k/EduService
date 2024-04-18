package com.javatechie.model.request;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
public class StudentNewRequest {
    private String id;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String trangThai;
    private String anh;
    private String lopHoc;
}
