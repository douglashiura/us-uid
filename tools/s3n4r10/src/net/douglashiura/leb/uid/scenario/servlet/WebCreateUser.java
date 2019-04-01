package net.douglashiura.leb.uid.scenario.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.douglashiura.leb.uid.scenario.Scenarios;
import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.data.Scenario;

/**
 * Servlet implementation class WebList
 */
@WebServlet("/newAccount/*")
public class WebCreateUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String USER = "user";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private String EDITOR_JSP = "user/%s";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		String folder = req.getParameter(EMAIL).toString();
		String name = req.getParameter(USER).toString();
		String password = req.getParameter(PASSWORD).toString();
		
		resp.sendRedirect(String.format(EDITOR_JSP, folder.replace('.', File.separatorChar), name));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		List<Scenario> scenarios = Scenarios.getScenarios(Project.get());
		StringBuilder json = new StringBuilder();
		json.append("[");
		int i = 0;
		for (Scenario scenario : scenarios) {
			json.append("\"");
			json.append(scenario.getVirtualName());
			if (++i == scenarios.size()) {
				json.append("\"");
			} else {
				json.append("\", ");
			}
		}
		json.append("]");

		response.getWriter().write(json.toString());
	}
}