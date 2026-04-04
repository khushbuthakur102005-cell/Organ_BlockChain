package com.transplantchain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SecurityLog {
    @Id
    private String eventId;
    private String type;
    private String sourceIp;
    private String riskLevel;
    private String timestamp;
    private String status; // ACTIVE, BANNED, DISMISSED
    private String reason;

    public SecurityLog() {}
    public SecurityLog(String eventId, String type, String sourceIp, String riskLevel, String timestamp, String status, String reason) {
        this.eventId = eventId; this.type = type; this.sourceIp = sourceIp; this.riskLevel = riskLevel; this.timestamp = timestamp; this.status = status; this.reason = reason;
    }

    public String getEventId() { return eventId; }
    public String getType() { return type; }
    public String getSourceIp() { return sourceIp; }
    public String getRiskLevel() { return riskLevel; }
    public String getTimestamp() { return timestamp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReason() { return reason; }
}
