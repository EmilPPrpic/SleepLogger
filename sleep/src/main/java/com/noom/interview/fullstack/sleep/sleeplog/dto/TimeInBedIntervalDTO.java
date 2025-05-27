package com.noom.interview.fullstack.sleep.sleeplog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeInBedIntervalDTO {
    private long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
