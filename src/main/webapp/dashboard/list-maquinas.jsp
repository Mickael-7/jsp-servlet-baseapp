<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="br.mendonca.testemaven.services.dto.MaquinaDTO"%>

<%
    List<MaquinaDTO> lista = (List<MaquinaDTO>) request.getAttribute("lista");
%>

<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Máquinas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">
    <h1 class="h3 mb-3 fw-normal">Lista de Máquinas</h1>

    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">UUID</th>
                <th scope="col">Nome</th>
                <th scope="col">Peso Total (kg)</th>
                <th scope="col">Quebrada</th>
            </tr>
        </thead>
        <tbody>
            <%
            if (lista != null && !lista.isEmpty()) {
                for (MaquinaDTO maquina : lista) {
            %>
            <tr>
                <td><%= maquina.getUuid() %></td>
                <td><%= maquina.getNome() %></td>
                <td><%= maquina.getPesoTotal() %></td>
                <td><%= maquina.isQuebrada() ? "Sim" : "Não" %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4" class="text-center">Nenhuma máquina encontrada</td>
            </tr>
            <%
            }
            %>
        </tbody>
    </table>

    <a href="/dashboard/novamaquina.jsp" class="btn btn-primary mt-3">Adicionar Nova Máquina</a>
    <a href="/dashboard/dashboard.jsp" class="btn btn-secondary mt-3">Voltar</a>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
