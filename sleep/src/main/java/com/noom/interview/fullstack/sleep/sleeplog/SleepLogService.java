package com.noom.interview.fullstack.sleep.sleeplog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SleepLogService {
    private final SleepLogRepository sleepLogRepository;

    @Autowired
    public SleepLogService(SleepLogRepository sleepLogRepository) {
        this.sleepLogRepository = sleepLogRepository;
    }


}
