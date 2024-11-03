function scaricaFile() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();
    const nome = document.querySelector('input[name="nome"]').value;
    const quantita = document.querySelector('input[name="quantita"]').value;
    const prezzo = document.getElementById("prezzoCalcolato").innerText;
    const numeriCasuali = Array.from({ length: 8 }, () => Math.floor(Math.random() * 10)).join('');
    const codiceABarre = generaCodiceABarre(nome.toUpperCase() + numeriCasuali);
    doc.text(`Nome: ${nome}`, 10, 10);
    doc.text(`Quantità: ${quantita}`, 10, 20);
    doc.text(`Prezzo: $${prezzo}`, 10, 30);
    doc.text("Codice a Barre:", 10, 40);
    doc.text(codiceABarre, 10, 50);
    doc.save("biglietti.pdf");
}