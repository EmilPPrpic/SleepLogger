package com.noom.interview.fullstack.sleep.sleeplog.models;

import com.noom.interview.fullstack.sleep.user.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SleepLog {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime sleepDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_in_bed_id")
    private TimeInBedInterval timeInBedInterval;

    @Column(nullable = false)
    private long totalTimeInBedSeconds;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MorningFeeling morningFeeling;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
