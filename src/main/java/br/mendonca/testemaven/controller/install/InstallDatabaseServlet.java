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
			String msg = "<h1>INSTALL DATABASE</h1>";

			service.testConnection();
			msg += "<h2>Connection DB successful!</h2>\n";

			// Exclui a tabela de usuários e cria uma nova
			service.deleteUserTable();
			msg += "<h2>Delete table user successful!</h2>\n";
			service.createUserTable();
			msg += "<h2>Create table user successful!</h2>\n";

			// Criação e exclusão de outras tabelas
			service.deleteExercicioTable();
			msg += "<h2>Delete table Exercicio sucessful!</h2>\n";

			service.createExercicioTable();
			msg += "<h2>Create table exercicio successful!</h2>\n";
			service.deleteMaquinaTable();
			msg += "<h2>Delete table machine successful!</h2>\n";
			service.createMaquinaTable();
			msg += "<h2>Create table machine successful!</h2>\n";

			// Imprime a mensagem de sucesso
			msg += "<h2>Create table exercicio sucessful!</h2>\n";

			page.println("<html lang='pt-br'><head><title>Teste</title></head><body>");
			page.println(msg);
			/*/
			page.println("<code>");
			for (Map.Entry<String,String> pair : env.entrySet()) {
			    page.println(pair.getKey());
			    page.println(pair.getValue());
			}
			//*/
			page.println("</code>");
			page.println("</body></html>");
			page.close();

		} catch (Exception e) {
			// Captura as mensagens de erro e exibe-as no HTML
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
