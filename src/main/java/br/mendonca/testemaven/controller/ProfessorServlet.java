package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import br.mendonca.testemaven.services.ProfessorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/register")
public class ProfessorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();
        try {
            page.println("<html lang='pt-br'><head><title>Registrar Professor</title></head><body>");
            page.println("<h1>Registrar Professor</h1>");
            page.println("<form method='post' action='/professor/register'>");
            page.println("  UUID: <input type='text' name='uuid'><br>");
            page.println("  Nome: <input type='text' name='name'><br>");
            page.println("  Idade: <input type='number' name='idade'><br>");
            page.println("  Presente: <input type='checkbox' name='estaPresente'><br>");
            page.println("  <input type='submit' value='Registrar Professor'>");
            page.println("</form>");
            page.println("</body></html>");
            page.close();
        } catch (Exception e) {
            handleError(page, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();
        try {
            String name = request.getParameter("nome");
            int idade = Integer.parseInt(request.getParameter("idade"));
            boolean estaPresente = request.getParameter("estaPresente") != null;

            ProfessorService service = new ProfessorService();
            service.register( name, idade, estaPresente);
            response.sendRedirect("/dashboard/list-professor.jsp");

        } catch (NumberFormatException e) {
            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Erro: Idade deve ser um número.</h1>");
            page.println("</body></html>");
            page.close();
        } catch (Exception e) {
            handleError(page, e);
        }
    }

    private void handleError(PrintWriter page, Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
        page.println("<h1>Error</h1>");
        page.println("<code>" + sw.toString() + "</code>");
        page.println("</body></html>");
        page.close();
    }
}
