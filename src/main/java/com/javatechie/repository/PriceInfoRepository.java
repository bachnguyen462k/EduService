package com.javatechie.repository;

import com.javatechie.entity.Config;
import com.javatechie.entity.PriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceInfoRepository extends JpaRepository<PriceInfo, Integer> {
    PriceInfo findByLopHoc(String idClass);
}
