const fs = require('fs');
const path = require('path');

const basePath = __dirname;
const files = fs.readdirSync(basePath).filter(f => f.endsWith('.html'));

files.forEach(file => {
    let content = fs.readFileSync(path.join(basePath, file), 'utf8');
    let modified = false;

    // Much more resilient regex that looks for an anchor containing the primary-logo and ending with the word "OrganChain" or "Admin Command Center", followed by some tags and the </a>
    const logoRegex = /<a[^>]*href="([^"]+)"[^>]*>[\s\S]*?<img src="images\/primary-logo\.png"[\s\S]*?(?:OrganChain|Admin Command Center)[^<]*<\/span>\s*(?:<\/span>\s*)?<\/a>/i;
    
    // Also support checking for just the text without the inner span ending differently
    const logoFallbackRegex = /<a[^>]*href="([^"]+)"[^>]*>[\s\S]*?<img src="images\/primary-logo\.png"[\s\S]*?<\/a>/i;
    
    // Test which one matches (prefer the specific one, fallback if not found, as long as it's the branding logo not something else)
    let match = content.match(logoRegex);
    if (!match) {
        // Find the specific branding block. It's almost always the first occurrence of primary-logo
        const firstAStart = content.indexOf('<a ');
        const logoPos = content.indexOf('images/primary-logo.png');
        
        if (logoPos !== -1) {
             match = content.match(logoFallbackRegex);
        }
    }

    if (match) {
        let href = match[1];
        let adminCommand = match[0].includes('Admin Command Center');
        let textMatch = adminCommand ? 'Admin Command Center' : 'OrganChain';

        let standardizedAnchor = `<a href="${href}" class="flex items-center gap-x-3 transition-opacity hover:opacity-90 shrink-0 whitespace-nowrap">
                    <img src="images/primary-logo.png" alt="Logo" class="h-10 w-auto object-contain drop-shadow-md" />
                    <span class="text-white font-extrabold tracking-tight text-xl flex items-center"><span class="font-light text-slate-300">${textMatch}</span></span>
                </a>`;
        
        // Ensure we aren't replacing it with the EXACT SAME STRING (prevents infinite file writes)
        if (match[0] !== standardizedAnchor) {
            content = content.replace(match[0], standardizedAnchor);
            modified = true;
        }
    }

    if (modified) {
        fs.writeFileSync(path.join(basePath, file), content);
        console.log(`Successfully hard-aligned branding constraints in: ${file}`);
    }
});
