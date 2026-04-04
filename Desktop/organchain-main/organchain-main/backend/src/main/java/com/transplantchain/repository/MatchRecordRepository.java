package com.transplantchain.repository;

import com.transplantchain.entity.MatchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRecordRepository extends JpaRepository<MatchRecord, Long> {
    List<MatchRecord> findByHospitalId(String hospitalId);
    List<MatchRecord> findByOrderByMatchedAtDesc();
    List<MatchRecord> findTop20ByOrderByMatchedAtDesc();
}
