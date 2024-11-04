const p = 14.5;

function calcolaPrezzo() {
    const quantita = Number(document.getElementById("amount").value);
    const prezzoTotale = p * quantita;
    document.getElementById("prezzoCalcolato").innerText = prezzoTotale.toFixed(2);
}