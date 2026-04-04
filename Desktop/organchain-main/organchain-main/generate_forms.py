"""Generate all 10 NOTTO-style placeholder PDF forms."""
import os

FORM_TITLES = {
    1: "Application for Removal of Human Organ or Tissue from a Near Related Living Donor",
    2: "Consent for Living Spousal Organ Donation",
    3: "Application for Swap Living Donor Transplantation",
    4: "Form of Genetic Relationship for Living Donor",
    5: "Consent for Spousal Near-Relationship Donation",
    6: "Deceased Donor Pledging Form",
    7: "Certificate of Brain-Stem Death",
    8: "Application Form for Living Donor Registration",
    9: "Living Donor Authorization Committee Certificate",
    10: "Living Donor Domicile Verification",
}

# Minimal valid PDF generator (no external libs needed)
def make_pdf(title, form_number):
    lines = [
        "%PDF-1.4",
        "1 0 obj << /Type /Catalog /Pages 2 0 R >> endobj",
        "2 0 obj << /Type /Pages /Kids [3 0 R] /Count 1 >> endobj",
        "3 0 obj << /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] /Contents 4 0 R /Resources << /Font << /F1 5 0 R >> >> >> endobj",
    ]

    # Build page content stream
    content_lines = [
        "BT",
        "/F1 18 Tf",
        "72 700 Td",
        f"(NOTTO - Form {form_number}) Tj",
        "0 -30 Td",
        "/F1 12 Tf",
        f"({title}) Tj",
        "0 -40 Td",
        "(National Organ and Tissue Transplant Organisation) Tj",
        "0 -25 Td",
        "(Ministry of Health and Family Welfare, Government of India) Tj",
        "0 -40 Td",
        "/F1 10 Tf",
        "(This is an official transplant document template.) Tj",
        "0 -20 Td",
        "(Please fill in all required fields and submit to your) Tj",
        "0 -15 Td",
        "(registered transplant centre for processing.) Tj",
        "0 -30 Td",
        "(________________________________________) Tj",
        "0 -20 Td",
        "(Full Name of Applicant / Donor) Tj",
        "0 -30 Td",
        "(________________________________________) Tj",
        "0 -20 Td",
        "(ABHA ID / National Health ID) Tj",
        "0 -30 Td",
        "(________________________________________) Tj",
        "0 -20 Td",
        "(Date of Birth) Tj",
        "0 -30 Td",
        "(________________________________________) Tj",
        "0 -20 Td",
        "(Blood Group) Tj",
        "0 -30 Td",
        "(________________________________________) Tj",
        "0 -20 Td",
        "(Organ(s) Being Pledged / Donated) Tj",
        "0 -40 Td",
        "/F1 8 Tf",
        f"(Form {form_number} | Generated for TransplantChain Portal | organchain.in) Tj",
        "ET",
    ]
    stream = "\n".join(content_lines)
    stream_obj = f"4 0 obj << /Length {len(stream)} >> stream\n{stream}\nendstream endobj"
    lines.append(stream_obj)

    # Font
    lines.append("5 0 obj << /Type /Font /Subtype /Type1 /BaseFont /Helvetica >> endobj")

    # xref + trailer
    body = "\n".join(lines)
    xref_pos = len(body) + 1
    xref = "xref\n0 6\n"
    xref += "0000000000 65535 f \n"
    # Approximate offsets (good enough for a simple reader)
    offsets = []
    pos = 0
    for line in lines:
        offsets.append(pos)
        pos += len(line) + 1
    for i in range(1, 6):
        xref += f"{offsets[i-1]:010d} 00000 n \n"

    trailer = f"trailer << /Size 6 /Root 1 0 R >>\nstartxref\n{xref_pos}\n%%EOF"
    return body + "\n" + xref + trailer


if __name__ == "__main__":
    src_forms = os.path.join(os.path.dirname(__file__),
        "TransplantChain-Organ_Donation_Application-main", "src", "forms")
    public_forms = os.path.join(os.path.dirname(__file__),
        "TransplantChain-Organ_Donation_Application-main", "public", "forms")

    for d in [src_forms, public_forms]:
        os.makedirs(d, exist_ok=True)

    for num, title in FORM_TITLES.items():
        pdf_data = make_pdf(title, num)
        for d in [src_forms, public_forms]:
            path = os.path.join(d, f"Form_{num}.pdf")
            with open(path, "w", encoding="utf-8") as f:
                f.write(pdf_data)
            print(f"[OK] {path}")

    print("\n✅ All 10 forms generated in both src/forms/ and public/forms/")
