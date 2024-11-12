<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.dto.ExercicioDTO" %>
<%@ page import="br.mendonca.testemaven.services.ExercicioService" %>

<%
  if (session.getAttribute("user") != null) {
    ExercicioService exercicioService = new ExercicioService();
    List<ExercicioDTO> exercicios = exercicioService.listAllExercicios();
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
  					</ul>
  					<span class="navbar-text">
  						<a class="btn btn-success" href="/auth/logoff">Logoff</a>
  					</span>
  				</div>
  			</div>
  		</nav>

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

    <%
      String mensagemSucesso = (String) request.getAttribute("mensagemSucesso");
      String mensagemErro = (String) request.getAttribute("mensagemErro");
      if (mensagemSucesso != null) {
    %>
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
          <strong>Quantidade de Séries:</strong> <%= exercicio.getQuantidadeSeries() %> |
          <strong>Disponível:</strong> <%= exercicio.isDisponivelNaAcademia() ? "Sim" : "Não" %>
        </div>
      </li>
      <% } %>
    </ul>
  </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

<%
  } else {
    response.sendRedirect("/login.jsp");
  }
%>
