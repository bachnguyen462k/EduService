package com.javatechie.repository;

import com.javatechie.entity.AttendanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceInforepository extends JpaRepository<AttendanceInfo, Integer> {
}
