package net.douglashiura.leb.uid.scenario.servlet;

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
import net.douglashiura.leb.uid.scenario.data.primitive.UserNameNullException;

@WebServlet("/authentication")
public class WebAuthentication extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String USER = "user";
	private String EDITOR_JSP = "Projects.jsp?user=%s";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		String username = request.getParameter(USER);
		try {
			SimpleName username2 = new SimpleName(username);
			resp.sendRedirect(String.format(EDITOR_JSP, username2.getName()));
		} catch (UserNameNullException | SimpleNameEmptyException | SimpleNameBiggerThat30Exception
				| SimpleNameInvalidException error) {
			throw new ServletException(error);
		}
	}
}