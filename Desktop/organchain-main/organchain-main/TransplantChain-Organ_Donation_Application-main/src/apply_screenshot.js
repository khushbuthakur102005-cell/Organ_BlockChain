const fs = require('fs');
const path = require('path');

const basePath = __dirname; 

// Rename the raw dump file to primary-logo.png safely resolving URL Space escaping issues
const oldFile = path.join(basePath, 'images', 'Screenshot (745).png');
const newFile = path.join(basePath, 'images', 'primary-logo.png');

if (fs.existsSync(oldFile)) {
    fs.renameSync(oldFile, newFile);
}

// Map the HTML links across all portal screens natively
const files = fs.readdirSync(basePath).filter(f => f.endsWith('.html'));

files.forEach(file => {
    let content = fs.readFileSync(path.join(basePath, file), 'utf8');
    if (content.includes('organ-donation-logo-new.svg')) {
        content = content.replace(/organ-donation-logo-new\.svg/g, 'primary-logo.png');
        
        // Let's also ensure object-contain is applied in case their custom crop is wide
        if (content.match(/<img src="images\/primary-logo\.png"[^>]+class="h-8 w-auto[^"]*"/)) {
            content = content.replace(/class="h-8 w-auto/g, 'class="h-8 w-auto object-contain');
        }
        
        fs.writeFileSync(path.join(basePath, file), content);
        console.log(`Updated Logo pointer in ${file}`);
    }
});
console.log('Logo File Override & Directory Rename Process Completed.');
