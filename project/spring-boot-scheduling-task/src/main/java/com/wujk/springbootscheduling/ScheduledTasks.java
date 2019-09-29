package com.wujk.springbootscheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(cron="0/2 * * * * ?")
    private void task() {
        System.out.println("task is running...");
    }

}
