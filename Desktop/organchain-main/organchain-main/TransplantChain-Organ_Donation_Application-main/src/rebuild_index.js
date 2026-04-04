const fs = require('fs');
const path = require('path');

const targetFile = path.join(__dirname, 'index.html');
let html = fs.readFileSync(targetFile, 'utf8');

const startPattern = '<!-- ── Replacement Section A: The Life-Cycle of a Match ── -->';
const endPattern = '<!-- ── Replacement Section B: National Infrastructure at a Glance ── -->';
const startIndex = html.indexOf(startPattern);
const endIndex = html.indexOf(endPattern);

if (startIndex !== -1 && endIndex !== -1) {
    const newSection = `<!-- ── Replacement Section A: The Life-Cycle of a Match ── -->
    <main class="w-full bg-slate-950 py-32 border-b border-slate-800 relative overflow-hidden">
        
        <!-- Subtle Glow Background -->
        <div class="absolute top-0 left-1/2 -translate-x-1/2 w-[800px] h-[500px] bg-emerald-500/5 blur-[150px] rounded-full pointer-events-none"></div>

        <div class="max-w-[1400px] mx-auto px-6 relative z-10">
            <div class="text-center mb-20">
                <h2 class="text-4xl font-extrabold text-white tracking-tight mb-4">The Life-Cycle of a Match</h2>
                <p class="text-slate-400 font-medium text-lg max-w-2xl mx-auto">How OrganChain guarantees zero-discretion allocation through immutable transparency.</p>
            </div>
            
            <div class="grid grid-cols-1 md:grid-cols-3 gap-10 relative z-10">
                <!-- Step 1 -->
                <div class="bg-slate-900 rounded-[20px] shadow-2xl border border-emerald-500/20 text-center transition-all hover:-translate-y-2 hover:shadow-[0_15px_40px_-10px_rgba(16,185,129,0.3)] hover:border-emerald-500/50 duration-500 relative overflow-hidden group">
                    <div class="w-full h-56 relative overflow-hidden border-b border-slate-800">
                        <img src="images/lifecycle_pledge.png" alt="Secure Pledging" class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-1000 ease-out">
                        <div class="absolute inset-0 bg-gradient-to-t from-slate-900 via-slate-900/20 to-emerald-900/10 mix-blend-overlay"></div>
                    </div>
                    <div class="p-10 pt-8 relative z-10 flex flex-col h-[180px]">
                        <h3 class="text-2xl font-bold text-white mb-3">1. Secure Pledging</h3>
                        <p class="text-[0.95rem] text-slate-400 font-light leading-relaxed">Securely register your intent via ABHA ID. Your clinical identifiers are immediately anonymized.</p>
                    </div>
                </div>

                <!-- Step 2 -->
                <div class="bg-slate-900 rounded-[20px] shadow-2xl border border-emerald-500/20 text-center transition-all hover:-translate-y-2 hover:shadow-[0_15px_40px_-10px_rgba(16,185,129,0.3)] hover:border-emerald-500/50 duration-500 relative overflow-hidden group">
                    <div class="w-full h-56 relative overflow-hidden border-b border-slate-800">
                        <img src="images/lifecycle_matrix.png" alt="Fair Allocation" class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-1000 ease-out">
                        <div class="absolute inset-0 bg-gradient-to-t from-slate-900 via-slate-900/20 to-emerald-900/10 mix-blend-overlay"></div>
                    </div>
                    <div class="p-10 pt-8 relative z-10 flex flex-col h-[180px]">
                        <h3 class="text-2xl font-bold text-white mb-3">2. Fair Allocation</h3>
                        <p class="text-[0.95rem] text-slate-400 font-light leading-relaxed">Your medical markers are cryptographically hashed <span class="font-mono px-1.5 py-0.5 bg-emerald-950/50 border border-emerald-800/50 rounded-md text-[11px] text-emerald-400 shadow-inner">&nbsp;0x&nbsp;...&nbsp;</span> and synced to the secure Ethereum Layer-2 ledger.</p>
                    </div>
                </div>

                <!-- Step 3 -->
                <div class="bg-slate-900 rounded-[20px] shadow-2xl border border-emerald-500/20 text-center transition-all hover:-translate-y-2 hover:shadow-[0_15px_40px_-10px_rgba(16,185,129,0.3)] hover:border-emerald-500/50 duration-500 relative overflow-hidden group">
                    <div class="w-full h-56 relative overflow-hidden border-b border-slate-800">
                        <img src="images/lifecycle_audit.png" alt="Traceable Finalization" class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-1000 ease-out">
                        <div class="absolute inset-0 bg-gradient-to-t from-slate-900 via-slate-900/20 to-emerald-900/10 mix-blend-overlay"></div>
                    </div>
                    <div class="p-10 pt-8 relative z-10 flex flex-col h-[180px]">
                        <h3 class="text-2xl font-bold text-white mb-3">3. Traceable Finalization</h3>
                        <p class="text-[0.95rem] text-slate-400 font-light leading-relaxed">Zero-discretion pairing executes via smart contract algorithms when a matching donor is available.</p>
                    </div>
                </div>
            </div>
        </div>
    </main>

    `;
    
    html = html.substring(0, startIndex) + newSection + html.substring(endIndex);
    fs.writeFileSync(targetFile, html);
    console.log('Successfully embedded the 3D high-fidelity assets into the index layout!');
} else {
    console.log('Error: Could not locate the Life-Cycle bounds.');
}
