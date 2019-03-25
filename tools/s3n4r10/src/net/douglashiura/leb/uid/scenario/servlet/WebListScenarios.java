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
@WebServlet("/scenarios/*")
public class WebListScenarios extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String NAME = "scenario";
	private static final String FOLDER = "folder";
	private String EDITOR_JSP = "Editor.jsp?scenario=files" + File.separatorChar + "%s" + File.separatorChar + "%s.us";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String folder = req.getParameter(FOLDER).toString();
		String name = req.getParameter(NAME).toString();
		String[] directories = folder.split("\\.");
		Project project = Project.get();
		for (String path : directories) {
			project = project.enter(path);
		}
		project.newScenario(name);
		resp.sendRedirect(String.format(EDITOR_JSP, folder.replace('.', File.separatorChar), name));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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