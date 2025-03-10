$(document).ready(function() {
    $("#produtoForm").submit(function(event) {
        event.preventDefault(); // Evita o comportamento padrão do formulário

        // Captura os valores do formulário
        var nome = $("#nome").val();
        var preco = $("#preco").val();
        var quantidade = $("#quantidade").val();

        // Verifica se os campos foram preenchidos
        if (nome.trim() === "" || preco.trim() === "" || quantidade.trim() === "") {
            alert("Preencha todos os campos!");
            return;
        }

        // Adiciona o novo produto na tabela
        var newRow = `
            <tr>
                <td>${nome}</td>
                <td>${preco}</td>
                <td>${quantidade}</td>
                <td>
                    <button class="btn edit">Editar</button>
                    <button class="btn delete">Deletar</button>
                </td>
            </tr>
        `;
        $("table tbody").append(newRow);

        // Limpa o formulário
        $("#produtoForm")[0].reset();
    });

    // Evento de deletar produto
    $(document).on("click", ".delete", function() {
        $(this).closest("tr").remove();
    });

    // Evento de editar produto
    $(document).on("click", ".edit", function() {
        var row = $(this).closest("tr");
        var nome = row.find("td:eq(0)").text();
        var preco = row.find("td:eq(1)").text();
        var quantidade = row.find("td:eq(2)").text();

        $("#nome").val(nome);
        $("#preco").val(preco);
        $("#quantidade").val(quantidade);

        row.remove(); // Remove a linha para evitar duplicação na edição
    });
});


