package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.douglashiura.leb.uid.scenario.data.ProjectScenario;
import net.douglashiura.leb.uid.scenario.data.User;
import net.douglashiura.leb.uid.scenario.data.UserDuplicationException;
import net.douglashiura.leb.uid.scenario.data.primitive.Email;
import net.douglashiura.leb.uid.scenario.data.primitive.EmailBiggerThat120Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.EmailEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.EmailInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.EmailNullException;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleName;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameBiggerThat30Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameNullException;

/**
 * Servlet implementation class WebList
 */
@WebServlet("/newAccount/*")
public class WebCreateUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String USER = "user";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private String EDITOR_JSP = "User.jsp";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		String email = request.getParameter(EMAIL).toString();
		String username = request.getParameter(USER).toString();
		String password = request.getParameter(PASSWORD).toString();
		ProjectScenario scenario = (ProjectScenario) request.getServletContext()
				.getAttribute(ListenerConfigProjectScenario.SCENARIO_APP);
		try {
			scenario.createUser(new User(new Email(email), new SimpleName(username), password));
			resp.addHeader(USER, username);
			resp.sendRedirect(EDITOR_JSP);
		} catch (UserDuplicationException | EmailEmptyException | EmailNullException | EmailBiggerThat120Exception
				| EmailInvalidException | UsernameNullException | UsernameEmptyException | UsernameBiggerThat30Exception
				| UsernameInvalidException error) {
			throw new ServletException(error);
		}
	}
}