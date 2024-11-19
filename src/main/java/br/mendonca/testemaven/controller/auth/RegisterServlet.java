package br.mendonca.testemaven.controller.auth;

import br.mendonca.testemaven.dao.UserDAO;
import br.mendonca.testemaven.model.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redireciona para o formulário de registro
		response.sendRedirect("form-register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();

		try {
			// Recupera os parâmetros enviados pelo formulário
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			String idadeStr = request.getParameter("idade");
			String statusStr = request.getParameter("status");

			// Valida os campos obrigatórios
			if (name == null || name.isEmpty() || email == null || email.isEmpty() || pass == null || pass.isEmpty()) {
				throw new IllegalArgumentException("Nome, e-mail e senha são obrigatórios!");
			}

			// Converte os campos adicionais
			int idade = Integer.parseInt(idadeStr);
			boolean status = Boolean.parseBoolean(statusStr);

			// Cria o objeto User
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(pass);
			user.setIdade(idade);
			user.setStatus(status);

			// Registra o usuário no banco
			UserDAO userDAO = new UserDAO();
			userDAO.register(user);

			// Redireciona para a página inicial
			response.sendRedirect("index.jsp");

		} catch (Exception e) {
			// Escreve as mensagens de Exception em uma página de resposta
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
