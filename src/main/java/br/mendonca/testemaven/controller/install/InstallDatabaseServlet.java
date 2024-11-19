package br.mendonca.testemaven.controller.install;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import br.mendonca.testemaven.services.InstallService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/install")
public class InstallDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter page = response.getWriter();

		try {
			InstallService service = new InstallService();
			StringBuilder msg = new StringBuilder("<h1>INSTALL DATABASE</h1>");

			// Testa a conexão com o banco de dados
			service.testConnection();
			msg.append("<h2>Connection to DB successful!</h2>\n");

			// Exclui e recria a tabela de usuários
			service.deleteUserTable();
			msg.append("<h2>Deleted table 'users' successfully!</h2>\n");
			service.createUserTable();
			msg.append("<h2>Created table 'users' successfully!</h2>\n");

			// Exclui e recria a tabela de seguidores
			service.deleteSeguidorTable();
			msg.append("<h2>Deleted table 'seguidores' successfully!</h2>\n");
			service.createSeguidoresTable();
			msg.append("<h2>Created table 'seguidores' successfully!</h2>\n");

			// Exclui e recria a tabela de professores
			service.deleteProfessorTable();
			msg.append("<h2>Deleted table 'professor' successfully!</h2>\n");
			service.createProfessorTable();
			msg.append("<h2>Created table 'professor' successfully!</h2>\n");
			service.populateProfessorTable();
			msg.append("<h2>Inserted random professors successfully!</h2>\n");

			// Exclui e recria a tabela de exercícios
			service.deleteExercicioTable();
			msg.append("<h2>Deleted table 'exercicio' successfully!</h2>\n");
			service.createExercicioTable();
			msg.append("<h2>Created table 'exercicio' successfully!</h2>\n");
			service.populateExercicioTable();
			msg.append("<h2>Inserted random exercises successfully!</h2>\n");

			// Exclui e recria a tabela de máquinas
			service.deleteMaquinaTable();
			msg.append("<h2>Deleted table 'maquinas' successfully!</h2>\n");
			service.createMaquinaTable();
			msg.append("<h2>Created table 'maquinas' successfully!</h2>\n");
			service.populateMaquinaTable();
			msg.append("<h2>Inserted 7 random machines successfully!</h2>\n");



			// Exibe a página HTML com as mensagens
			page.println("<html lang='pt-br'><head><title>Instalação do Banco de Dados</title></head><body>");
			page.println(msg.toString());
			page.println("</body></html>");
			page.close();

		} catch (Exception e) {
			// Captura mensagens de erro e as exibe em uma página HTML
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
		}
	}
}
