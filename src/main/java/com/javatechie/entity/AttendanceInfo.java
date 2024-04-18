package com.javatechie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    private Date ngayDiemDanh;

    private String nguoiDiemDanh;

    private String caHoc;
    private String lopHoc;
    private String maHocSinh;
    private BigDecimal giaTien;
    private String thuHoc="";
    private String idDateSchedule="";
    private String studentName="";
    private String status="";
}
