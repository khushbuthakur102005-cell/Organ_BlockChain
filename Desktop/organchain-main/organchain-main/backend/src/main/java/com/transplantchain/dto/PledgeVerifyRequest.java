package com.transplantchain.dto;

public class PledgeVerifyRequest {
    private String abhaHash;
    private String documentHash;
    private String cid;
    private int organBitmap;
    private String witnessSignature;

    private String abhaId;
    private String witnessName;
    private String witnessContact;
    private String patientName;
    private int patientAge;
    private String bloodGroup;

    // Phase 12 additions
    private String hospitalId;
    private String hospitalName;
    private String hlaMarkers;
    private String organ;
    private String role;

    // Getters and Setters
    public String getAbhaHash() { return abhaHash; }
    public void setAbhaHash(String abhaHash) { this.abhaHash = abhaHash; }

    public String getDocumentHash() { return documentHash; }
    public void setDocumentHash(String documentHash) { this.documentHash = documentHash; }

    public String getCid() { return cid; }
    public void setCid(String cid) { this.cid = cid; }

    public int getOrganBitmap() { return organBitmap; }
    public void setOrganBitmap(int organBitmap) { this.organBitmap = organBitmap; }

    public String getWitnessSignature() { return witnessSignature; }
    public void setWitnessSignature(String witnessSignature) { this.witnessSignature = witnessSignature; }

    public String getAbhaId() { return abhaId; }
    public void setAbhaId(String abhaId) { this.abhaId = abhaId; }

    public String getWitnessName() { return witnessName; }
    public void setWitnessName(String witnessName) { this.witnessName = witnessName; }

    public String getWitnessContact() { return witnessContact; }
    public void setWitnessContact(String witnessContact) { this.witnessContact = witnessContact; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public int getPatientAge() { return patientAge; }
    public void setPatientAge(int patientAge) { this.patientAge = patientAge; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

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
}
