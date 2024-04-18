package com.javatechie.service.impl;

import com.javatechie.entity.Config;
import com.javatechie.entity.DateScheduleInfo;
import com.javatechie.model.base.Status;
import com.javatechie.model.response.DateScheduleNewsResponse;
import com.javatechie.repository.ConfigRepository;
import com.javatechie.repository.DateScheduleInfoRepository;
import com.javatechie.service.DashboardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private DateScheduleInfoRepository dateScheduleInfoRepository;

    @Override
    public DateScheduleNewsResponse dateSchedule(String user) {
        DateScheduleNewsResponse response = new DateScheduleNewsResponse();
        response.getStatus().setCode(Status.SUCCESS);
        // lay lich hoc ngay hom nay


        // Định nghĩa múi giờ của Việt Nam
        ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");

        // Lấy ngày hiện tại theo múi giờ của Việt Nam
        LocalDate vietnamDate = LocalDate.now(vietnamZone);
        // Lấy thứ của ngày hiện tại
        DayOfWeek dayOfWeek = vietnamDate.getDayOfWeek();
        List<DateScheduleInfo> dateScheduleInfos = dateScheduleInfoRepository.findByValueNgayHoc(String.valueOf(dayOfWeek.getValue()));
        for (DateScheduleInfo xx: dateScheduleInfos){

        }
        // In ra thứ của ngày hiện tại
        Optional<Config> config = configRepository.findByValue(String.valueOf(dayOfWeek.getValue()));
        Config dayOfWeek2 = config.get();
        log.info("Hôm nay là : " + dayOfWeek2.getMoTa());

        return response;
    }
}
