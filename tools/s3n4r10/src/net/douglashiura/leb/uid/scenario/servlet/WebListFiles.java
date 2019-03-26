package net.douglashiura.leb.uid.scenario.servlet;

import java.io.File;
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
				System.out.println();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String path = request.getRequestURI().split("files")[1];
			path = new java.net.URI(path).getPath();
			byte[] all = new byte[request.getContentLength()];
			IOUtils.readFully(request.getInputStream(), all);
			String name = extractFile(path).replace(FilterScenario.EXTENSION, "");
			String directory = extractDiretory(path);
			Project.get(directory).newScenario(name).write(all);
		} catch (URISyntaxException | ExceptionNotAFile e) {
			throw new ServletException(e);
		}
	}

	private String extractFile(String path) throws ExceptionNotAFile {
		if (path.endsWith(FilterScenario.EXTENSION)) {
			return path.substring(path.lastIndexOf(File.separatorChar + ""));
		} else {
			throw new ExceptionNotAFile("Not a file");
		}
	}

	private String extractDiretory(String path) throws ExceptionNotAFile {
		return path.replace(extractFile(path), "");
	}
}
