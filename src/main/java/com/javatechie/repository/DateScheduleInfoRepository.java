package com.javatechie.repository;

import com.javatechie.entity.DateScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateScheduleInfoRepository extends JpaRepository<DateScheduleInfo,Integer> {
    List<DateScheduleInfo> findByIdLop(String idLop);
    List<DateScheduleInfo> findByValueNgayHoc(String id);
}
