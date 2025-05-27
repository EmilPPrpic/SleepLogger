package com.noom.interview.fullstack.sleep.sleeplog;

import com.noom.interview.fullstack.sleep.sleeplog.Utils.ConversionUtil;
import com.noom.interview.fullstack.sleep.sleeplog.dto.SleepLogDTO;
import com.noom.interview.fullstack.sleep.sleeplog.models.SleepLog;
import com.noom.interview.fullstack.sleep.user.UserRepository;
import com.noom.interview.fullstack.sleep.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SleepLogService {
    private final SleepLogRepository sleepLogRepository;
    private final UserRepository userRepository;

    @Autowired
    public SleepLogService(SleepLogRepository sleepLogRepository, UserRepository userRepository) {
        this.sleepLogRepository = sleepLogRepository;
        this.userRepository = userRepository;
    }


    public SleepLogDTO createSleepLog(SleepLogDTO sleepLogDTO, long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        SleepLog sleepLog = ConversionUtil.convertToSleepLog(sleepLogDTO);
        sleepLog.setUser(user);
        SleepLog savedSleepLog = sleepLogRepository.save(sleepLog);
        return ConversionUtil.convertToSleepLogDTO(savedSleepLog);
    }

    public SleepLogDTO fetchLastNightSleepLog(long userId) {
        return sleepLogRepository.findFirstByUserIdOrderBySleepDateDesc(userId)
                .map(ConversionUtil::convertToSleepLogDTO)
                .orElse(null);

    }
}
