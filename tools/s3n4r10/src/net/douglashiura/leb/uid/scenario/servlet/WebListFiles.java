package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import net.douglashiura.leb.uid.scenario.data.Project;

@WebServlet("/files/*")
public class WebListFiles extends HttpServlet {

	private static final long serialVersionUID = 1L;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURL().toString().split("files")[1];
		if (path.endsWith(".us")) {
			response.setContentType("text/plain; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			String scenario = Project.get(path).getScenario();
			response.getWriter().write(scenario.toString());
		} else {
			List<String> scenarios = Project.get(path).getScenariesAsNames();
			response.getWriter().write(scenarios.toString());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String path = request.getRequestURL().toString().split("files")[1];
		byte[] all = new byte[request.getContentLength()];
		IOUtils.readFully(request.getInputStream(), all);
		Project.get(path).write(all);
	}
}
