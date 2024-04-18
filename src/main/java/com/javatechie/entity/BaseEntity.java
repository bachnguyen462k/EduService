package com.javatechie.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
@Data
public class BaseEntity {


    @CreatedDate
    private Date ngayTao;
    @CreatedDate
    private Date ngayCapNhat;

    private String nguoiTao;
    private String nguoiCapNhat;
}
