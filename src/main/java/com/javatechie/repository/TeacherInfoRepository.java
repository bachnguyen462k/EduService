package com.javatechie.repository;

import com.javatechie.entity.AdminInfo;
import com.javatechie.entity.TeacherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherInfoRepository extends JpaRepository<TeacherInfo, Integer> {
}
