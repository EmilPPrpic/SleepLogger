package com.noom.interview.fullstack.sleep.sleeplog;

import com.noom.interview.fullstack.sleep.sleeplog.models.SleepLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepLogRepository extends CrudRepository<SleepLog, Long> {
}
