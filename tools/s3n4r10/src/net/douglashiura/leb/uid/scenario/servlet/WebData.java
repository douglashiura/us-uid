package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.douglashiura.leb.uid.scenario.data.Element;
import net.douglashiura.leb.uid.scenario.data.Project;

@WebServlet("/data")
public class WebData extends HttpServlet {

	private static final long serialVersionUID = -2056786032626617198L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		List<Element> elements = Project.get().onScenarios().getElements();

		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		JsonArray data = new JsonArray();
		for (Element element : elements) {
			JsonObject object = new JsonObject();
			object.addProperty("fixture", element.getFixture());
			object.addProperty("usuid", element.getUsuid());
			object.addProperty("value", element.getValue());
			object.addProperty("type", element.getType().toString());
			data.add(object);
		}
		response.getWriter().append(data.toString());
	}

}