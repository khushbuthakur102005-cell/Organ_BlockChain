from flask import Flask, request, jsonify
import re

app = Flask(__name__)

# Basic Regex for Indian context masking
AADHAR_REGEX = r'\b\d{4}\s?\d{4}\s?\d{4}\b'
PHONE_REGEX = r'\b(?:\+?91[\-\s]?)?[789]\d{9}\b'
NAME_REGEX = r'\b(?:Mr\.|Mrs\.|Ms\.|Dr\.)\s+[A-Z][a-z]+(?:\s+[A-Z][a-z]+)?\b'

@app.route('/mask-pii', methods=['POST'])
def mask_pii():
    if 'file' in request.files:
        file = request.files['file']
        file_bytes = file.read()
        return file_bytes, 200, {'Content-Type': 'application/pdf'}

    data = request.get_json(silent=True)
    if not data or 'text' not in data:
        return jsonify({'error': 'Missing text or file payload'}), 400
    
    text = data['text']
    text = re.sub(AADHAR_REGEX, '[REDACTED_AADHAR]', text)
    text = re.sub(PHONE_REGEX, '[REDACTED_PHONE]', text)
    text = re.sub(NAME_REGEX, '[REDACTED_NAME]', text)
    
    return jsonify({'safe_text': text})

@app.route('/predict-viability', methods=['POST'])
def predict_viability():
    data = request.get_json()
    if not data:
        return jsonify({'error': 'Missing payload'}), 400
        
    donor_age = data.get('donor_age', 40)
    ischemic_time = data.get('ischemic_time_hours', 4.0)
    hla_mismatch = data.get('hla_mismatch_score', 3)
    hospital_id = data.get('hospital_id', None)
    
    # Base score 100
    age_penalty = max(0, (donor_age - 40) * 0.5)
    time_penalty = max(0, (ischemic_time - 4.0) * 2.0)
    hla_penalty = hla_mismatch * 5.0
    
    score = 100.0 - age_penalty - time_penalty - hla_penalty
    
    # Same-hospital bonus: reduce ischemic time penalty
    if hospital_id:
        score += 3.0  # proximity bonus for same-hospital
    
    score = max(0.0, min(100.0, score))
    
    result = {'success_probability_percent': round(score, 2)}
    if hospital_id:
        result['hospital_id'] = hospital_id
        result['proximity_bonus_applied'] = True
    
    return jsonify(result)

@app.route('/match-filter', methods=['POST'])
def match_filter():
    """
    Filter and prioritize donor-recipient pairs within the same hospital network.
    Expects JSON: { hospital_id, donors: [...], recipients: [...] }
    Returns ranked matches with scores.
    """
    data = request.get_json()
    if not data:
        return jsonify({'error': 'Missing payload'}), 400
    
    hospital_id = data.get('hospital_id', None)
    donors = data.get('donors', [])
    recipients = data.get('recipients', [])
    
    ABO_COMPAT = {
        'O-': ['O-','O+','A-','A+','B-','B+','AB-','AB+'],
        'O+': ['O+','A+','B+','AB+'],
        'A-': ['A-','A+','AB-','AB+'],
        'A+': ['A+','AB+'],
        'B-': ['B-','B+','AB-','AB+'],
        'B+': ['B+','AB+'],
        'AB-': ['AB-','AB+'],
        'AB+': ['AB+'],
    }
    
    matches = []
    for donor in donors:
        donor_blood = donor.get('bloodGroup', '')
        donor_hla = set(donor.get('hlaMarkers', '').split(','))
        donor_age = donor.get('age', 40)
        donor_hospital = donor.get('hospitalId', '')
        
        for recipient in recipients:
            recip_blood = recipient.get('bloodGroup', '')
            recip_hla = set(recipient.get('hlaMarkers', '').split(','))
            recip_age = recipient.get('age', 40)
            recip_hospital = recipient.get('hospitalId', '')
            
            # ABO check
            compat_list = ABO_COMPAT.get(donor_blood, [])
            if recip_blood not in compat_list:
                continue
            
            score = 0
            # ABO score
            if donor_blood == recip_blood:
                score += 40
            else:
                score += 25
            
            # HLA match (max 60)
            shared = len(donor_hla & recip_hla)
            score += shared * 10
            
            # Age penalty
            age_diff = abs(donor_age - recip_age)
            score -= min(age_diff * 0.5, 15)
            
            # Same hospital bonus (prioritize within network)
            same_hospital = False
            if hospital_id:
                if donor_hospital == hospital_id and recip_hospital == hospital_id:
                    score += 15  # significant same-hospital bonus
                    same_hospital = True
                elif donor_hospital == hospital_id or recip_hospital == hospital_id:
                    score += 5   # partial network bonus
            
            if score > 0:
                matches.append({
                    'donorId': donor.get('abhaId', ''),
                    'recipientId': recipient.get('abhaId', ''),
                    'donorName': donor.get('name', ''),
                    'recipientName': recipient.get('name', ''),
                    'score': round(score, 1),
                    'hlaShared': shared,
                    'sameHospital': same_hospital,
                    'donorBlood': donor_blood,
                    'recipientBlood': recip_blood
                })
    
    # Sort by score descending
    matches.sort(key=lambda x: x['score'], reverse=True)
    
    return jsonify({
        'hospital_id': hospital_id,
        'totalMatches': len(matches),
        'matches': matches[:20]
    })

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
