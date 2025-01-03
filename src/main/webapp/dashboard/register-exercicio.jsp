<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.ExercicioDTO" %>
<%@ page import="br.mendonca.testemaven.services.ExercicioService" %>

<%
  if (session.getAttribute("user") != null) {
    ExercicioService exercicioService = new ExercicioService();
    int pagina = request.getParameter("pagina") != null ? Integer.parseInt(request.getParameter("pagina")) : 0;
    int tamanhoPagina = 3;
    List<ExercicioDTO> exercicios = exercicioService.listExerciciosPaginados(pagina, tamanhoPagina);
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
            <li class="nav-item"><a class="nav-link" href="/dashboard/users.jsp">Users</a></li>
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

  <!-- Formulário de Registro -->
  <form action="/registerexercicio" method="post">
    <h1 class="h3 mb-3 fw-normal">Cadastrar novo exercício:</h1>

    <div class="form-floating mb-3">
      <input type="text" name="nome" class="form-control" id="floatingNome" placeholder="Nome do exercício" required />
      <label for="floatingNome">Nome</label>
    </div>

    <div class="form-floating mb-3">
      <input type="number" name="quantidadeSeries" class="form-control" id="floatingSeries" placeholder="Quantidade de séries" required min="1" />
      <label for="floatingSeries">Quantidade de Séries</label>
    </div>

    <div class="form-check mb-3">
      <input class="form-check-input" type="checkbox" name="disponivel" id="flexCheckDisponivel" />
      <label class="form-check-label" for="flexCheckDisponivel">Disponível na academia?</label>
    </div>

    <button class="btn btn-primary w-100 py-2 mt-2" type="submit">Cadastrar Exercício</button>

    <% String mensagemSucesso = (String) request.getAttribute("mensagemSucesso");
       String mensagemErro = (String) request.getAttribute("mensagemErro");
       if (mensagemSucesso != null) { %>
      <div class="alert alert-success mt-3" role="alert">
        <%= mensagemSucesso %>
      </div>
    <% } else if (mensagemErro != null) { %>
      <div class="alert alert-danger mt-3" role="alert">
        <%= mensagemErro %>
      </div>
    <% } %>
  </form>

  <!-- Lista de Exercícios Cadastrados -->
  <div class="mt-5">
    <h2>Exercícios Cadastrados</h2>
      <ul class="list-group">
        <% for (ExercicioDTO exercicio : exercicios) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
          <div>
            <strong>Nome:</strong> <%= exercicio.getNome() %> |
          </div>
          <!-- Botão para abrir o modal -->
          <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#modalExercicio<%= exercicio.getUuid() %>">
            Detalhes
          </button>
          <button type="button" class="btn btn-danger" onclick="marcarComoInvisivel('<%= exercicio.getUuid() %>')">
            Deletar
          </button>
        </li>

        <!-- Modal para cada exercício -->
        <div class="modal fade" id="modalExercicio<%= exercicio.getUuid() %>" tabindex="-1" aria-labelledby="modalLabel<%= exercicio.getUuid() %>" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="modalLabel<%= exercicio.getUuid() %>">Detalhes do Exercício</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <p><strong>Nome:</strong> <%= exercicio.getNome() %></p>
                <p><strong>Quantidade de Séries:</strong> <%= exercicio.getQuantidadeSeries() %></p>
                <p><strong>Disponível:</strong> <%= exercicio.isDisponivelNaAcademia() ? "Sim" : "Não" %></p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
              </div>
            </div>
          </div>
        </div>
        <% } %>
      </ul>

    <!-- Controles de Paginação -->
    <nav aria-label="Paginação de Exercícios" class="mt-3">
      <ul class="pagination justify-content-center">
        <% if (pagina > 0) { %>
          <li class="page-item">
            <a class="page-link" href="register-exercicio.jsp?pagina=<%= pagina - 1 %>">Anterior</a>
          </li>
        <% } %>
        <li class="page-item">
          <a class="page-link" href="register-exercicio.jsp?pagina=<%= pagina + 1 %>">Próxima</a>
        </li>
      </ul>
    </nav>
  </div>

</main>

<script>
  function marcarComoInvisivel(uuid) {
    if (confirm("Deseja realmente excluir este exercicio da listagem?")) {
      fetch('/exercicio/marcarComoInvisivel?uuid=' + uuid, { method: 'POST' })
              .then(response => {
                if (response.ok) {
                  alert("Exercicio marcada para não visualização.");
                  window.location.reload(true);
                } else {
                  alert("Erro ao excluir a exercicio.");
                }
              });
    }
  }
</script>

<script src="https://unpkg.com/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous">

</script>
</body>
</html>

<%
  } else {
    response.sendRedirect("/login.jsp");
  }
%>
