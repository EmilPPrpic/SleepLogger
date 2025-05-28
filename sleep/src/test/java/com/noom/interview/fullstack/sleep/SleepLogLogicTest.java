package com.noom.interview.fullstack.sleep;

import com.noom.interview.fullstack.sleep.sleeplog.Utils.ConversionUtil;
import com.noom.interview.fullstack.sleep.sleeplog.dto.SleepLogAveragesDTO;
import com.noom.interview.fullstack.sleep.sleeplog.dto.SleepLogDTO;
import com.noom.interview.fullstack.sleep.sleeplog.dto.TimeInBedIntervalDTO;
import com.noom.interview.fullstack.sleep.sleeplog.models.MorningFeeling;
import com.noom.interview.fullstack.sleep.sleeplog.models.SleepLog;
import com.noom.interview.fullstack.sleep.sleeplog.models.TimeInBedInterval;
import com.noom.interview.fullstack.sleep.user.models.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


class SleepLogLogicTest {

    @Test
    void sleepLogToDTOConversionTest() {
        SleepLog sleepLog = new SleepLog();
        TimeInBedInterval timeInBedInterval = new TimeInBedInterval();
        User user = new User(1L, "TestUser", null);
        timeInBedInterval.setStartTime(LocalDateTime.of(2025, 10, 1, 22, 0));
        timeInBedInterval.setEndTime(LocalDateTime.of(2025, 10, 2, 22, 0));
        sleepLog.setId(1L);
        sleepLog.setTotalTimeInBedSeconds(20000);
        sleepLog.setMorningFeeling(MorningFeeling.BAD);
        sleepLog.setTimeInBedInterval(timeInBedInterval);
        sleepLog.setUser(user);

        SleepLogDTO sleepLogDTO = ConversionUtil.convertToSleepLogDTO(sleepLog);

        assert sleepLogDTO != null;
        assert sleepLogDTO.getTotalTimeInBedSeconds() == 20000;
        assert sleepLogDTO.getMorningFeeling() == MorningFeeling.BAD;
        assert sleepLogDTO.getTimeInBedInterval() != null;
        assert sleepLogDTO.getTimeInBedInterval().getStartTime().equals(LocalDateTime.of(2025, 10, 1, 22, 0));
        assert sleepLogDTO.getTimeInBedInterval().getEndTime().equals(LocalDateTime.of(2025, 10, 2, 22, 0));
        assert sleepLogDTO.getSleepDate().equals(sleepLog.getSleepDate());

    }

    @Test
    void sleepLogDTOToSleepLogConversionTest() {
        SleepLogDTO sleepLogDTO = new SleepLogDTO();
        sleepLogDTO.setId(1L);
        sleepLogDTO.setTotalTimeInBedSeconds(20000);
        sleepLogDTO.setMorningFeeling(MorningFeeling.BAD);
        TimeInBedIntervalDTO timeInBedInterval = new TimeInBedIntervalDTO();
        timeInBedInterval.setStartTime(LocalDateTime.of(2025, 10, 1, 22, 0));
        timeInBedInterval.setEndTime(LocalDateTime.of(2025, 10, 2, 22, 0));
        sleepLogDTO.setTimeInBedInterval(timeInBedInterval);

        SleepLog sleepLog = ConversionUtil.convertToSleepLog(sleepLogDTO);

        assert sleepLog != null;
        assert sleepLog.getTotalTimeInBedSeconds() == 20000;
        assert sleepLog.getMorningFeeling() == MorningFeeling.BAD;
        assert sleepLog.getTimeInBedInterval() != null;
        assert sleepLog.getTimeInBedInterval().getStartTime().equals(LocalDateTime.of(2025, 10, 1, 22, 0));
        assert sleepLog.getTimeInBedInterval().getEndTime().equals(LocalDateTime.of(2025, 10, 2, 22, 0));
    }

    @Test
    void testSleepLogAveragesCalculation() {
        User user = new User(1L, "TestUser", null);

        TimeInBedInterval interval1 = new TimeInBedInterval(0,
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0)
                );

        TimeInBedInterval interval2 = new TimeInBedInterval(1,
                LocalDateTime.of(2025, 10, 2, 22, 0),
                LocalDateTime.of(2025, 10, 3, 6, 20)
                );

        TimeInBedInterval interval3 = new TimeInBedInterval(2,
                LocalDateTime.of(2025, 10, 3, 22, 0),
                LocalDateTime.of(2025, 10, 4, 5, 30));

        List<SleepLog> sleepLogs = new ArrayList<>(List.of(
                new SleepLog(1L, LocalDateTime.of(2025, 10, 1, 22, 0), interval1, 28800, MorningFeeling.GOOD, user),
                new SleepLog(2L, LocalDateTime.of(2025, 10, 2, 22, 0), interval2, 30000, MorningFeeling.OK, user),
                new SleepLog(3L, LocalDateTime.of(2025, 10, 3, 22, 0), interval3, 27000, MorningFeeling.BAD, user)
        ));

        SleepLogAveragesDTO averages = SleepLogAveragesDTO.fromLogs(sleepLogs);
        
        assert averages != null;
        assert averages.getStartDate().equals(LocalDateTime.of(2025, 10, 1, 22, 0).toLocalDate());
        assert averages.getEndDate().equals(LocalDateTime.of(2025, 10, 3, 22, 0).toLocalDate());
        assert averages.getAverageTotalTimeInBedSeconds() == (28800 + 30000 + 27000) / 3.0;
        assert averages.getAverageGoToBedTime() == 
                (interval1.getStartTime().toLocalTime().toSecondOfDay() + 
                 interval2.getStartTime().toLocalTime().toSecondOfDay() + 
                 interval3.getStartTime().toLocalTime().toSecondOfDay()) / 3.0;
        assert averages.getAverageWakeUpTime() ==
                (interval1.getEndTime().toLocalTime().toSecondOfDay() + 
                 interval2.getEndTime().toLocalTime().toSecondOfDay() + 
                 interval3.getEndTime().toLocalTime().toSecondOfDay()) / 3.0;
        assert averages.getFeelingCounts().get(MorningFeeling.GOOD) == 1.0;
        assert averages.getFeelingCounts().get(MorningFeeling.OK) == 1.0;
        assert averages.getFeelingCounts().get(MorningFeeling.BAD) == 1.0;
        
        
    }

}