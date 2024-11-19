<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.ProfessorDTO" %>
<%@ page import="br.mendonca.testemaven.services.ProfessorService" %>

<%
  if (session.getAttribute("user") != null) {
    ProfessorService professorService = new ProfessorService();
    int pagina = request.getParameter("pagina") != null ? Integer.parseInt(request.getParameter("pagina")) : 0;
    int tamanhoPagina = 3;
    List<ProfessorDTO> professores = professorService.listProfessoresPaginados(pagina, tamanhoPagina);
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Registrar Professor</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="/dashboard/dashboard.jsp">Gerência de Configuração</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item"><a class="nav-link" href="/dashboard/dashboard.jsp">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/list-users.jsp">Users</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/list-professor.jsp">Registrar Professor</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/professoresDeletados.jsp">Professores Excluídos</a></li>
          </ul>
          <span class="navbar-text">
            <a class="btn btn-success" href="/auth/logoff">Logoff</a>
          </span>
        </div>
      </div>
  </nav>

  <!-- Formulário de Registro -->
  <form action="/dashboard/register" method="post">
    <h1 class="h3 mb-3 fw-normal">Cadastrar novo professor:</h1>

    <div class="form-floating mb-3">
      <input type="text" name="nome" class="form-control" id="floatingNome" placeholder="Nome do professor" required />
      <label for="floatingNome">Nome</label>
    </div>

    <div class="form-floating mb-3">
      <input type="number" name="idade" class="form-control" id="floatingIdade" placeholder="Idade do professor" required min="18" />
      <label for="floatingIdade">Idade</label>
    </div>

    <div class="form-check mb-3">
      <input class="form-check-input" type="checkbox" name="estaPresente" id="flexCheckPresente" />
      <label class="form-check-label" for="flexCheckPresente">Está presente na instituição?</label>
    </div>

    <button class="btn btn-primary w-100 py-2 mt-2" type="submit">Cadastrar Professor</button>

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

  <!-- Lista de Professores Cadastrados -->
  <div class="mt-5">
    <h2>Professores Cadastrados</h2>
      <ul class="list-group">
        <% for (ProfessorDTO professor : professores) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
          <div>
            <strong>Nome:</strong> <%= professor.getName() %> |
          </div>
          <!-- Botão para abrir o modal -->
          <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#modalProfessor<%= professor.getUuid() %>">
            Detalhes
          </button>
          <button type="button" class="btn btn-danger" onclick="marcarComoInvisivel('<%= professor.getUuid() %>')">
            Deletar
          </button>
        </li>

        <!-- Modal para cada professor -->
        <div class="modal fade" id="modalProfessor<%= professor.getUuid() %>" tabindex="-1" aria-labelledby="modalLabel<%= professor.getUuid() %>" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="modalLabel<%= professor.getUuid() %>">Detalhes do Professor</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <p><strong>Nome:</strong> <%= professor.getName() %></p>
                <p><strong>Idade:</strong> <%= professor.getIdade() %></p>
                <p><strong>Presente:</strong> <%= professor.isEstaPresente() ? "Sim" : "Não" %></p>
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
    <nav aria-label="Paginação de Professores" class="mt-3">
      <ul class="pagination justify-content-center">
        <% if (pagina > 0) { %>
          <li class="page-item">
            <a class="page-link" href="list-professor.jsp?pagina=<%= pagina - 1 %>">Anterior</a>
          </li>
        <% } %>
        <li class="page-item">
          <a class="page-link" href="list-professor.jsp?pagina=<%= pagina + 1 %>">Próxima</a>
        </li>
      </ul>
    </nav>
  </div>

</main>

<!-- Correção do JavaScript -->
<script>
  function marcarComoInvisivel(uuid) {
    if (confirm("Deseja realmente excluir este professor da listagem?")) {
      fetch('/professor/marcarComoInvisivel?uuid=' + uuid, { method: 'POST' })
              .then(response => {
                if (response.ok) {
                  alert("professor marcado para não visualização.");
                  window.location.reload(true);
                } else {
                  alert("Erro ao excluir o professor.");
                }
              });
    }
  }
</script>

<script src="https://unpkg.com/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>

<%
  } else {
    response.sendRedirect("/auth/login.jsp");
  }
%>
