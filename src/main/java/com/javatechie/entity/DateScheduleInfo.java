package com.javatechie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateScheduleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String idLop;
    private String ngayHoc;
    private String valueNgayHoc;
    private String thoiGianHoc;
    private String caHoc;

    @CreatedDate
    private Date ngayTao;
    private String nguoiTao;
    @LastModifiedDate
    private Date ngayCapNhat;
    private String nguoiCapNhat;
    private String moTa;
    private String status;
}
