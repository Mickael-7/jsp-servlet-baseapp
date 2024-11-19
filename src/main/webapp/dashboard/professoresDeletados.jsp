<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.ProfessorDTO" %>
<%@ page import="br.mendonca.testemaven.services.ProfessorService" %>

<%
    if (session.getAttribute("user") != null) {
        ProfessorService professorService = new ProfessorService();
        int tamanhoPagina = 3;
        int pageNumber = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        List<ProfessorDTO> professores = professorService.listAllProfessorsDesativadosPaginados(pageNumber, tamanhoPagina);
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Professores Deletados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">

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

    <div class="container mt-4">
        <!-- Lista de Professores Deletados -->
        <div class="mt-5">
            <h2>Professores Deletados</h2>
            <ul class="list-group">
                <% for (ProfessorDTO professor : professores) { %>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div>
                        <strong>Nome:</strong> <%= professor.getName() %> |
                    </div>
                    <div class="d-flex gap-2">
                        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#detalhesModal<%= professor.getUuid() %>">
                            Ver Detalhes
                        </button>
                    </div>
                </li>
                <!-- Modal para detalhes do professor -->
                <div class="modal fade" id="detalhesModal<%= professor.getUuid() %>" tabindex="-1" aria-labelledby="detalhesModalLabel<%= professor.getUuid() %>" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="detalhesModalLabel<%= professor.getUuid() %>">Detalhes do Professor</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p><strong>Nome:</strong> <%= professor.getName() %></p>
                                <p><strong>Idade:</strong> <%= professor.getIdade() %> anos</p>
                                <p><strong>Presente:</strong> <%= professor.isEstaPresente() ? "Sim" : "Não" %></p>
                                <!-- Adicione outras informações aqui se houver -->
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <% } %>
            </ul>

            <!-- Navegação de Páginas -->
            <div class="mt-4">
                <nav>
                    <ul class="pagination">
                        <li class="page-item <%= (pageNumber <= 0) ? "disabled" : "" %>">
                            <a class="page-link" href="?page=<%= pageNumber - 1 %>">Anterior</a>
                        </li>
                        <!-- Adicione mais lógica de paginação, se necessário -->
                        <li class="page-item">
                            <a class="page-link" href="?page=<%= pageNumber + 1 %>">Próxima</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>

<%
    } else {
        response.sendRedirect("/login.jsp");
    }
%>
