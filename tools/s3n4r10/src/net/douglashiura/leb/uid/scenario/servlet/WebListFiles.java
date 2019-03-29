package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import net.douglashiura.leb.uid.scenario.data.FilterScenario;
import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.servlet.util.NotAFileException;
import net.douglashiura.leb.uid.scenario.servlet.util.FileUtil;

@WebServlet("/files/*")
public class WebListFiles extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String path = new java.net.URI(request.getRequestURI()).getPath().split("files")[1];
			if (path.endsWith(FilterScenario.EXTENSION)) {
				response.setContentType("text/plain; charset=utf-8");
				response.setCharacterEncoding("UTF-8");
				String scenario = Project.get(path).getScenario().getDocument();
				response.getWriter().write(scenario);

			} else {
				List<String> scenarios = Project.get(path).getScenariosAsNames();
				StringBuilder json = new StringBuilder();
				json.append("[");
				int i = 0;
				for (String scenario : scenarios) {
					json.append("\"");
					json.append(scenario);
					if (++i == scenarios.size()) {
						json.append("\"");
					} else {
						json.append("\", ");
					}
				}
				json.append("]");
				response.getWriter().write(json.toString());
			}
		} catch (URISyntaxException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String path = request.getRequestURI().split("files")[1];
			FileUtil file = new FileUtil(new java.net.URI(path));
			byte[] all = new byte[request.getContentLength()];
			IOUtils.readFully(request.getInputStream(), all);
			Project.get(file.getDirectory()).newScenario(file.getNameWithoutExtension()).write(all);
		} catch (URISyntaxException | NotAFileException e) {
			throw new ServletException(e);
		}
	}

}
