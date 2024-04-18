package com.javatechie.entity;

import com.javatechie.model.request.ClassNewRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tenLop;

    @CreatedDate
    private Date ngayTao;
    @LastModifiedDate
    private Date ngayCapNhat;
    private String nguoiTao;
    private String nguoiCapNhat;
    private String giaoVienDay;
    private String troGiang;

    private Date ngayBatDau;
    private String status;
    private Date ngayKetThuc;

    public ClassInfo(ClassNewRequest info, String username) {
        this.tenLop = info.getTenLop();
        this.ngayTao = new Date();
        this.nguoiTao = username;
        this.giaoVienDay = info.getGiaoVienDay();
        this.troGiang = info.getTroGiang();
        this.ngayBatDau = info.getNgayBatDau();
        this.ngayKetThuc = info.getNgayKetThuc();
    }
}
