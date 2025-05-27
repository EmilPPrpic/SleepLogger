package com.noom.interview.fullstack.sleep.sleeplog.Utils;

import com.noom.interview.fullstack.sleep.sleeplog.dto.SleepLogDTO;
import com.noom.interview.fullstack.sleep.sleeplog.dto.TimeInBedIntervalDTO;
import com.noom.interview.fullstack.sleep.sleeplog.models.SleepLog;
import com.noom.interview.fullstack.sleep.sleeplog.models.TimeInBedInterval;

public class ConversionUtil {

    public static SleepLogDTO convertToSleepLogDTO(SleepLog sleepLog) {
        if (sleepLog == null) {
            return null;
        }
        SleepLogDTO sleepLogDTO = new SleepLogDTO();
        sleepLogDTO.setId(sleepLog.getId());
        sleepLogDTO.setSleepDate(sleepLog.getSleepDate());
        sleepLogDTO.setTimeInBedInterval(convertToTimeInBedIntervalDTO(sleepLog.getTimeInBedInterval()));
        sleepLogDTO.setTotalTimeInBedSeconds(sleepLog.getTotalTimeInBedSeconds());
        sleepLogDTO.setMorningFeeling(sleepLog.getMorningFeeling());
        return sleepLogDTO;
    }

    public static SleepLog convertToSleepLog(SleepLogDTO sleepLogDTO) {
        if (sleepLogDTO == null) {
            return null;
        }
        SleepLog sleepLog = new SleepLog();
        sleepLog.setId(sleepLogDTO.getId());
        sleepLog.setSleepDate(sleepLogDTO.getSleepDate());
        sleepLog.setTimeInBedInterval(convertToTimeInBedInterval(sleepLogDTO.getTimeInBedInterval()));
        sleepLog.setTotalTimeInBedSeconds(sleepLogDTO.getTotalTimeInBedSeconds());
        sleepLog.setMorningFeeling(sleepLogDTO.getMorningFeeling());
        return sleepLog;
    }

    public static TimeInBedIntervalDTO convertToTimeInBedIntervalDTO(TimeInBedInterval timeInBedInterval) {
        if (timeInBedInterval == null) {
            return null;
        }
        TimeInBedIntervalDTO timeInBedIntervalDTO = new TimeInBedIntervalDTO();
        timeInBedIntervalDTO.setStartTime(timeInBedInterval.getStartTime());
        timeInBedIntervalDTO.setEndTime(timeInBedInterval.getEndTime());
        return timeInBedIntervalDTO;
    }

    public static TimeInBedInterval convertToTimeInBedInterval(TimeInBedIntervalDTO timeInBedIntervalDTO) {
        if (timeInBedIntervalDTO == null) {
            return null;
        }
        TimeInBedInterval timeInBedInterval = new TimeInBedInterval();
        timeInBedInterval.setStartTime(timeInBedIntervalDTO.getStartTime());
        timeInBedInterval.setEndTime(timeInBedIntervalDTO.getEndTime());
        return timeInBedInterval;
    }


}
