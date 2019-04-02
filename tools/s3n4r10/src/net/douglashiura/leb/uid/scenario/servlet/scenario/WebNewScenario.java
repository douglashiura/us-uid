package net.douglashiura.leb.uid.scenario.servlet.scenario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import net.douglashiura.leb.uid.scenario.data.InvalidProjectExeception;
import net.douglashiura.leb.uid.scenario.data.OnProject;
import net.douglashiura.leb.uid.scenario.data.ProjectScenario;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleName;
import net.douglashiura.leb.uid.scenario.data.primitive.UserInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameBiggerThat30Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameNullException;
import net.douglashiura.leb.uid.scenario.servlet.ListenerConfigProjectScenario;
import net.douglashiura.leb.uid.scenario.servlet.util.FileName;
import net.douglashiura.leb.uid.scenario.servlet.util.NotAFileException;

@WebServlet("/newScenario/*")
public class WebNewScenario extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProjectScenario scenario = (ProjectScenario) request.getServletContext()
				.getAttribute(ListenerConfigProjectScenario.SCENARIO_APP);
		String user = request.getParameter("user");
		String project = request.getParameter("project");
		String packaged = request.getParameter("name");
		String name = request.getParameter("package");
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			OnProject onProject = scenario.onUser(new SimpleName(user)).onProject(new SimpleName(project));
			byte[] all = new byte[request.getContentLength()];
			IOUtils.readFully(request.getInputStream(), all);
			onProject.createNewScenario(new FileName(packaged, name)).write(all);
		} catch (NotAFileException | InvalidProjectExeception | UserInvalidException | UsernameNullException
				| UsernameEmptyException | UsernameBiggerThat30Exception | UsernameInvalidException e) {
			throw new ServletException(e);
		}
	}
}
