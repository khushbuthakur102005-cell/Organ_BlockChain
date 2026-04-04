package com.transplantchain.entity;

import jakarta.persistence.*;

@Entity
public class PledgeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String abhaId;
    private String abhaHash;
    private String documentHash;
    private String ipfsCid;
    private Integer organBitmap;
    private String witnessName;
    private String witnessContact;
    private String transactionHash;

    private String patientName;
    private Integer patientAge;
    private String bloodGroup;

    // ── Phase 12 additions ──
    private String hospitalId;
    private String hospitalName;
    private String hlaMarkers;   // comma-separated, e.g. "A1,A2,B7,DR3,DR4,B44"
    private String organ;        // e.g. "Kidney", "Liver"
    private String role;         // "DONOR" or "RECIPIENT"
    private Long blockNumber;

    public PledgeRecord() {}

    // ── Getters & Setters ──
    public Long getId() { return id; }
    public String getAbhaId() { return abhaId; }
    public void setAbhaId(String abhaId) { this.abhaId = abhaId; }
    public String getAbhaHash() { return abhaHash; }
    public void setAbhaHash(String abhaHash) { this.abhaHash = abhaHash; }
    public String getDocumentHash() { return documentHash; }
    public void setDocumentHash(String documentHash) { this.documentHash = documentHash; }
    public String getIpfsCid() { return ipfsCid; }
    public void setIpfsCid(String ipfsCid) { this.ipfsCid = ipfsCid; }
    public Integer getOrganBitmap() { return organBitmap; }
    public void setOrganBitmap(Integer organBitmap) { this.organBitmap = organBitmap; }
    public String getWitnessName() { return witnessName; }
    public void setWitnessName(String witnessName) { this.witnessName = witnessName; }
    public String getWitnessContact() { return witnessContact; }
    public void setWitnessContact(String witnessContact) { this.witnessContact = witnessContact; }
    public String getTransactionHash() { return transactionHash; }
    public void setTransactionHash(String transactionHash) { this.transactionHash = transactionHash; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public Integer getPatientAge() { return patientAge; }
    public void setPatientAge(Integer patientAge) { this.patientAge = patientAge; }
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    // Phase 12
    public String getHospitalId() { return hospitalId; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }
    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }
    public String getHlaMarkers() { return hlaMarkers; }
    public void setHlaMarkers(String hlaMarkers) { this.hlaMarkers = hlaMarkers; }
    public String getOrgan() { return organ; }
    public void setOrgan(String organ) { this.organ = organ; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Long getBlockNumber() { return blockNumber; }
    public void setBlockNumber(Long blockNumber) { this.blockNumber = blockNumber; }
}
