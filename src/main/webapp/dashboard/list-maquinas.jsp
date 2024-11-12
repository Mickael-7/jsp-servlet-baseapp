<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.MaquinaDTO" %>
<%@ page import="br.mendonca.testemaven.services.MaquinaService" %>

<%
    if (session.getAttribute("user") != null) {
        MaquinaService maquinaService = new MaquinaService();

        // Lógica de paginação
        int pagina = request.getParameter("pagina") != null ? Integer.parseInt(request.getParameter("pagina")) : 0;
        int tamanhoPagina = 3; // Defina o tamanho da página
        List<MaquinaDTO> lista = maquinaService.listMaquinasPaginadas(pagina, tamanhoPagina);
        int totalMaquinas = maquinaService.getTotalMaquinas();
        int totalPaginas = (int) Math.ceil((double) totalMaquinas / tamanhoPagina);
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestão de Máquinas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
    <section id="listaMaquinas" class="mt-5">
        <h1 class="h3 mb-3 fw-normal">Lista de Máquinas</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Peso Total (kg)</th>
                    <th scope="col">Quebrada</th>
                </tr>
            </thead>
            <tbody>
                <% if (lista != null && !lista.isEmpty()) { %>
                    <% for (MaquinaDTO maquina : lista) { %>
                    <tr>
                        <td><%= maquina.getNome() %></td>
                        <td><%= maquina.getPesoTotal() %></td>
                        <td><%= maquina.isQuebrada() ? "Sim" : "Não" %></td>
                    </tr>
                    <% } %>
                <% } else { %>
                    <tr>
                        <td colspan="3" class="text-center">Nenhuma máquina encontrada</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </section>

    <!-- Controles de Paginação -->
    <nav aria-label="Paginação de Máquinas" class="mt-3">
        <ul class="pagination justify-content-center">
            <% if (pagina > 0) { %>
                <li class="page-item">
                    <a class="page-link" href="list-maquinas.jsp?pagina=<%= pagina - 1 %>">Anterior</a>
                </li>
            <% } %>
            <% for (int i = 0; i < totalPaginas; i++) { %>
                <li class="page-item <%= (i == pagina) ? "active" : "" %>">
                    <a class="page-link" href="list-maquinas.jsp?pagina=<%= i %>"><%= i + 1 %></a>
                </li>
            <% } %>
            <% if (pagina < totalPaginas - 1) { %>
                <li class="page-item">
                    <a class="page-link" href="list-maquinas.jsp?pagina=<%= pagina + 1 %>">Próxima</a>
                </li>
            <% } %>
        </ul>
    </nav>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%
    } else {
        response.sendRedirect("login.jsp");
    }
%>
