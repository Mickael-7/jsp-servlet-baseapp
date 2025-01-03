package br.mendonca.testemaven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import br.mendonca.testemaven.services.UserService;
import br.mendonca.testemaven.services.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard/users")
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name"); // Parâmetro de pesquisa
			UserService service = new UserService();
			List<UserDTO> lista;

			if (name != null && !name.isEmpty()) {
				lista = service.searchByName(name); // Filtra pelo nome
			} else {
				lista = service.listAllUsers(); // Lista todos os usuários
			}

			// Garante que a lista seja atribuída mesmo se estiver vazia
			if (lista == null) {
				lista = new ArrayList<>();
			}

			request.setAttribute("lista", lista); // Atribui a lista ao request
			request.setAttribute("searchQuery", name); // Atribui a consulta ao request (para reutilizar no formulário)
			request.getRequestDispatcher("/dashboard/list-users.jsp").forward(request, response); // Redireciona para o JSP
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao carregar usuários.");
		}
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();
		
		try {
			// A programa��o do servlet deve ser colocada neste bloco try.
			// Apague o conte�do deste bloco try e escreva seu c�digo.
			String parametro = request.getParameter("nomeparametro");
			
			page.println("Parametro: " + parametro);
			page.close();
			
			
		} catch (Exception e) {
			// Escreve as mensagens de Exception em uma p�gina de resposta.
			// N�o apagar este bloco.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			page.println("<html lang='pt-br'><head><title>Error</title></head><body>");
			page.println("<h1>Error</h1>");
			page.println("<code>");
			page.println(sw.toString());
			page.println("</code>");
			page.println("</body></html>");
			page.close();
		} finally {
			
		}
	}
}
