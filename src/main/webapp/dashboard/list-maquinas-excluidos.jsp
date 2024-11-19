<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.MaquinaDTO" %>
<%@ page import="br.mendonca.testemaven.services.MaquinaService" %>

<%
  if (session.getAttribute("user") != null) {
    MaquinaService maquinaService = new MaquinaService();
    int pagina = 0;
    int tamanhoPagina = 3;

    try {
      if (request.getParameter("pagina") != null) {
        pagina = Integer.parseInt(request.getParameter("pagina"));
      }
    } catch (NumberFormatException e) {
      pagina = 0; // Valor padrão em caso de erro
    }

    List<MaquinaDTO> listaOcultos = maquinaService.listMaquinasExcluidosPaginados(pagina, tamanhoPagina);
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Máquinas Deletadas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg bg-body-tertiary mb-4">
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
                    <li class="nav-item"><a class="nav-link active" href="/dashboard/list-maquinas-excluidos.jsp">Deletados</a></li>
                </ul>
                <span class="navbar-text">
                    <a class="btn btn-success" href="/auth/logoff">Logoff</a>
                </span>
            </div>
        </div>
    </nav>

   <!-- Lista de Exercícios Excluidos -->
   <div class="mt-5">
    <h2>Maquinas Excluidas</h2>
      <ul class="list-group">
        <% for (MaquinaDTO maquina : maquinas) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
          <div>
            <strong>Nome:</strong> <%= maquina.getNome() %> |
            <strong>Peso Total (kg):</strong> <%= maquina.getPesoTotal() %> |
            <strong>Está Quebrada:</strong> <%= maquina.isquebrada() ? "Sim" : "Não" %>
          </div>
        </li>
        <% } %> <!-- Fechamento do loop for -->


    <!-- Controles de Paginação -->
    <nav aria-label="Paginação de Máquinas" class="mt-3">
        <ul class="pagination justify-content-center">
          <% if (pagina > 0) { %>
            <li class="page-item">
              <a class="page-link" href="list-maquinas-excluidos.jsp?pagina=<%= pagina - 1 %>">Anterior</a>
            </li>
          <% } %>
          <li class="page-item">
            <a class="page-link" href="list-maquinas-excluidos.jsp?pagina=<%= pagina + 1 %>">Próxima</a>
          </li>
        </ul>
      </nav>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%
  } else {
    response.sendRedirect("/auth/login.jsp");
  } // Fechamento do bloco 'if'
%>
