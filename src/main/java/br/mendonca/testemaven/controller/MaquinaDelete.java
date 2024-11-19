package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.services.MaquinaService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/maquina/marcarComoInvisivel")
public class MaquinaDelete extends HttpServlet {
    private MaquinaService maquinaService = new MaquinaService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = request.getParameter("uuid");
        try {
            maquinaService.desativarMaquina(uuid);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}