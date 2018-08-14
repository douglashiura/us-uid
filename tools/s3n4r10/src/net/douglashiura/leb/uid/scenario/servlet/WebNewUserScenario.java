package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.douglashiura.leb.uid.scenario.data.Folder;

@WebServlet("/newUserScenario")
public class WebNewUserScenario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NAME = "scenario";
	private static final String FOLDER = "folder";
	public static String EDITOR_JSP = "Editor.jsp?scenario=files/%s/%s.us";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String folder = req.getParameter(FOLDER).toString();
		String name = req.getParameter(NAME).toString();
		String[] directories = folder.split("\\.");
		Folder.getDefault().make(directories).newScenario(name);
		resp.sendRedirect(String.format(EDITOR_JSP, folder.replace('.', '/'), name));
	}

}