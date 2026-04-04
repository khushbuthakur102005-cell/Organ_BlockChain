import subprocess
import time
import requests
import json
import socket
import os
import sys

def get_maven():
    import shutil
    import urllib.request
    import zipfile
    mvn_cmd = "mvn"
    if shutil.which("mvn") is None:
        if not os.path.exists("apache-maven-3.9.6"):
            print("[*] Downloading Apache Maven (not found globally)...")
            url = "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
            urllib.request.urlretrieve(url, "maven.zip")
            with zipfile.ZipFile("maven.zip", 'r') as zip_ref:
                zip_ref.extractall(".")
            try:
                os.remove("maven.zip")
            except:
                pass
        mvn_cmd = os.path.abspath("apache-maven-3.9.6/bin/mvn.cmd")
    return mvn_cmd

# Directories based on standard project structure
BACKEND_DIR = os.path.join(os.getcwd(), "backend")
ML_DIR = os.path.join(os.getcwd(), "ml-service")
BLOCKCHAIN_DIR = os.path.join(os.getcwd(), "blockchain")

def is_port_in_use(port):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        return s.connect_ex(('127.0.0.1', port)) == 0

def start_process(cmd, cwd, name):
    print(f"[*] Starting {name}...")
    log_file = open(f"{name}_log.txt", "w")
    # shell=True is needed for running shell builtins or cmd wrappers like mvn on windows
    proc = subprocess.Popen(cmd, cwd=cwd, stdout=log_file, stderr=subprocess.STDOUT, shell=True)
    return proc, log_file

def main():
    print("======================================================")
    print("      TransplantChain End-to-End Pipeline Test        ")
    print("======================================================")
    
    # 1. Start Ganache
    ganache_proc, ganache_log = None, None
    if not is_port_in_use(7545):
        ganache_proc, ganache_log = start_process("npx ganache --port 7545", BLOCKCHAIN_DIR, "ganache")
        time.sleep(4)
        print("[!] WARNING: Started a fresh Ganache instance! Your smart contract may not be deployed. If verified-witness fails, you need to migrate it and update the hardcoded address in BlockchainService.java!")
    else:
        print("[+] Ganache is already running on port 7545.")

    # 2. Start Flask ML Service
    flask_proc, flask_log = None, None
    if not is_port_in_use(5000):
        flask_proc, flask_log = start_process(f"{sys.executable} app.py", ML_DIR, "flask")
        time.sleep(3)
    else:
        print("[+] Flask ML Service is already running on port 5000.")

    # 3. Start Spring Boot
    spring_proc, spring_log = None, None
    if not is_port_in_use(8080):
        mvn_exec = get_maven()
        mvn_cmd = f"{mvn_exec} spring-boot:run"
        spring_proc, spring_log = start_process(mvn_cmd, BACKEND_DIR, "spring_boot")
        
        print("[*] Waiting for Spring Boot to start on Port 8080 (can take 15-30 seconds)...")
        for _ in range(40):
            if is_port_in_use(8080):
                print("[+] Spring Boot successfully initialized!")
                break
            time.sleep(2)
    else:
        print("[+] Spring Boot is already running on port 8080.")

    print("\n--- ALL SERVERS ON. BEGINNING PIPELINE TESTS ---\n")
    time.sleep(2) # Final buffer for routing

    try:
        # --- TEST 1: ABHA Verification ---
        abha_id = "1234-5678-9012-3456"
        print(f"1) Testing ABHA Verification for ID: {abha_id}")
        resp1 = requests.get(f"http://127.0.0.1:8080/api/auth/abha-verify?abhaId={abha_id}")
        resp1.raise_for_status()
        print(f"   => Success! Received JWT Token: {resp1.json().get('token')}\n")

        # --- TEST 2: PII Masking via Flask ---
        print("2) Testing PII Masking via Flask NLP Microservice")
        raw_text = "Patient Mr. Rahul Sharma, Aadhar 1234 5678 9012, Phone +91 9876543210 needs standard match."
        print(f"   Original: {raw_text}")
        resp2 = requests.post("http://127.0.0.1:5000/mask-pii", json={"text": raw_text})
        resp2.raise_for_status()
        print(f"   => Masked : {resp2.json().get('safe_text')}\n")

        # --- TEST 3: IPFS Document Upload & SHA-256 ---
        print("3) Testing IPFS Document PIN and SHA-256 Hashing")
        dummy_file = "test_medical_record.txt"
        with open(dummy_file, "w") as f:
            f.write("Transplantchain Medical Record - Clear for surgery.")
        
        doc_hash = "0x" + "0"*64
        cid = "QmDummyFake123"
        with open(dummy_file, "rb") as f:
            files = {"file": (dummy_file, f, "text/plain")}
            resp3 = requests.post("http://127.0.0.1:8080/api/pledge/upload", files=files)
            
            if resp3.status_code == 500:
                print("   => FAILED: Check your Pinata API keys in application.properties! (Are they empty?)")
                print(f"   => Error: {resp3.text}\n")
            else:
                resp3.raise_for_status()
                upload_data = resp3.json()
                doc_hash = upload_data.get("documentHash", doc_hash)
                cid = upload_data.get("cid", cid)
                print(f"   => Success! Doc SHA-256 Hash: {doc_hash}")
                print(f"   => Success! IPFS CID: {cid}\n")
        
        os.remove(dummy_file)

        # --- TEST 4: Smart Contract Pledge Witness ---
        print("4) Testing Smart Contract execution on Local Ganache")
        # Ensure proper byte32 lengths
        abha_hash = "0x" + "1"*64
        doc_hash_fixed = "0x" + (doc_hash.replace("0x", "").zfill(64))[:64]

        pledge_payload = {
            "abhaHash": abha_hash,
            "documentHash": doc_hash_fixed,
            "cid": cid,
            "organBitmap": 3, 
            "witnessSignature": "sig12345"
        }
        resp4 = requests.post("http://127.0.0.1:8080/api/pledge/verify-witness", json=pledge_payload)
        
        if resp4.status_code == 500:
            print("   => FAILED: Could not commit to Ganache.")
            print("   => Please verify that your `BlockchainService.java` private key matches an active Ganache account with ETH!")
            print(f"   => Error: {resp4.text}\n")
        else:
            resp4.raise_for_status()
            tx_data = resp4.json()
            print(f"   => Success! Blockchain Tx Hash: {tx_data.get('transactionHash')}\n")
            
        print("======================================================")
        print("                 TEST SUITE COMPLETE                  ")
        print("======================================================")

    except Exception as e:
        print(f"\n[X] Pipeline Execution Stopped Early Due To Error: {e}")

    finally:
        print("\n--- Terminating Background Server Processes ---")
        if spring_proc:
            # Terminate tree because Maven spawns Java
            subprocess.call(['taskkill', '/F', '/T', '/PID', str(spring_proc.pid)])
            if spring_log: spring_log.close()
            print("Terminated Spring Boot.")
        if flask_proc:
            flask_proc.terminate()
            if flask_log: flask_log.close()
            print("Terminated Flask ML.")
        if ganache_proc:
            subprocess.call(['taskkill', '/F', '/T', '/PID', str(ganache_proc.pid)])
            if ganache_log: ganache_log.close()
            print("Terminated Ganache.")

if __name__ == "__main__":
    main()
