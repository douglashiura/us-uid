package net.douglashiura.leb.uid.scenario.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.douglashiura.leb.uid.scenario.data.Folder;
import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.data.Scenario;

@WebServlet("/updateUserScenario")
public class WebUpdateUserScenario extends HttpServlet {
	private static final String SELECT_SCENARIO = "selectScenario";
	private static final long serialVersionUID = 1L;
	private static final String NAME = "scenario";
	private static final String FOLDER = "folder";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String folder = req.getParameter(FOLDER).toString();
		String name = req.getParameter(NAME).toString();
		String scenarioSelecionado = req.getParameter(SELECT_SCENARIO).toString();
		String[] directories = folder.split("\\.");

		Scenario scenario = Project.get(scenarioSelecionado).getScenario();
		String conteudo = scenario.getDocument();
		
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual) + " " +
				new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		
		String elemento = "[\r\n" +
				"  {\r\n" + 
				"    \"type\": \"draw2d.shape.icon.Fork\",\r\n" + 
				"    \"id\": \"ee32380d-c05e-80e2-a837-6fb341c9220e\",\r\n" + 
				"    \"x\": 15,\r\n" + 
				"    \"y\": 15,\r\n" + 
				"    \"width\": 30,\r\n" + 
				"    \"height\": 30,\r\n" + 
				"    \"alpha\": 1,\r\n" + 
				"    \"userData\": { \r\n" +
				"      \"anotacoes\": [\r\n" +
				"        {\r\n" +
				"	       \"comentario\": \"US-UID de origem: " + scenario.getName() + "\",\r\n" +
				"	       \"data\": \"" + data + "\"\r\n" +
				"        }\r\n" +
				"      ]\r\n" +
				"    },\r\n" + 
				"    \"cssClass\": \"draw2d_shape_icon_Fork\",\r\n" + 
				"    \"ports\": [],\r\n" + 
				"    \"bgColor\": \"#000000\",\r\n" + 
				"    \"color\": \"#000000\",\r\n" + 
				"    \"stroke\": 0,\r\n" + 
				"    \"radius\": 0\r\n" +  
				"  },";
				
		conteudo = elemento + conteudo.substring(1);
		Folder.getDefault().make(directories).newScenario(name, conteudo);

		resp.sendRedirect(String.format(WebNewUserScenario.EDITOR_JSP, folder.replace('.', '/'), name));
	}
}
