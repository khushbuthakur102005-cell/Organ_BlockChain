package com.transplantchain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIEnhancementService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String FLASK_BASE_URL = "http://127.0.0.1:5000";

    public String maskPii(String rawText) {
        String url = FLASK_BASE_URL + "/mask-pii";
        Map<String, String> request = new HashMap<>();
        request.put("text", rawText);
        
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return (String) response.getBody().get("safe_text");
            }
        } catch (Exception e) {
            System.err.println("Error calling Flask AI Microservice: " + e.getMessage());
        }
        return rawText; // Return original on fallback
    }

    public double predictViability(int donorAge, double ischemicTimeHours, int hlaMismatchScore) {
        String url = FLASK_BASE_URL + "/predict-viability";
        Map<String, Object> request = new HashMap<>();
        request.put("donor_age", donorAge);
        request.put("ischemic_time_hours", ischemicTimeHours);
        request.put("hla_mismatch_score", hlaMismatchScore);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Object percent = response.getBody().get("success_probability_percent");
                if (percent instanceof Number) {
                    return ((Number) percent).doubleValue();
                }
            }
        } catch (Exception e) {
            System.err.println("Error calling Flask AI Microservice: " + e.getMessage());
        }
        return 0.0;
    }
}
