package com.noom.interview.fullstack.sleep.sleeplog.dto;

import com.noom.interview.fullstack.sleep.sleeplog.models.MorningFeeling;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SleepLogDTO {
    private Long id;
    private LocalDateTime sleepDate;
    private TimeInBedIntervalDTO timeInBedInterval;
    private long totalTimeInBedSeconds;
    private MorningFeeling morningFeeling;
    private Long userId;
}
