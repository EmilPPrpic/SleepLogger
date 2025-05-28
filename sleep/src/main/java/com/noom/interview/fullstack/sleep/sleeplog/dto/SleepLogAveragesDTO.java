package com.noom.interview.fullstack.sleep.sleeplog.dto;

import com.noom.interview.fullstack.sleep.sleeplog.models.MorningFeeling;
import com.noom.interview.fullstack.sleep.sleeplog.models.SleepLog;
import lombok.Data;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Data
public class SleepLogAveragesDTO {
    LocalDate startDate;
    LocalDate endDate;
    double averageTotalTimeInBedSeconds;
    double averageGoToBedTime;  // in seconds from midnight
    double averageWakeUpTime;   // in seconds from midnight
    Map<MorningFeeling, Double> feelingCounts;

    public static SleepLogAveragesDTO fromLogs(List<SleepLog> sleepLogs) {
        if (sleepLogs == null || sleepLogs.isEmpty()) {
            return null;
        }
        SleepLogAveragesDTO averages = new SleepLogAveragesDTO();

        sleepLogs.sort(Comparator.comparing(SleepLog::getSleepDate));
        averages.startDate = sleepLogs.get(0).getSleepDate().toLocalDate();
        averages.endDate = sleepLogs.get(sleepLogs.size() - 1).getSleepDate().toLocalDate();

        double totalTimeInBedSum = 0;
        double totalGoToBedSeconds = 0;
        double totalWakeUpSeconds = 0;

        Map<MorningFeeling, Double> feelingCounts = new EnumMap<>(MorningFeeling.class);
        for (MorningFeeling feeling : MorningFeeling.values()) {
            feelingCounts.put(feeling, 0.0);
        }

        for (SleepLog log : sleepLogs) {
            totalTimeInBedSum += log.getTotalTimeInBedSeconds();
            totalGoToBedSeconds += log.getTimeInBedInterval().getStartTime().toLocalTime().toSecondOfDay();
            totalWakeUpSeconds += log.getTimeInBedInterval().getEndTime().toLocalTime().toSecondOfDay();


            MorningFeeling feeling = log.getMorningFeeling();
            feelingCounts.put(feeling, feelingCounts.get(feeling) + 1.0);
        }

        int totalLogs = sleepLogs.size();
        averages.averageTotalTimeInBedSeconds = totalTimeInBedSum / totalLogs;
        averages.averageGoToBedTime = totalGoToBedSeconds / totalLogs;
        averages.averageWakeUpTime = totalWakeUpSeconds / totalLogs;
        averages.feelingCounts = new EnumMap<>(feelingCounts);
        return averages;
    }

}
