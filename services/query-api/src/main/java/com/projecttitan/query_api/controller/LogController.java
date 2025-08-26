package com.projecttitan.query_api.controller;

import com.projecttitan.query_api.model.Log;
import com.projecttitan.query_api.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {

    private final LogRepository logRepository;

    @Autowired
    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @PostMapping
    public ResponseEntity<Log> createLog(@RequestBody Log log) {
        // Set the timestamp to the current time before saving
        log.setTimestamp(Instant.now());
        Log savedLog = logRepository.save(log);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

    @GetMapping
    public Page<Log> getRecentLogs() {
        // Fetch the most recent 100 logs, sorted by timestamp descending
        return logRepository.findAll(
                PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "timestamp"))
        );
    }
}