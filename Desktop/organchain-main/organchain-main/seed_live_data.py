import requests
import random
from faker import Faker
import time

fake = Faker('en_IN')

URL = "http://localhost:8080/api/pledge/verify-witness"

print("=====================================================")
print("🚀 ORGANCHAIN: 100-PATIENT LIVE DATA INJECTION SCRIPT")
print("=====================================================")
print("Target: " + URL)
print("Mining sequential transactions via Ganache...\n")

success_count = 0

for i in range(1, 101):
    patient_name = fake.name()
    abha_id = f"{random.randint(10,99)}-{random.randint(1000,9999)}-{random.randint(1000,9999)}-{random.randint(1000,9999)}"
    age = random.randint(18, 70)
    bg = random.choice(["A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"])
    bitmap = random.randint(1, 63)  # Organs random combination
    witness_name = fake.name()
    witness_contact = fake.phone_number()
    ipfs_cid = f"Qm{fake.sha256()[:44]}" # MOCK CID FORMAT

    payload = {
        "abhaHash": f"0x{fake.sha256()}",
        "documentHash": f"0x{fake.sha256()}",
        "cid": ipfs_cid,
        "organBitmap": bitmap,
        "witnessSignature": f"0x{fake.sha256()}0011",
        "abhaId": abha_id,
        "witnessName": witness_name,
        "witnessContact": witness_contact,
        "patientName": patient_name,
        "patientAge": age,
        "bloodGroup": bg
    }
    
    print(f"[{i:03}/100] Seeding Patient: {patient_name:<25} (ABHA: {abha_id}) ... ", end='', flush=True)
    
    try:
        response = requests.post(URL, json=payload, timeout=20)
        if response.status_code == 200:
            tx_hash = response.text
            print(f"✅ SUCCESS. Ganache Tx: {tx_hash}")
            success_count += 1
        else:
            print(f"❌ FAILED. Status: {response.status_code} - {response.text}")
    except Exception as e:
        print(f"⚠️ ERROR: {str(e)}")
        
    time.sleep(0.3) # Slight delay to let Ganache mine cleanly

print("\n=====================================================")
print(f"🏁 LIVE SEEDING COMPLETE! {success_count}/100 Pledges Mined.")
print("=====================================================")
