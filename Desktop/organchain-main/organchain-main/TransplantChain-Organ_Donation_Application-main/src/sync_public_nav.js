const fs = require('fs');
const path = require('path');

const basePath = __dirname;
const indexFile = path.join(basePath, 'index.html');
const targetFiles = [
    { name: 'awareness-general.html', activeTag: 'awareness-general.html' },
    { name: 'faqs-general.html', activeTag: 'faqs-general.html' }
];

// 1. Extract the polished <ul> block from index.html
const indexHtml = fs.readFileSync(indexFile, 'utf8');
const ulStart = indexHtml.indexOf('<ul class="flex items-center h-full m-0 p-0 list-none gap-8 hidden lg:flex">');
const ulEnd = indexHtml.indexOf('</ul>', ulStart) + 5;

if (ulStart !== -1 && ulEnd !== -1) {
    const optimizedUlBlock = indexHtml.substring(ulStart, ulEnd);

    // 2. Iterate through target public pages 
    for (const target of targetFiles) {
        const filePath = path.join(basePath, target.name);
        if (!fs.existsSync(filePath)) continue;

        let html = fs.readFileSync(filePath, 'utf8');

        const localUlStart = html.indexOf('<ul class="flex items-center h-full m-0 p-0 list-none gap-8 hidden lg:flex">');
        let localUlEnd = html.indexOf('</ul>', localUlStart);
        if (localUlEnd !== -1) localUlEnd += 5;

        // Fallback for older non-optimized UL tags in the target pages
        // In case the spacing or classes differ, we'll try to find the nearest <ul> inside the <nav> block
        let finalUlStart = localUlStart;
        let finalUlEnd = localUlEnd;

        if (finalUlStart === -1) {
            const navStart = html.indexOf('<nav');
            if (navStart !== -1) {
                finalUlStart = html.indexOf('<ul', navStart);
                finalUlEnd = html.indexOf('</ul>', finalUlStart) + 5;
            }
        }

        if (finalUlStart !== -1 && finalUlEnd !== -1) {
            // Drop in the new standardized structure
            let newHtml = html.substring(0, finalUlStart) + optimizedUlBlock + html.substring(finalUlEnd);

            // 3. Shift the "nav-active" class dynamically to the specific page
            // Strip it out from index.html (Home)
            newHtml = newHtml.replace(
                'href="index.html" class="nav-item nav-active"', 
                'href="index.html" class="nav-item"'
            );
            
            const regexActive = new RegExp(`href="${target.activeTag}" class="nav-item"`);
            newHtml = newHtml.replace(
                regexActive, 
                `href="${target.activeTag}" class="nav-item nav-active"`
            );

            fs.writeFileSync(filePath, newHtml);
            console.log(`Synced public navigation matrix securely to ${target.name}`);
        }
    }
} else {
    console.error('Failed to locate UL parent container inside index.html');
}
