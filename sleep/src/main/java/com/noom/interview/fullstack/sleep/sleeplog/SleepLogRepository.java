package com.noom.interview.fullstack.sleep.sleeplog;

import com.noom.interview.fullstack.sleep.sleeplog.models.SleepLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SleepLogRepository extends CrudRepository<SleepLog, Long> {
    Optional<SleepLog> findFirstByUserIdOrderBySleepDateDesc(Long userId);

    @Query(value = "SELECT * FROM sleep_log WHERE user_id = :userId ORDER BY sleep_date DESC LIMIT 30", nativeQuery = true)
    List<SleepLog> findLast30ByUserId(@Param("userId") Long userId);
}
