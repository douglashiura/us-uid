package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.douglashiura.leb.uid.scenario.data.OnUser;
import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.data.ProjectScenario;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleName;
import net.douglashiura.leb.uid.scenario.data.primitive.UserInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameBiggerThat30Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameNullException;

@WebServlet("/projetcs/*")
public class WebProjects extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProjectScenario scenario = (ProjectScenario) request.getServletContext()
				.getAttribute(ListenerConfigProjectScenario.SCENARIO_APP);
		String user = request.getParameter("user");
		try {
			OnUser onUser = scenario.onUser(new SimpleName(user));

			response.setContentType("text/plain; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			List<Project> projects = onUser.listProjects();
			StringBuilder json = new StringBuilder();
			json.append("[");
			int i = 0;
			for (Project folder : projects) {
				json.append("\"");
				json.append(folder);
				if (++i == projects.size()) {
					json.append("\"");
				} else {
					json.append("\", ");
				}
			}
			json.append("]");
			response.getWriter().write(json.toString());
		} catch (UserInvalidException | UsernameNullException | UsernameEmptyException | UsernameBiggerThat30Exception
				| UsernameInvalidException error) {
			throw new ServletException(error);
		}
	}

}
