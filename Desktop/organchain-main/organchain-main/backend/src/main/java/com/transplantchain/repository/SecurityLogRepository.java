package com.transplantchain.repository;

import com.transplantchain.entity.SecurityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityLogRepository extends JpaRepository<SecurityLog, String> {
    List<SecurityLog> findByStatus(String status);
}
