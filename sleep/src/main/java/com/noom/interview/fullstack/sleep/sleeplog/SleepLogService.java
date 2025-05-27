package com.noom.interview.fullstack.sleep.sleeplog;

import com.noom.interview.fullstack.sleep.sleeplog.Utils.ConversionUtil;
import com.noom.interview.fullstack.sleep.sleeplog.dto.SleepLogDTO;
import com.noom.interview.fullstack.sleep.sleeplog.models.SleepLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SleepLogService {
    private final SleepLogRepository sleepLogRepository;

    @Autowired
    public SleepLogService(SleepLogRepository sleepLogRepository) {
        this.sleepLogRepository = sleepLogRepository;
    }


    public SleepLogDTO createSleepLog(SleepLogDTO sleepLogDTO) {
        SleepLog sleepLog = ConversionUtil.convertToSleepLog(sleepLogDTO);
        sleepLogRepository.save(sleepLog);
        return ConversionUtil.convertToSleepLogDTO(sleepLog);
    }

    public SleepLogDTO fetchLastNightSleepLog() {
        // TODO: Implement logic to fetch the last night's sleep log
        return null;
    }

}
