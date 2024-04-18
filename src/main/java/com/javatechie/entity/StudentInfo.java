package com.javatechie.entity;

import com.javatechie.model.request.StudentNewRequest;
import com.javatechie.utils.Utility;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String trangThai;
    @CreatedDate
    private Date ngayTao;

    private String anh;
    private String nguoiTao;


    private String lopHoc;
    private String nguoiCapNhat;
    @LastModifiedDate
    private Date updatedAt;

    public StudentInfo(StudentNewRequest info,String username) {
        this.hoTen=info.getHoTen();
        this.gioiTinh=info.getGioiTinh();
        this.ngaySinh=info.getNgaySinh();
        this.trangThai=info.getTrangThai();
        this.anh=info.getAnh();
        this.lopHoc=info.getLopHoc();

    }
}
