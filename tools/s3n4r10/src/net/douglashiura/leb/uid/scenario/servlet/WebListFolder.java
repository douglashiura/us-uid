package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.douglashiura.leb.uid.scenario.data.Project;

/**
 * Servlet implementation class WebList
 */
@WebServlet("/folders/*")
public class WebListFolder extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURL().toString().split("folders")[1];
		List<String> folders = Project.get(path).getFolders();
		response.getWriter().write(folders.toString());
	}
}
