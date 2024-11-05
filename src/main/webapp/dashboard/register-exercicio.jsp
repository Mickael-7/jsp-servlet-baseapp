<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
      <form action="/registerexercicio" method="post">
        <h1 class="h3 mb-3 fw-normal">Cadastrar novo exercício:</h1>

        <!-- Campo para o nome do exercício -->
        <div class="form-floating mb-3">
          <input type="text" name="nome" class="form-control" id="floatingNome" placeholder="Nome do exercício" required />
          <label for="floatingNome">Nome</label>
        </div>

        <!-- Campo para quantidade de séries -->
        <div class="form-floating mb-3">
          <input type="number" name="quantidadeSeries" class="form-control" id="floatingSeries" placeholder="Quantidade de séries" required min="1" />
          <label for="floatingSeries">Quantidade de Séries</label>
        </div>

        <!-- Checkbox para disponibilidade do exercício -->
        <div class="form-check mb-3">
          <input class="form-check-input" type="checkbox" name="disponivel" id="flexCheckDisponivel" />
          <label class="form-check-label" for="flexCheckDisponivel">Disponível na academia?</label>
        </div>

        <!-- Botão de submissão -->
        <button class="btn btn-primary w-100 py-2 mt-2" type="submit">Cadastrar Exercício</button>

        <!-- Exibir mensagem de sucesso ou erro, se houver -->
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
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  </body>
</html>
