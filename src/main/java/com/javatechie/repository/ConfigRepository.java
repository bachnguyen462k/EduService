package com.javatechie.repository;

import com.javatechie.entity.AdminInfo;
import com.javatechie.entity.Config;
import com.javatechie.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConfigRepository extends JpaRepository<Config, Integer> {
    Optional<Config> findByCode(String username);
    Optional<Config> findByValue(String value);
}
