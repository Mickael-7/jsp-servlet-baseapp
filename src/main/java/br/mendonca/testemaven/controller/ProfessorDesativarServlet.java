package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.services.ProfessorService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/professor/marcarComoInvisivel")
public class ProfessorDesativarServlet extends HttpServlet {
    private ProfessorService estrelaService = new ProfessorService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = request.getParameter("uuid");
        try {
            estrelaService.desativarProfessor(uuid);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
