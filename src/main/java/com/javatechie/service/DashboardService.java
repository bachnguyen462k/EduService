package com.javatechie.service;

import com.javatechie.model.response.DateScheduleNewsResponse;

public interface DashboardService {
    DateScheduleNewsResponse dateSchedule(String user);
}
