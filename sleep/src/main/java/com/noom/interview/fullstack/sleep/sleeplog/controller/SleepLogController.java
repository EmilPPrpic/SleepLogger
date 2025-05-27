package com.noom.interview.fullstack.sleep.sleeplog.controller;

import com.noom.interview.fullstack.sleep.sleeplog.SleepLogService;
import com.noom.interview.fullstack.sleep.sleeplog.dto.SleepLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sleeplog")
public class SleepLogController {

    private final SleepLogService sleepLogService;

    @Autowired
    public SleepLogController(SleepLogService sleepLogService) {
        this.sleepLogService = sleepLogService;
    }

    @PostMapping("/create")
    public ResponseEntity<SleepLogDTO> createSleepLog(@RequestBody SleepLogDTO sleepLogDTO) {
        return ResponseEntity.ok(sleepLogService.createSleepLog(sleepLogDTO));
    }

    @GetMapping("/fetch_last_night")
    public ResponseEntity<SleepLogDTO> fetchLastNightSleepLog() {
        SleepLogDTO sleepLogDTO = sleepLogService.fetchLastNightSleepLog();
        if (sleepLogDTO != null) {
            return ResponseEntity.ok(sleepLogDTO);
        }
        return ResponseEntity.notFound().build();
    }
}