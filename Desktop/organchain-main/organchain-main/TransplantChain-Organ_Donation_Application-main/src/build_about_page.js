const fs = require('fs');
const path = require('path');

const targetFile = path.join(__dirname, 'about-general.html');
let html = fs.readFileSync(targetFile, 'utf8');

// 1. Update Title and Hero Text
html = html.replace(
    '<title>Mission Architecture — Secure Organ Network</title>', 
    '<title>About CyberCare — National Digital Infrastructure</title>'
);

html = html.replace(
    'Mission Architecture: <br class="hidden md:block" /><span class="font-medium" style="color:#10B981;">Securing the Future of Healthcare</span>',
    'About CyberCare: <br class="hidden md:block" /><span class="font-medium" style="color:#10B981;">National Digital Infrastructure</span>'
);

html = html.replace(
    'Eliminating resource and trust failures by migrating organ allocation to cryptographically enforced smart contracts.',
    'CyberCare serves as the decentralized backbone for the National Organ & Tissue Transplant Organization (NOTTO), ensuring trust, transparency, and equity in the gift of life.'
);

// 2. Replace the entire Core Architecture block up through the footer start
const startPattern = '<!-- ── Core Architecture Grid (3-Layer Stack) ── -->';
const endPattern = '<!-- ── Regulatory Compliance (Footer Section) ── -->';
const startIndex = html.indexOf(startPattern);
const endIndex = html.indexOf(endPattern);

if (startIndex !== -1 && endIndex !== -1) {
    const newSections = `<!-- ── Institutional Mandate ── -->
<section class="py-24 bg-white">
    <div class="max-w-7xl mx-auto px-6">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-16 items-center">
            <!-- Left Column: Establishment -->
            <div class="pr-0 lg:pr-8">
                <span class="text-emerald-500 font-bold tracking-widest uppercase text-xs mb-3 block">Institutional Mandate</span>
                <h2 class="text-3xl lg:text-4xl font-light text-slate-900 tracking-tight mb-6 leading-tight">Implementing the <span class="font-medium">Digital India</span> Vision</h2>
                <p class="text-lg text-slate-600 font-light leading-relaxed mb-6">Established to eliminate the administrative friction in organ procurement, CyberCare implements the Digital India vision within the framework of the Transplantation of Human Organs Act (THOA).</p>
                <div class="h-1 w-20 bg-emerald-500 rounded-full"></div>
            </div>
            
            <!-- Right Column: Core Objectives -->
            <div class="bg-slate-50 p-8 rounded-2xl border border-slate-100 shadow-sm">
                <h3 class="text-xl font-medium text-slate-900 mb-8">Core Systematic Objectives</h3>
                <ul class="space-y-6">
                    <li class="flex items-start">
                        <div class="mt-1 w-8 h-8 rounded-full bg-emerald-100 flex items-center justify-center text-emerald-600 shrink-0 mr-4">
                            <i data-lucide="network" class="w-4 h-4"></i>
                        </div>
                        <div>
                            <strong class="text-slate-800 font-medium block text-lg mb-1">National Networking</strong>
                            <span class="text-slate-500 text-sm font-light leading-relaxed">Connecting all ROTTOs and SOTTOs via a single, synchronized ledger.</span>
                        </div>
                    </li>
                    <li class="flex items-start">
                        <div class="mt-1 w-8 h-8 rounded-full bg-emerald-100 flex items-center justify-center text-emerald-600 shrink-0 mr-4">
                            <i data-lucide="scale" class="w-4 h-4"></i>
                        </div>
                        <div>
                            <strong class="text-slate-800 font-medium block text-lg mb-1">Fair Allocation</strong>
                            <span class="text-slate-500 text-sm font-light leading-relaxed">Zero-discretion algorithmic matching based strictly on authorized clinical protocols.</span>
                        </div>
                    </li>
                    <li class="flex items-start">
                        <div class="mt-1 w-8 h-8 rounded-full bg-emerald-100 flex items-center justify-center text-emerald-600 shrink-0 mr-4">
                            <i data-lucide="contact-2" class="w-4 h-4"></i>
                        </div>
                        <div>
                            <strong class="text-slate-800 font-medium block text-lg mb-1">Pledge Awareness</strong>
                            <span class="text-slate-500 text-sm font-light leading-relaxed">Maintaining a universal, immutable registry for both deceased and living donors.</span>
                        </div>
                    </li>
                    <li class="flex items-start">
                        <div class="mt-1 w-8 h-8 rounded-full bg-emerald-100 flex items-center justify-center text-emerald-600 shrink-0 mr-4">
                            <i data-lucide="shield-check" class="w-4 h-4"></i>
                        </div>
                        <div>
                            <strong class="text-slate-800 font-medium block text-lg mb-1">Data Integrity</strong>
                            <span class="text-slate-500 text-sm font-light leading-relaxed">Guaranteeing permanent, zero-knowledge, and unalterable medical records.</span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>

<!-- ── Operational Hierarchy (NOTTO-ROTTO-SOTTO) ── -->
<section class="py-24 bg-slate-50/50 border-t border-slate-100">
    <div class="max-w-7xl mx-auto px-6">
        <div class="text-center mb-16">
            <h2 class="text-3xl lg:text-4xl font-light text-slate-900 tracking-tight">Operational <span class="font-medium">Hierarchy</span></h2>
            <p class="text-slate-500 mt-4 text-lg font-light max-w-2xl mx-auto leading-relaxed">Blockchain smart contracts ensure that clinical mapping and donor availability criteria flow seamlessly and securely from state-level gateways up to the national hub.</p>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
            <!-- Block 1: NOTTO -->
            <div class="bg-white p-10 rounded-[20px] border-t-[5px] border-emerald-500 shadow-[0_4px_20px_-5px_rgba(0,0,0,0.05)] hover:-translate-y-1 transition-transform">
                <div class="text-emerald-500 mb-5"><i data-lucide="globe-2" class="w-10 h-10"></i></div>
                <h3 class="text-2xl font-bold text-slate-900 mb-2 tracking-tight">NOTTO</h3>
                <p class="text-xs font-bold text-slate-400 uppercase tracking-widest mb-5">National Level</p>
                <p class="text-slate-600 text-[0.95rem] font-light leading-relaxed">The apex national networking organization. Acts as the master verification node, auditing the ledger across all state channels and governing national sharing policies.</p>
            </div>
            
            <!-- Block 2: ROTTO -->
            <div class="bg-white p-10 rounded-[20px] border-t-[5px] border-emerald-400 shadow-[0_4px_20px_-5px_rgba(0,0,0,0.05)] hover:-translate-y-1 transition-transform">
                <div class="text-emerald-400 mb-5"><i data-lucide="map" class="w-10 h-10"></i></div>
                <h3 class="text-2xl font-bold text-slate-900 mb-2 tracking-tight">ROTTO</h3>
                <p class="text-xs font-bold text-slate-400 uppercase tracking-widest mb-5">Regional Level</p>
                <p class="text-slate-600 text-[0.95rem] font-light leading-relaxed">Regional aggregation hubs that coordinate inter-state organ matches. They synchronize localized smart contract events across the regional blockchain subnets.</p>
            </div>
            
            <!-- Block 3: SOTTO -->
            <div class="bg-white p-10 rounded-[20px] border-t-[5px] border-emerald-300 shadow-[0_4px_20px_-5px_rgba(0,0,0,0.05)] hover:-translate-y-1 transition-transform">
                <div class="text-emerald-300 mb-5"><i data-lucide="building-2" class="w-10 h-10"></i></div>
                <h3 class="text-2xl font-bold text-slate-900 mb-2 tracking-tight">SOTTO</h3>
                <p class="text-xs font-bold text-slate-400 uppercase tracking-widest mb-5">State Level</p>
                <p class="text-slate-600 text-[0.95rem] font-light leading-relaxed">The foundational state-level gateways directly interfacing with registered hospitals. SOTTO nodes actively process newly minted donor pledges and patient waitlist requests.</p>
            </div>
        </div>
    </div>
</section>\n\n`;

    html = html.substring(0, startIndex) + newSections + html.substring(endIndex);
}

// 3. Replace the Regulatory Footer
const footerStartPattern = '<!-- ── Regulatory Compliance (Footer Section) ── -->';
const footerStartIndex = html.indexOf(footerStartPattern);
const scriptIndex = html.indexOf('<script>lucide.createIcons();</script>');

if (footerStartIndex !== -1 && scriptIndex !== -1) {
    const newFooter = `<!-- ── Regulatory Compliance (Footer Section) ── -->
<footer class="bg-slate-900 py-20 relative overflow-hidden border-t-4 border-emerald-500">
    <div class="max-w-7xl mx-auto px-6 relative z-10">
        <div class="mb-12 text-center md:text-left">
            <h4 class="text-white text-2xl font-medium tracking-tight">Statutory Framework & Compliance</h4>
            <p class="text-slate-400 text-sm mt-3 font-light max-w-2xl">Establishing unwavering technical authority through strict algorithmic adherence to national health frameworks.</p>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-3 gap-10 border-t border-slate-800 pt-12">
            <div>
                <span class="text-emerald-500 text-[11px] font-bold tracking-widest uppercase mb-4 block">Legal Mandate</span>
                <p class="text-white font-medium mb-3 text-lg">THOA 1994 (Amended 2011)</p>
                <p class="text-slate-400 text-sm font-light leading-relaxed pr-0 md:pr-6">Strictly coded to comply with the Transplantation of Human Organs and Tissues Act—the absolute statutory framework governing donation and allocation in India.</p>
            </div>
            <div>
                <span class="text-emerald-500 text-[11px] font-bold tracking-widest uppercase mb-4 block">Operational Standard</span>
                <p class="text-white font-medium mb-3 text-lg">NOTP Guidelines</p>
                <p class="text-slate-400 text-sm font-light leading-relaxed pr-0 md:pr-6">Architecturally interoperable with the National Organ Transplant Programme protocols, ensuring algorithmic governance maps directly to established national directives.</p>
            </div>
            <div>
                <span class="text-emerald-500 text-[11px] font-bold tracking-widest uppercase mb-4 block">Data Security</span>
                <p class="text-white font-medium mb-3 text-lg">Digital India API</p>
                <p class="text-slate-400 text-sm font-light leading-relaxed pr-0 md:pr-6">All patient data schemas leverage the ABHA identity gateway ecosystem, securing medical records immutably on-chain without exposing private identification parameters.</p>
            </div>
        </div>
    </div>
</footer>\n\n    `;
    
    html = html.substring(0, footerStartIndex) + newFooter + html.substring(scriptIndex);
}

fs.writeFileSync(targetFile, html);
console.log('about-general.html successfully transformed to NOTTO standards!');
