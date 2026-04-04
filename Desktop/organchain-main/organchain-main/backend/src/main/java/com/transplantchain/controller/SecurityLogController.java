package com.transplantchain.controller;

import com.transplantchain.entity.SecurityLog;
import com.transplantchain.repository.SecurityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin/security-logs")
public class SecurityLogController {

    @Autowired
    private SecurityLogRepository securityLogRepository;

    @GetMapping
    public ResponseEntity<List<SecurityLog>> getLogs() {
        return ResponseEntity.ok(securityLogRepository.findByStatus("ACTIVE"));
    }

    @PostMapping("/{id}/ban")
    public ResponseEntity<String> banIp(@PathVariable String id) {
        SecurityLog log = securityLogRepository.findById(id).orElse(null);
        if (log != null) {
            log.setStatus("BANNED");
            securityLogRepository.save(log);
        }
        return ResponseEntity.ok("Banned");
    }

    @PostMapping("/{id}/dismiss")
    public ResponseEntity<String> dismissLog(@PathVariable String id) {
        SecurityLog log = securityLogRepository.findById(id).orElse(null);
        if (log != null) {
            log.setStatus("DISMISSED");
            securityLogRepository.save(log);
        }
        return ResponseEntity.ok("Dismissed");
    }
}
