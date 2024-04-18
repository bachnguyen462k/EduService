package com.javatechie.controller;

import com.javatechie.entity.Config;
import com.javatechie.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@CrossOrigin
public class TestController {
    @Autowired
    private ConfigRepository configRepository;

    @GetMapping("/test")
    public String testMethod() {
        log.error("Test error method");
        // Định nghĩa múi giờ của Việt Nam
        ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");

        // Lấy thời gian hiện tại theo múi giờ của Việt Nam
        ZonedDateTime vietnamTime = ZonedDateTime.now(vietnamZone);

        // Lấy ngày hiện tại theo múi giờ của Việt Nam
        LocalDate vietnamDate = LocalDate.now(vietnamZone);
        // Lấy thứ của ngày hiện tại
        DayOfWeek dayOfWeek = vietnamDate.getDayOfWeek();
        // In ra thứ của ngày hiện tại
        Optional<Config> config = configRepository.findByValue(String.valueOf(dayOfWeek.getValue()));
        Config dayOfWeek2 = config.get();
        log.info("Hôm nay là : " + dayOfWeek2.getMoTa());
        return "Hôm nay là : " + dayOfWeek2.getMoTa();
    }
}
