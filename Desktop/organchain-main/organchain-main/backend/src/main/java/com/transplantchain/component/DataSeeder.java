package com.transplantchain.component;

import com.transplantchain.entity.MatchRecord;
import com.transplantchain.entity.PledgeRecord;
import com.transplantchain.entity.SecurityLog;
import com.transplantchain.repository.MatchRecordRepository;
import com.transplantchain.repository.PledgeRecordRepository;
import com.transplantchain.repository.SecurityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private SecurityLogRepository securityLogRepository;

    @Autowired
    private PledgeRecordRepository pledgeRecordRepository;

    @Autowired
    private MatchRecordRepository matchRecordRepository;

    @Override
    public void run(String... args) {
        // Security logs
        if (securityLogRepository.count() == 0) {
            securityLogRepository.saveAll(List.of(
                new SecurityLog("SEC-8821", "FAILED_AUTH", "192.168.1.104", "LOW", "2m ago", "ACTIVE", "Error: Multiple failed login attempts detected. Suspected brute-force vector."),
                new SecurityLog("SEC-8820", "SIGNATURE_MISMATCH", "***.***.45.12", "CRITICAL", "14m ago", "ACTIVE", "Error: $AES-256$ Handshake failed. Peer signature does not match Authorized Node Registry."),
                new SecurityLog("SEC-8819", "UNAUTHORIZED_NODE", "10.0.4.111", "MEDIUM", "1h ago", "ACTIVE", "Error: Connection anomaly detected by Intrusion Detection System."),
                new SecurityLog("SEC-8818", "IP_BLACKLIST", "45.2.19.88", "LOW", "3h ago", "ACTIVE", "Error: Packet origin in global deny-list.")
            ));
        }

        // Seed pledge records (donors + recipients across hospitals)
        if (pledgeRecordRepository.count() == 0) {
            String[][] hospitals = {
                {"HOSP-AIIMS-DEL", "AIIMS New Delhi"},
                {"HOSP-APOLLO-CHE", "Apollo Hospitals Chennai"},
                {"HOSP-KEM-MUM", "KEM Hospital Mumbai"},
                {"HOSP-NIMHANS-BAN", "NIMHANS Bangalore"},
                {"HOSP-PGIMER-CHD", "PGIMER Chandigarh"}
            };
            String[] organs = {"Kidney", "Liver", "Heart", "Lung", "Cornea", "Pancreas"};
            String[][] donorData = {
                {"Aarav Sharma", "28", "O+", "A1,A2,B7,DR3,DR4,B44", "1234-5678-9012"},
                {"Vivaan Patel", "35", "A+", "A2,A11,B8,DR1,DR7,B35", "2345-6789-0123"},
                {"Sai Reddy", "42", "B+", "A3,A24,B51,DR4,DR11,B7", "3456-7890-1234"},
                {"Arjun Singh", "31", "O-", "A1,A11,B44,DR3,DR15,B8", "4567-8901-2345"},
                {"Krishna Nair", "45", "AB+", "A2,A3,B7,DR1,DR4,B51", "5678-9012-3456"},
                {"Diya Gupta", "29", "A-", "A1,A24,B35,DR7,DR11,B44", "6789-0123-4567"},
                {"Prisha Iyer", "38", "B-", "A2,A11,B8,DR3,DR15,B7", "7890-1234-5678"},
                {"Rohan Verma", "33", "O+", "A3,A1,B44,DR4,DR7,B35", "8901-2345-6789"},
                {"Meera Joshi", "40", "A+", "A24,A11,B51,DR1,DR11,B8", "9012-3456-7890"},
                {"Rahul Das", "36", "B+", "A1,A2,B7,DR3,DR4,B44", "0123-4567-8901"},
                {"Kavya Mehta", "27", "O-", "A2,A3,B35,DR7,DR15,B51", "1122-3344-5566"},
                {"Nikhil Kapoor", "44", "AB-", "A11,A24,B8,DR1,DR4,B7", "2233-4455-6677"}
            };
            String[][] recipientData = {
                {"Ananya Rao", "32", "O+", "A1,A2,B7,DR3,DR4,B44", "1111-2222-3333"},
                {"Ishaan Kumar", "48", "A+", "A2,A11,B8,DR1,DR7,B35", "2222-3333-4444"},
                {"Zara Chatterjee", "25", "B+", "A3,A24,B51,DR4,DR11,B7", "3333-4444-5555"},
                {"Advik Mishra", "52", "O-", "A1,A11,B44,DR3,DR15,B8", "4444-5555-6666"},
                {"Riya Pillai", "30", "AB+", "A2,A3,B7,DR1,DR4,B51", "5555-6666-7777"},
                {"Anika Shah", "41", "A-", "A1,A24,B35,DR7,DR11,B44", "6666-7777-8888"},
                {"Tara Desai", "37", "B-", "A2,A11,B8,DR3,DR15,B7", "7777-8888-9999"},
                {"Vikram Yadav", "50", "O+", "A3,A1,B44,DR4,DR7,B35", "8888-9999-0000"},
                {"Pooja Chauhan", "34", "A+", "A24,A11,B51,DR1,DR11,B8", "9999-0000-1111"},
                {"Deepak Sinha", "46", "B+", "A1,A2,B7,DR3,DR4,B44", "0000-1111-2222"},
                {"Saanvi Bhat", "26", "O-", "A2,A3,B35,DR7,DR15,B51", "1010-2020-3030"},
                {"Manish Pandey", "55", "AB+", "A11,A24,B8,DR1,DR4,B7", "4040-5050-6060"}
            };

            long blockBase = 14207800;

            // Seed donors
            for (int i = 0; i < donorData.length; i++) {
                PledgeRecord p = new PledgeRecord();
                String[] h = hospitals[i % hospitals.length];
                String[] d = donorData[i];
                p.setPatientName(d[0]);
                p.setPatientAge(Integer.parseInt(d[1]));
                p.setBloodGroup(d[2]);
                p.setHlaMarkers(d[3]);
                p.setAbhaId(d[4]);
                p.setAbhaHash("0x" + UUID.randomUUID().toString().replace("-", ""));
                p.setDocumentHash("0x" + UUID.randomUUID().toString().replace("-", ""));
                p.setIpfsCid("Qm" + UUID.randomUUID().toString().replace("-", ""));
                p.setOrganBitmap(1 << (i % organs.length));
                p.setOrgan(organs[i % organs.length]);
                p.setRole("DONOR");
                p.setHospitalId(h[0]);
                p.setHospitalName(h[1]);
                p.setTransactionHash("0x" + UUID.randomUUID().toString().replace("-", "") + "a1b2");
                p.setBlockNumber(blockBase + i * 3);
                p.setWitnessName("Dr. Witness " + (i + 1));
                p.setWitnessContact("+91 98765" + String.format("%05d", i));
                pledgeRecordRepository.save(p);
            }

            // Seed recipients
            for (int i = 0; i < recipientData.length; i++) {
                PledgeRecord p = new PledgeRecord();
                String[] h = hospitals[i % hospitals.length];
                String[] d = recipientData[i];
                p.setPatientName(d[0]);
                p.setPatientAge(Integer.parseInt(d[1]));
                p.setBloodGroup(d[2]);
                p.setHlaMarkers(d[3]);
                p.setAbhaId(d[4]);
                p.setAbhaHash("0x" + UUID.randomUUID().toString().replace("-", ""));
                p.setDocumentHash("0x" + UUID.randomUUID().toString().replace("-", ""));
                p.setIpfsCid("Qm" + UUID.randomUUID().toString().replace("-", ""));
                p.setOrganBitmap(1 << (i % organs.length));
                p.setOrgan(organs[i % organs.length]);
                p.setRole("RECIPIENT");
                p.setHospitalId(h[0]);
                p.setHospitalName(h[1]);
                p.setTransactionHash("0x" + UUID.randomUUID().toString().replace("-", "") + "c3d4");
                p.setBlockNumber(blockBase + 40 + i * 3);
                p.setWitnessName("Dr. Witness R" + (i + 1));
                p.setWitnessContact("+91 91234" + String.format("%05d", i));
                pledgeRecordRepository.save(p);
            }

            System.out.println("DataSeeder: Seeded " + pledgeRecordRepository.count() + " pledge records across " + hospitals.length + " hospitals.");
        }

        // Seed match records
        if (matchRecordRepository.count() == 0) {
            String[][] hospitals = {
                {"HOSP-AIIMS-DEL", "AIIMS New Delhi"},
                {"HOSP-APOLLO-CHE", "Apollo Hospitals Chennai"},
                {"HOSP-KEM-MUM", "KEM Hospital Mumbai"},
                {"HOSP-NIMHANS-BAN", "NIMHANS Bangalore"},
                {"HOSP-PGIMER-CHD", "PGIMER Chandigarh"}
            };

            Object[][] matchData = {
                {"Aarav Sharma", "Ananya Rao", "O+", "O+", 28, 32, "A1,A2,B7,DR3,DR4,B44", "A1,A2,B7,DR3,DR4,B44", "Kidney", 98.5, 0},
                {"Vivaan Patel", "Ishaan Kumar", "A+", "A+", 35, 48, "A2,A11,B8,DR1,DR7,B35", "A2,A11,B8,DR1,DR7,B35", "Liver", 92.1, 1},
                {"Sai Reddy", "Zara Chatterjee", "B+", "B+", 42, 25, "A3,A24,B51,DR4,DR11,B7", "A3,A24,B51,DR4,DR11,B7", "Heart", 95.7, 2},
                {"Diya Gupta", "Anika Shah", "A-", "A-", 29, 41, "A1,A24,B35,DR7,DR11,B44", "A1,A24,B35,DR7,DR11,B44", "Lung", 88.3, 3},
                {"Krishna Nair", "Riya Pillai", "AB+", "AB+", 45, 30, "A2,A3,B7,DR1,DR4,B51", "A2,A3,B7,DR1,DR4,B51", "Cornea", 91.0, 4}
            };

            long blockBase = 14207850;
            for (int i = 0; i < matchData.length; i++) {
                Object[] m = matchData[i];
                MatchRecord record = new MatchRecord();
                record.setDonorName((String) m[0]);
                record.setRecipientName((String) m[1]);
                record.setDonorBloodGroup((String) m[2]);
                record.setRecipientBloodGroup((String) m[3]);
                record.setDonorAge((Integer) m[4]);
                record.setRecipientAge((Integer) m[5]);
                record.setDonorHla((String) m[6]);
                record.setRecipientHla((String) m[7]);
                record.setOrganType((String) m[8]);
                record.setCompatibilityScore((Double) m[9]);
                record.setHospitalId(hospitals[(int) m[10]][0]);
                record.setHospitalName(hospitals[(int) m[10]][1]);
                record.setTransactionHash("0x" + UUID.randomUUID().toString().replace("-", "") + "match");
                record.setBlockNumber(blockBase + i * 5);
                record.setDonorAbhaHash("0x" + UUID.randomUUID().toString().replace("-", ""));
                record.setRecipientAbhaHash("0x" + UUID.randomUUID().toString().replace("-", ""));
                record.setMatchedAt(LocalDateTime.now().minusHours(i * 12L + 1));
                matchRecordRepository.save(record);
            }
            System.out.println("DataSeeder: Seeded " + matchRecordRepository.count() + " match records.");
        }
    }
}
