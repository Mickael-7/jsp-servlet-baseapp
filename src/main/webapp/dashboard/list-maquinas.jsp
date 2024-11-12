<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.MaquinaDTO" %>
<%@ page import="br.mendonca.testemaven.services.MaquinaService" %>
<%@ page import="java.util.UUID" %>

<%
    if (session.getAttribute("user") != null) {
        MaquinaService maquinaService = new MaquinaService();

        // Parâmetro para controlar a exibição de máquinas ocultas ou não
        boolean showHidden = request.getParameter("showHidden") != null && request.getParameter("showHidden").equals("true");

        // Listar as máquinas de acordo com o parâmetro showHidden
        List<MaquinaDTO> lista = showHidden ? maquinaService.listHiddenMachines() : maquinaService.listVisibleMachines();
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestão de Máquinas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
    <script>
        function toggleDetails(id) {
            const detailsRow = document.getElementById("details-" + .getUuid);
            if (detailsRow.style.display === "none") {
                detailsRow.style.display = "table-row";
            } else {
                detailsRow.style.display = "none";
            }
        }
    </script>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">

    <nav class="navbar navbar-expand-lg bg-body-terciary mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Gestão de Máquinas</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="/dashboard/dashboard.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
                    <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
                    <li class="nav-item"><a class="nav-link" href="/dashboard/list-maquinas.jsp">Máquinas</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Menu para alternar entre mostrar máquinas visíveis e ocultas -->
    <div class="mb-3">
        <form method="get" action="list-maquinas.jsp">
            <label for="showHidden">Mostrar máquinas:</label>
            <select name="showHidden" id="showHidden" class="form-select w-auto d-inline">
                <option value="false" <%= !showHidden ? "selected" : "" %>>Visíveis</option>
                <option value="true" <%= showHidden ? "selected" : "" %>>Ocultas</option>
            </select>
            <button type="submit" class="btn btn-secondary ms-2">Filtrar</button>
        </form>
    </div>

    <section id="cadastroMaquina">
        <h1 class="h3 mb-3 fw-normal">Cadastrar Nova Máquina:</h1>
        <form action="/dashboard/machine" method="post">
            <div class="form-floating mb-3">
                <input type="text" name="nome" class="form-control" id="nome" placeholder="Nome da Máquina" required>
                <label for="nome">Nome da Máquina</label>
            </div>
            <div class="form-floating mb-3">
                <input type="number" step="0.01" name="pesoTotal" class="form-control" id="pesoTotal" placeholder="Peso Total" required>
                <label for="pesoTotal">Peso Total (kg)</label>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" name="quebrada" class="form-check-input" id="quebrada">
                <label class="form-check-label" for="quebrada">Está Quebrada?</label>
            </div>
            <button class="btn btn-primary w-100 py-2 mt-2" type="submit">Salvar Máquina</button>
        </form>
    </section>

    <section id="listaMaquinas" class="mt-5">
        <h1 class="h3 mb-3 fw-normal">Lista de Máquinas</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                <% if (lista != null && !lista.isEmpty()) { %>
                    <% for (MaquinaDTO maquina : lista) { %>
                    <tr>
                        <td><%= maquina.getNome() %></td>
                        <td>
                            <!-- Botão para exibir os detalhes da máquina -->
                            <button type="button" class="btn btn-info btn-sm" onclick="toggleDetails('<%= maquina.getUuid() %>')">Detalhes</button>

                            <!-- Botão renomeado para "Deletar" sem alterar a ação -->
                            <button type="button" class="btn btn-warning btn-sm">Deletar</button>
                        </td>
                    </tr>
                    <!-- Linha de detalhes da máquina (escondida por padrão) -->
                    <tr id="details-<%= maquina.getUuid() %>" style="display: none;">
                        <td colspan="2">
                            <strong>Peso Total:</strong> <%= maquina.getPesoTotal() %> kg<br>
                            <strong>Quebrada:</strong> <%= maquina.isQuebrada() ? "Sim" : "Não" %>
                        </td>
                    </tr>
                    <% } %>
                <% } else { %>
                    <tr>
                        <td colspan="2" class="text-center">Nenhuma máquina encontrada</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </section>

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<%
    } else {
        response.sendRedirect("login.jsp");
    }
%>
