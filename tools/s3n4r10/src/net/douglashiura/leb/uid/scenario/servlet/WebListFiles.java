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
		String path = request.getRequestURI().split("files")[1];
		if (path.endsWith(".us")) {
			response.setContentType("text/plain; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			try {
				path = new java.net.URI(path).getPath();
				String scenario = Project.get(path).getScenario().getDocument();
				response.getWriter().write(scenario);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

		} else {
			List<String> scenarios = Project.get(path).getScenariesAsNames();
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
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String path = request.getRequestURI().split("files")[1];
			path = new java.net.URI(path).getPath();
			byte[] all = new byte[request.getContentLength()];
			IOUtils.readFully(request.getInputStream(), all);
			Project.get(extractDiretory(path)).newScenario(extractFile(path).replace(FilterScenario.EXTENSION, ""))
					.write(all);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ExceptionNotAFile e) {
			e.printStackTrace();
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
