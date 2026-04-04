const fs = require('fs');
const path = require('path');

const basePath = __dirname;
const files = fs.readdirSync(basePath).filter(f => f.endsWith('.html') || f.endsWith('.js'));

files.forEach(file => {
    // Avoid editing self or previous build scripts if not needed, but safe to do so
    if (file === 'rename_app.js') return;

    let content = fs.readFileSync(path.join(basePath, file), 'utf8');
    let original = content;

    // 1. Replace standard CamelCase "OrganChain"
    content = content.replace(/OrganChain/g, 'CyberCare');
    
    // 2. Replace all-caps "ORGANCHAIN"
    content = content.replace(/ORGANCHAIN/g, 'CYBERCARE');
    
    // 3. Replace lowercase "organchain" (ignoring URLs/repo strings potentially)
    // To be safe we will just globally replace organchain but preserve organchain-auth if it exists
    // actually, to avoid breaking file paths if any, we'll replace organchain -> cybercare
    content = content.replace(/organchain(?!-main|-auth)/g, 'cybercare');
    
    // Fix backend JWT string back to original just in case
    // (If the JWT is issued as organchain-auth, we should keep it string-matched)
    content = content.replace(/cybercare-auth/g, 'organchain-auth');

    if (content !== original) {
        fs.writeFileSync(path.join(basePath, file), content);
        console.log(`Updated branding to CyberCare in: ${file}`);
    }
});
