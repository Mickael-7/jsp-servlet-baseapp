package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.mendonca.testemaven.services.MaquinaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/machine")
public class MaquinaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            page.println("<html lang='pt-br'><head><title>Criar Máquina</title></head><body>");
            page.println("<h1>Criar Nova Máquina</h1>");
            page.println("<form method='post' action='/dashboard/machine'>");
            page.println("Nome da Máquina: <input type='text' name='nome'><br>");
            page.println("Peso Total (kg): <input type='number' step='0.01' name='pesoTotal'><br>");
            page.println("Está Quebrada? <input type='checkbox' name='quebrada'><br>");
            page.println("<input type='submit' value='Criar Máquina'>");
            page.println("  Ocultar Maquinas: <input type='checkbox' name='oculta'><br>");
            page.println("</form>");
            page.println("</body></html>");
            page.close();

        } catch (Exception e) {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter page = response.getWriter();

        try {
            String nome = request.getParameter("nome");
            float pesoTotal = Float.parseFloat(request.getParameter("pesoTotal"));
            boolean quebrada = request.getParameter("quebrada") != null;
            boolean oculta = request.getParameter("oculta") != null
                    ? Boolean.parseBoolean(request.getParameter("oculta"))
                    : true;
            ;

            MaquinaService service = new MaquinaService();
            service.register(nome, pesoTotal, quebrada, oculta);

            page.println("<html lang='pt-br'><head><title>Salvo com Sucesso</title>");
            page.println("<script type='text/javascript'>");
            page.println("alert('Maquina salva com sucesso!');");
            page.println("window.location.href = '/dashboard/list-maquinas.jsp';");
            page.println("</script>");
            page.println("</head><body></body></html>");
            page.close();

        } catch (NumberFormatException e) {
            page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
            page.println("<h1>Erro: Peso total deve ser um número.</h1>");
            page.println("</body></html>");
            page.close();
        } catch (Exception e) {
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
}
