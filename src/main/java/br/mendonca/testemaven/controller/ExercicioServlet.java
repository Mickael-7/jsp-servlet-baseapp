package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.mendonca.testemaven.services.ExercicioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/end/point")
public class ModeloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();

		try {
			page.println("<html lang='pt-br'><head><title>Registrar Exercicio</title></head><body>");
			page.println("<h1>Registrar Exercício</h1>");
			page.println("<form method='post' action='/end/point'>");
			page.println("  Nome do Exercício: <input type='text' name='nome'><br>");
			page.println("  Quantidade de Séries: <input type='number' name='quantidadeSeries'><br>");
			page.println("  Disponível: <input type='checkbox' name='disponivel'><br>");
			page.println("  <input type='submit' value='Registrar Exercício'>");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();

		try {
			String nome = request.getParameter("nome");
			int quantidadeSeries = Integer.parseInt(request.getParameter("quantidadeSeries"));
			boolean disponivel = request.getParameter("disponivel") != null;

			ExercicioService service = new ExercicioService();
			service.register(nome, quantidadeSeries, disponivel);

			page.println("<html lang='pt-br'><head><title>Sucesso</title></head><body>");
			page.println("<h1>Exercício registrado com sucesso!</h1>");
			page.println("<p>Nome: " + nome + "</p>");
			page.println("<p>Quantidade de Séries: " + quantidadeSeries + "</p>");
			page.println("<p>Disponível: " + (disponivel ? "Sim" : "Não") + "</p>");
			page.println("</body></html>");
			page.close();

		} catch (NumberFormatException e) {
			page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
			page.println("<h1>Erro: Quantidade de séries deve ser um número.</h1>");
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
