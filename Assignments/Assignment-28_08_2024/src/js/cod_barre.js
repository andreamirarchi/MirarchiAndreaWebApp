function generaCodiceABarre(text) {
    let codiceABarre = '';
    for (let char of text) {
        const codiceAscii = char.charCodeAt(0);
        for (let i = 0; i < 7; i++) {
            codiceABarre += (codiceAscii >> i) & 1 ? '|' : ' ';
        }
        codiceABarre += ' ';
    }
    return codiceABarre;
}