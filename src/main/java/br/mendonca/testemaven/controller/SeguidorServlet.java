package br.mendonca.testemaven.controller;

import br.mendonca.testemaven.services.SeguidorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerseguidor")
public class SeguidorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Injeção de dependência (idealmente usando um framework como Spring)
    private final SeguidorService seguidorService = new SeguidorService();

    // Método GET para renderizar o formulário
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            page.println("<html lang='pt-br'><head><title>Registrar Seguidor</title></head><body>");
            page.println("<h1>Registrar Seguidor</h1>");
            page.println("<form method='post' action='/registerseguidor'>");
            page.println("  UUID do Seguidor: <input type='text' name='seguidorUuid' required><br>");
            page.println("  UUID do Seguido: <input type='text' name='seguidoUuid' required><br>");
            page.println("  <input type='submit' value='Registrar Seguidor'>");
            page.println("</form>");
            page.println("</body></html>");
        } catch (Exception e) {
            page.println("<html lang='pt-br'><head><title>Erro</title></head><body>");
            page.println("<h1>Erro</h1>");
            page.println("<p>" + e.getMessage() + "</p>");
            page.println("</body></html>");
        } finally {
            page.close();
        }
    }

    // Método POST para processar o formulário
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            // Obtenção dos parâmetros do formulário
            String seguidorUuid = request.getParameter("seguidorUuid");
            String seguidoUuid = request.getParameter("seguidoUuid");

            // Criação do seguidor
            seguidorService.addSeguidor(seguidorUuid, seguidoUuid);

            // Feedback ao usuário e redirecionamento
            response.sendRedirect("/dashboard/dashboard.jsp");
        } catch (Exception e) {
            page.println("<html lang='pt-br'><head><title>Erro</title></head><body>");
            page.println("<h1>Ocorreu um erro inesperado.</h1>");
            page.println("<code>" + e.getMessage() + "</code>");
            page.println("</body></html>");
        } finally {
            page.close();
        }
    }
}
