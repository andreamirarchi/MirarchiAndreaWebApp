function scaricaFile() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const quantita = document.getElementById("amount").value;
    const prezzo = document.getElementById("prezzoCalcolato").innerText;
    const numeriCasuali = Array.from({ length: 8 }, () => Math.floor(Math.random() * 10)).join('');
    const codiceABarre = generaCodiceABarre(nome.toUpperCase() + numeriCasuali);
    doc.text(`Nome: ${nome}`, 10, 10);
    doc.text(`Email: ${email}`, 10, 20);
    doc.text(`Quantità: ${quantita}`, 10, 30);
    doc.text(`Prezzo: $${prezzo}`, 10, 40);
    doc.text("Codice a Barre:", 10, 50);
    doc.text(codiceABarre, 10, 60);
    doc.save("biglietti.pdf");
}