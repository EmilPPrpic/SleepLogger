package com.noom.interview.fullstack.sleep.sleeplog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeInBedInterval {
    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}