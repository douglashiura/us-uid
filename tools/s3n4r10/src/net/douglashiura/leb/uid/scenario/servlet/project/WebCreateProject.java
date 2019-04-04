package net.douglashiura.leb.uid.scenario.servlet.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.douglashiura.leb.uid.scenario.data.primitive.SimpleName;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleNameBiggerThat30Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleNameEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleNameInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UserInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UserNameNullException;
import net.douglashiura.leb.uid.scenario.servlet.scenario.OnContext;

@WebServlet("/newProject/*")
public class WebCreateProject extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String NAME = "name";
	private String EDITOR_JSP = "App.jsp?user=%s&project=%s";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		String name = request.getParameter(NAME);
		try {
			SimpleName project = new SimpleName(name);
			OnContext onContext = new OnContext(request);
			onContext.onUser().createProject(project);
			resp.sendRedirect(String.format(EDITOR_JSP, onContext.getUser(), project.getName()));
		} catch (UserNameNullException | SimpleNameEmptyException | SimpleNameBiggerThat30Exception
				| SimpleNameInvalidException | UserInvalidException error) {
			throw new ServletException(error);
		}
	}
}