    function aggiungiCommento() {
        const username = document.getElementById('username').value;
        const commentText = document.getElementById('commentText').value;
        const date = new Date().toISOString().split('T')[0];

        if (!username || !commentText) { // Controllo dei campi obbligatori
            alert("Entrambi i campi sono obbligatori.");
            return;
        }

        const table = document.getElementById('commentTable');
        const row = document.createElement('tr');

        row.innerHTML = `
            <td><div class="profile-pic"></div></td>
            <td>${username}</td>
            <td>${commentText}</td>
            <td class="comment-date">${date}</td>
            <td>
                <button class="btn btn-warning btn-sm" onclick="modificaCommento(this)">
                    <span class="glyphicon glyphicon-pencil"></span> Modifica
                </button>
                <button class="btn btn-danger btn-sm" onclick="cancellaCommento(this)">
                    <span class="glyphicon glyphicon-trash"></span> Elimina
                </button>
            </td>
        `;

        table.prepend(row);  // la nuova riga la voglio in cima alla tabella
        document.getElementById('username').value = '';
        document.getElementById('commentText').value = '';
    }

    function modificaCommento(button) {
        const row = button.parentElement.parentElement;
        const commentCell = row.cells[2];

        const newComment = prompt("Modifica il commento:", commentCell.innerText);

        if (newComment !== null && newComment.trim() !== "") {
            commentCell.innerText = newComment.trim();
        }
    }

    function cancellaCommento(button) {
        const row = button.parentElement.parentElement;
        row.remove();
    }