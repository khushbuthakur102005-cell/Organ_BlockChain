package com.transplantchain.repository;

import com.transplantchain.entity.PledgeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PledgeRecordRepository extends JpaRepository<PledgeRecord, Long> {
    List<PledgeRecord> findTop15ByOrderByIdDesc();
    List<PledgeRecord> findByHospitalId(String hospitalId);
    List<PledgeRecord> findByRole(String role);
    List<PledgeRecord> findByHospitalIdAndRole(String hospitalId, String role);
}
