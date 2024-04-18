//package com.javatechie.healthcheck;
//
//import org.springframework.boot.actuate.health.Health;
//import org.springframework.boot.actuate.health.HealthIndicator;
//import org.springframework.stereotype.Component;
//
///**
// * A custom health check indicator that checks the amount of free memory in the JVM.
// */
//@Component
//public class ServiceHealthIndicator implements HealthIndicator {
//
//    @Override
//    public Health health() {
//        int errorCode = check();
//        if (errorCode != 0) {
//            return Health.outOfService().withDetail("Error Code", errorCode).build();
//        }
//        return Health.up().build();
//    }
//
//    private int check() {
//        return 1;
//    }
//}