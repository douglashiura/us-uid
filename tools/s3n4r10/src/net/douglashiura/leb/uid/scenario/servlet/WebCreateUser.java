package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.douglashiura.leb.uid.scenario.data.User;
import net.douglashiura.leb.uid.scenario.data.UserDuplicationException;
import net.douglashiura.leb.uid.scenario.data.primitive.Email;
import net.douglashiura.leb.uid.scenario.data.primitive.EmailBiggerThat120Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.EmailEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.EmailInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.EmailNullException;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleName;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleNameBiggerThat30Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleNameEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleNameInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UserNameNullException;
import net.douglashiura.leb.uid.scenario.servlet.scenario.OnContext;

@WebServlet("/newAccount/*")
public class WebCreateUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String USER = "user";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private String EDITOR_JSP = "Projects.jsp?user=%s";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain; charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		String email = request.getParameter(EMAIL);
		String username = request.getParameter(USER);
		String password = request.getParameter(PASSWORD);

		try {
			SimpleName username2 = new SimpleName(username);
			new OnContext(request).onProjectScenario().createUser(new User(new Email(email), username2, password));
			resp.sendRedirect(String.format(EDITOR_JSP, username2.getName()));
		} catch (UserDuplicationException | EmailEmptyException | EmailNullException | EmailBiggerThat120Exception
				| EmailInvalidException | UserNameNullException | SimpleNameEmptyException
				| SimpleNameBiggerThat30Exception | SimpleNameInvalidException error) {
			throw new ServletException(error);
		}
	}
}