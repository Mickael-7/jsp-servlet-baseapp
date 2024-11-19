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
      pagina = 0;
    }

    List<MaquinaDTO> maquinas = maquinaService.listMachinesPaginated(pagina, tamanhoPagina);
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
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="/dashboard/dashboard.jsp">Gestão de Máquinas</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
          aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item"><a class="nav-link" href="/dashboard/dashboard.jsp">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/users.jsp">Users</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/about.jsp">About</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/list-maquinas.jsp">Máquinas</a></li>
            <li class="nav-item"><a class="nav-link" href="/dashboard/list-maquinas-excluidos.jsp">Excluídas</a></li>
          </ul>
          <span class="navbar-text">
            <a class="btn btn-success" href="/auth/logoff">Logoff</a>
          </span>
        </div>
      </div>
  </nav>

  <!-- Formulário de Cadastro -->
  <form action="/dashboard/machine" method="post">
    <h1 class="h3 mb-3 fw-normal">Cadastrar nova maquina:</h1>

    <div class="form-floating mb-3">
      <input type="text" name="nome" class="form-control" id="floatingNome" placeholder="Nome da maquina" required />
      <label for="floatingNome">Nome</label>
    </div>

    <div class="form-floating mb-3">
      <input type="number" name="pesoTotal" class="form-control" id="floatingSeries" placeholder="Peso Total" required min="1" />
      <label for="floatingSeries">peso Total(kg)</label>
    </div>

    <div class="form-check mb-3">
      <input class="form-check-input" type="checkbox" name="disponivel" id="flexCheckDisponivel" />
      <label class="form-check-label" for="flexCheckDisponivel">Etá quebrada?</label>
    </div>

    <button class="btn btn-primary w-100 py-2 mt-2" type="submit">Cadastrar Maquina</button>

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
    <h2>Maquinas Cadastrados</h2>
      <ul class="list-group">
        <% for (MaquinaDTO maquina : maquinas) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
          <div>
            <strong>Nome:</strong> <%= maquina.getNome() %> |
          </div>
          <!-- Botão para abrir o modal -->
          <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#modalMaquina<%= maquina.getUuid() %>">
            Detalhes
          </button>
          <button type="button" class="btn btn-danger" onclick="marcarComoInvisivel('<%= maquina.getUuid() %>')">
            Deletar
          </button>
        </li>

        <!-- Modal para cada exercício -->
        <div class="modal fade" id="modalMaquina<%= maquina.getUuid() %>" tabindex="-1" aria-labelledby="modalLabel<%= maquina.getUuid() %>" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="modalLabel<%= maquina.getUuid() %>">Detalhes do Exercício</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <p><strong>Nome:</strong> <%= maquina.getNome() %></p>
                <p><strong>Peso total(kg):</strong> <%= maquina.getPesoTotal() %></p>
                <p><strong>Está quebrada:</strong> <%= maquina.isQuebrada() ? "Sim" : "Não" %></p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
              </div>
            </div>
          </div>
        </div>
        <% } %>
      </ul>


    <!-- Paginação -->
    <nav aria-label="Paginação de Máquinas" class="mt-3">
      <ul class="pagination justify-content-center">
        <% if (pagina > 0) { %>
          <li class="page-item">
            <a class="page-link" href="list-maquinas.jsp?pagina=<%= pagina - 1 %>">Anterior</a>
          </li>
        <% } %>
        <li class="page-item">
          <a class="page-link" href="list-maquinas.jsp?pagina=<%= pagina + 1 %>">Próxima</a>
        </li>
      </ul>
    </nav>
  </div>

</main>
<!-- Modal para Detalhes -->
<div class="modal fade" id="modalDetalhes" tabindex="-1" aria-labelledby="modalDetalhesLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="modalDetalhesLabel">Detalhes da Máquina</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p><strong>Nome:</strong> <span id="detalheNome"></span></p>
          <p><strong>Peso Total:</strong> <span id="detalhePeso"></span> kg</p>
          <p><strong>Está Quebrada?</strong> <span id="detalheQuebrada"></span></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
        </div>
      </div>
    </div>
  </div>
<script>
    async function exibirDetalhes(uuid) {
        try {
            const response = await fetch(`/maquina/detalhes?uuid=${uuid}`);
            if (response.ok) {
                const maquina = await response.json();
                document.getElementById('detalheNome').textContent = maquina.nome;
                document.getElementById('detalhePeso').textContent = maquina.pesoTotal;
                document.getElementById('detalheQuebrada').textContent = maquina.quebrada ? 'Sim' : 'Não';
                const modal = new bootstrap.Modal(document.getElementById('modalDetalhes'));
                modal.show();
            } else {
                alert('Erro ao carregar detalhes da máquina.');
            }
        } catch (error) {
            alert('Erro na comunicação com o servidor.');
        }
    }

    function marcarComoInvisivel(uuid) {
        if (confirm("Deseja realmente excluir esta máquina da listagem?")) {
            fetch('/maquina/marcarComoInvisivel?uuid=' + uuid, { method: 'POST' })
                .then(response => {
                    if (response.ok) {
                        alert("Máquina marcada para não visualização.");
                        window.location.reload(true);
                    } else {
                        alert("Erro ao excluir a máquina.");
                    }
                })
                .catch(() => {
                    alert("Erro na comunicação com o servidor.");
                });
        }
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%
  } else {
    response.sendRedirect("/auth/login.jsp");
  }
%>
