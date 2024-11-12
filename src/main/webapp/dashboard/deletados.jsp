<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.mendonca.testemaven.services.MaquinaService" %>
<%@ page import="br.mendonca.testemaven.services.dto.MaquinaDTO" %>

<%
    // Inicializar o serviço de máquinas
    MaquinaService maquinaService = new MaquinaService();

    // Obter a lista de máquinas ocultas
    List<MaquinaDTO> listaOcultos = maquinaService.listHiddenMachines(); // Apenas máquinas ocultas
%>

<h1>Máquinas Deletadas</h1>
<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col">Nome</th>
            <th scope="col">Ações</th>
        </tr>
    </thead>
    <tbody>
        <% if (listaOcultos != null && !listaOcultos.isEmpty()) { %>
            <% for (MaquinaDTO maquina : listaOcultos) { %>
                <tr>
                    <td><%= maquina.getNome() %></td>
                    <td>
                        <!-- Botão para restaurar a máquina -->
                        <form action="/dashboard/restore-machine" method="post" style="display: inline;">
                            <input type="hidden" name="uuid" value="<%= maquina.getUuid() %>">
                            <button type="submit" class="btn btn-success btn-sm">Restaurar</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="2" class="text-center">Nenhuma máquina oculta encontrada</td>
            </tr>
        <% } %>
    </tbody>
</table>
