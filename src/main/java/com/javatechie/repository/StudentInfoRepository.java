package com.javatechie.repository;

import com.javatechie.entity.StudentInfo;
import com.javatechie.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer> {
    List<StudentInfo> findStudentInfoByLopHoc(String id);
}
