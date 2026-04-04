const fs = require('fs');
const path = require('path');

const basePath = __dirname;
const targetFiles = ['about-general.html', 'about.html'];

for (const file of targetFiles) {
    const filePath = path.join(basePath, file);
    if (!fs.existsSync(filePath)) continue;

    let html = fs.readFileSync(filePath, 'utf8');

    const startMarker = '<!-- ── Regulatory Compliance (Footer Section) ── -->';
    const endMarker = '<script>lucide.createIcons();</script>';
    
    const startIndex = html.indexOf(startMarker);
    const endIndex = html.indexOf(endMarker);

    if (startIndex !== -1 && endIndex !== -1) {
        const smartFooterHtml = `<!-- ── Smart Regulation Layer (Footer Section) ── -->
<footer class="bg-slate-950 py-32 relative overflow-hidden border-t border-emerald-500/20">
    
    <!-- Stylized Blockchain Background -->
    <div class="absolute inset-0 pointer-events-none opacity-20">
        <svg class="absolute w-full h-full" xmlns="http://www.w3.org/2000/svg">
            <defs>
                <pattern id="hex-grid" width="60" height="103.923" patternUnits="userSpaceOnUse" patternTransform="scale(0.5)">
                    <path d="M30 0L60 17.321V51.962L30 69.282L0 51.962V17.321L30 0Z" fill="none" stroke="#475569" stroke-width="1.5" stroke-dasharray="4 4" />
                </pattern>
            </defs>
            <rect width="100%" height="100%" fill="url(#hex-grid)" />
        </svg>
    </div>

    <!-- The Glowing Emerald Chain Line (Passes "Through" Cards on Desktop) -->
    <div class="absolute top-[60%] lg:top-[65%] left-0 w-full h-px bg-gradient-to-r from-transparent via-emerald-500/60 to-transparent shadow-[0_0_20px_rgba(16,185,129,0.7)] z-0 hidden lg:block"></div>

    <div class="max-w-7xl mx-auto px-6 relative z-10">
        <div class="mb-20 text-center md:text-left relative">
            <h4 class="text-white text-3xl font-light tracking-tight">Smart <span class="font-medium">Regulation Layer</span></h4>
            <p class="text-slate-400 text-[1rem] mt-4 font-light max-w-2xl leading-relaxed">Converting static compliance documents into active, mathematically enforceable smart contracts operating live on the national ledger.</p>
        </div>
        
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-8 relative z-10">
            <!-- Node 1: THOA -->
            <div class="group relative bg-[#020617]/70 backdrop-blur-xl rounded-[20px] p-8 border border-emerald-500/20 hover:border-emerald-400/60 hover:-translate-y-2 transition-all duration-500 hover:shadow-[0_0_40px_-5px_rgba(16,185,129,0.3)]">
                <!-- Live Protocol Badge -->
                <div class="absolute top-6 right-6 flex items-center gap-2">
                    <span class="relative flex h-2 w-2">
                      <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-emerald-400 opacity-75"></span>
                      <span class="relative inline-flex rounded-full h-2 w-2 bg-emerald-500"></span>
                    </span>
                    <span class="text-[0.65rem] font-bold text-emerald-500 uppercase tracking-widest hidden sm:block">Live Enforced</span>
                </div>

                <div class="text-emerald-400 mb-6 group-hover:scale-110 transition-transform duration-500 origin-left"><i data-lucide="scale" class="w-12 h-12 opacity-90 drop-shadow-[0_0_12px_rgba(16,185,129,0.6)]"></i></div>
                <span class="text-slate-500 text-[11px] font-bold tracking-widest uppercase mb-2 block group-hover:text-emerald-400 transition-colors duration-300">Statutory Mandate</span>
                <p class="text-white font-medium mb-3 text-2xl tracking-tight">THOA 1994</p>
                <p class="text-slate-400 text-[0.95rem] font-light leading-relaxed mb-8">Strictly algorithmically aligned with the Transplantation of Human Organs Act—the absolute legal framework governing all allocation priorities.</p>
                
                <div class="pt-5 border-t border-slate-800/80">
                    <p class="font-mono text-[0.75rem] text-emerald-500/60 tracking-tight">_thoa_rule_hash: <span class="text-emerald-400">0x89a42c...f2a9</span></p>
                </div>
            </div>

            <!-- Node 2: NOTP -->
            <div class="group relative bg-[#020617]/70 backdrop-blur-xl rounded-[20px] p-8 border border-emerald-500/20 hover:border-emerald-400/60 hover:-translate-y-2 transition-all duration-500 hover:shadow-[0_0_40px_-5px_rgba(16,185,129,0.3)]">
                <!-- Live Protocol Badge -->
                <div class="absolute top-6 right-6 flex items-center gap-2">
                    <span class="relative flex h-2 w-2">
                      <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-emerald-400 opacity-75"></span>
                      <span class="relative inline-flex rounded-full h-2 w-2 bg-emerald-500"></span>
                    </span>
                    <span class="text-[0.65rem] font-bold text-emerald-500 uppercase tracking-widest hidden sm:block">Live Enforced</span>
                </div>

                <div class="text-emerald-400 mb-6 group-hover:scale-110 transition-transform duration-500 origin-left"><i data-lucide="shield-check" class="w-12 h-12 opacity-90 drop-shadow-[0_0_12px_rgba(16,185,129,0.6)]"></i></div>
                <span class="text-slate-500 text-[11px] font-bold tracking-widest uppercase mb-2 block group-hover:text-emerald-400 transition-colors duration-300">Operational Standard</span>
                <p class="text-white font-medium mb-3 text-2xl tracking-tight">NOTP Guidelines</p>
                <p class="text-slate-400 text-[0.95rem] font-light leading-relaxed mb-8">Architecturally interoperable with the National Programme protocols, mapping established state-level hierarchies straight into contract layers.</p>
                
                <div class="pt-5 border-t border-slate-800/80">
                    <p class="font-mono text-[0.75rem] text-emerald-500/60 tracking-tight">_notp_gov_hash: <span class="text-emerald-400">0x14f9d1...e7b3</span></p>
                </div>
            </div>

            <!-- Node 3: DISHA -->
            <div class="group relative bg-[#020617]/70 backdrop-blur-xl rounded-[20px] p-8 border border-emerald-500/20 hover:border-emerald-400/60 hover:-translate-y-2 transition-all duration-500 hover:shadow-[0_0_40px_-5px_rgba(16,185,129,0.3)]">
                <!-- Live Protocol Badge -->
                <div class="absolute top-6 right-6 flex items-center gap-2">
                    <span class="relative flex h-2 w-2">
                      <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-emerald-400 opacity-75"></span>
                      <span class="relative inline-flex rounded-full h-2 w-2 bg-emerald-500"></span>
                    </span>
                    <span class="text-[0.65rem] font-bold text-emerald-500 uppercase tracking-widest hidden sm:block">Live Enforced</span>
                </div>

                <div class="text-emerald-400 mb-6 group-hover:scale-110 transition-transform duration-500 origin-left"><i data-lucide="file-key-2" class="w-12 h-12 opacity-90 drop-shadow-[0_0_12px_rgba(16,185,129,0.6)]"></i></div>
                <span class="text-slate-500 text-[11px] font-bold tracking-widest uppercase mb-2 block group-hover:text-emerald-400 transition-colors duration-300">Data Security Axis</span>
                <p class="text-white font-medium mb-3 text-2xl tracking-tight">DISHA Security</p>
                <p class="text-slate-400 text-[0.95rem] font-light leading-relaxed mb-8">Cryptographic hashes conform entirely to the Digital Information Security Act (DISHA), protecting ABHA identities natively on-chain.</p>
                
                <div class="pt-5 border-t border-slate-800/80">
                    <p class="font-mono text-[0.75rem] text-emerald-500/60 tracking-tight">_disha_sec_hash: <span class="text-emerald-400">0x99c2b4...c1f8</span></p>
                </div>
            </div>
        </div>
    </div>
</footer>\n\n    `;
        
        const newHtml = html.substring(0, startIndex) + smartFooterHtml + html.substring(endIndex);
        fs.writeFileSync(filePath, newHtml);
        console.log(`Successfully transformed Smart Regulation Footers across ${file}!`);
    }
}
