<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.ExercicioDTO" %>
<%@ page import="br.mendonca.testemaven.services.ExercicioService" %>
<%
  if (session.getAttribute("user") != null) {
    ExercicioService exercicioService = new ExercicioService();
    int pagina = request.getParameter("pagina") != null ? Integer.parseInt(request.getParameter("pagina")) : 0;
    int tamanhoPagina = 3;
    List<ExercicioDTO> exercicios = exercicioService.listExerciciosExcluidosPaginados(pagina, tamanhoPagina);
%>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Registrar Exercício</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="/dashboard/dashboard.jsp">Gerência de Configuração</a>
        <button class="navbar-toggler" type="button"
          data-bs-toggle="collapse" data-bs-target="#navbarText"
          aria-controls="navbarText" aria-expanded="false"
          aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item"><a class="nav-link" href="/dashboard/dashboard.jsp">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/users">Users</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/register-exercicio.jsp">Registrar Exercicio</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/list-exercicios-excluidos.jsp">Exercicios Excluidos</a></li>
          </ul>
          <span class="navbar-text">
            <a class="btn btn-success" href="/auth/logoff">Logoff</a>
          </span>
        </div>
      </div>
  </nav>
    <!-- Lista de Exercícios Excluidos -->
    <div class="mt-5">
      <h2>Exercícios Excluidos</h2>
        <ul class="list-group">
          <% for (ExercicioDTO exercicio : exercicios) { %>
          <li class="list-group-item d-flex justify-content-between align-items-center">
            <div>
              <strong>Nome:</strong> <%= exercicio.getNome() %> |
              <strong>Quantidade de Series:</strong> <%= exercicio.getQuantidadeSeries() %> |
              <strong>Disponível:</strong> <%= exercicio.isDisponivelNaAcademia() ? "Sim" : "Não" %>
            </div>
          </li>
          <% } %> <!-- Fechamento do loop for -->

      <!-- Controles de Paginação -->
      <nav aria-label="Paginação de Exercícios" class="mt-3">
        <ul class="pagination justify-content-center">
          <% if (pagina > 0) { %>
            <li class="page-item">
              <a class="page-link" href="list-exercicios-excluidos.jsp?pagina=<%= pagina - 1 %>">Anterior</a>
            </li>
          <% } %>
          <li class="page-item">
            <a class="page-link" href="list-exercicios-excluidos.jsp?pagina=<%= pagina + 1 %>">Próxima</a>
          </li>
        </ul>
      </nav>
    </div>

  </main>

  <script src="https://unpkg.com/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous">

  </script>
  </body>
  </html>

<%
  } else {
    response.sendRedirect("/login.jsp");
  } // Fechamento do bloco 'if'
%>
