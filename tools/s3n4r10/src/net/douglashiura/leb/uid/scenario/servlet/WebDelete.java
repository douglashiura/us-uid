package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.servlet.util.Command;
import net.douglashiura.leb.uid.scenario.servlet.util.NotAFileException;
import net.douglashiura.leb.uid.scenario.servlet.util.FileUtil;

@WebServlet("/delete")
public class WebDelete extends HttpServlet {

	public  static final String APP_HTML = "App.html";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("USE POST WITH JSON PARAMETER".toString());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new GsonBuilder().create();
		Command command = gson.fromJson(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8),
				Command.class);
		FileUtil file;
		try {
			file = new FileUtil(command.getActualFile());
			Project projectScenario = Project.get(file.getDirectory()).enter(file.getName());
			projectScenario.delete();
			response.setContentType("text/plain");
			response.sendRedirect(APP_HTML);
		} catch (NotAFileException e) {
			throw new ServletException(e);
		}
	}

}