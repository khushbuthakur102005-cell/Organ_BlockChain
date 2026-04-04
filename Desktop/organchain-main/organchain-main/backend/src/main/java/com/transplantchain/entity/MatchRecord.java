package com.transplantchain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MatchRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorAbhaHash;
    private String recipientAbhaHash;
    private String donorName;
    private String recipientName;
    private String donorBloodGroup;
    private String recipientBloodGroup;
    private Integer donorAge;
    private Integer recipientAge;
    private String donorHla;
    private String recipientHla;

    private String hospitalId;
    private String hospitalName;
    private String organType;
    private Double compatibilityScore;

    private String transactionHash;
    private Long blockNumber;
    private String status;          // CONFIRMED, PENDING, MANUAL_OVERRIDE
    private LocalDateTime matchedAt;

    private String matchType;       // AI_RECOMMENDED, MANUAL

    public MatchRecord() {
        this.matchedAt = LocalDateTime.now();
        this.status = "CONFIRMED";
        this.matchType = "AI_RECOMMENDED";
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getDonorAbhaHash() { return donorAbhaHash; }
    public void setDonorAbhaHash(String donorAbhaHash) { this.donorAbhaHash = donorAbhaHash; }

    public String getRecipientAbhaHash() { return recipientAbhaHash; }
    public void setRecipientAbhaHash(String recipientAbhaHash) { this.recipientAbhaHash = recipientAbhaHash; }

    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }

    public String getRecipientName() { return recipientName; }
    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }

    public String getDonorBloodGroup() { return donorBloodGroup; }
    public void setDonorBloodGroup(String donorBloodGroup) { this.donorBloodGroup = donorBloodGroup; }

    public String getRecipientBloodGroup() { return recipientBloodGroup; }
    public void setRecipientBloodGroup(String recipientBloodGroup) { this.recipientBloodGroup = recipientBloodGroup; }

    public Integer getDonorAge() { return donorAge; }
    public void setDonorAge(Integer donorAge) { this.donorAge = donorAge; }

    public Integer getRecipientAge() { return recipientAge; }
    public void setRecipientAge(Integer recipientAge) { this.recipientAge = recipientAge; }

    public String getDonorHla() { return donorHla; }
    public void setDonorHla(String donorHla) { this.donorHla = donorHla; }

    public String getRecipientHla() { return recipientHla; }
    public void setRecipientHla(String recipientHla) { this.recipientHla = recipientHla; }

    public String getHospitalId() { return hospitalId; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }

    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }

    public String getOrganType() { return organType; }
    public void setOrganType(String organType) { this.organType = organType; }

    public Double getCompatibilityScore() { return compatibilityScore; }
    public void setCompatibilityScore(Double compatibilityScore) { this.compatibilityScore = compatibilityScore; }

    public String getTransactionHash() { return transactionHash; }
    public void setTransactionHash(String transactionHash) { this.transactionHash = transactionHash; }

    public Long getBlockNumber() { return blockNumber; }
    public void setBlockNumber(Long blockNumber) { this.blockNumber = blockNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getMatchedAt() { return matchedAt; }
    public void setMatchedAt(LocalDateTime matchedAt) { this.matchedAt = matchedAt; }

    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }
}
