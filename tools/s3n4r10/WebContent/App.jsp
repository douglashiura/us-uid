
<%@page import="net.douglashiura.leb.uid.scenario.html.BeanScenario"%>
<%@page import="net.douglashiura.leb.uid.scenario.data.Scenario"%>
<%@page import="java.util.List"%>
<%@page import="net.douglashiura.leb.uid.scenario.html.FolderToHtml"%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
	xmlns:o="urn:schemas-microsoft-com:office:office">
<head>
<title>User scenario</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>


<body>

	<form action="newUserScenario" method="post">
		<h2>New User Scenario</h2>
		<table style="padding-left: 50px;">
			<tbody>
				<tr>
					<td><label>Folder</label></td>
					<td><input name="folder" size="50" /></td>
					<td>i.e.: org.eclipse.plugin</td>
				</tr>
				<tr>
					<td><label>Name </label></td>
					<td><input name="scenario" size="50" /></td>
				</tr>
				<tr>
					<td></td>
					<td><button>New scenario</button></td>
				</tr>
			</tbody>
		</table>
	</form>
	<br />
	<form action="newVersion" method="post">
		<h2>New version</h2>
		<table style="padding-left: 50px;">
			<tbody>
				<tr>
					<td><label>Select scenario </label></td>
					<td><select name="selectScenario" style="width: 325px">

							<%
								List<Scenario> list = new BeanScenario().getScenarios();
								for (Scenario scenario : list) {
									out.println("<option value=\""+scenario.getVirtualName()+
											"\">"+scenario.getVirtualName()+"</option>");
								}
							%>

					</select></td>
				</tr>
				<tr>
					<td><label>Folder</label></td>
					<td><input name="folder" size="50" /></td>
					<td>i.e.: org.eclipse.plugin</td>
				</tr>
				<tr>
					<td><label>Name </label></td>
					<td><input name="scenario" size="50" /></td>
				</tr>
				<tr>
					<td></td>
					<td><button>New version</button></td>
				</tr>
			</tbody>
		</table>
	</form>
	<br />
	<h2>Project</h2>
	<%=FolderToHtml.toHtml()%>
</body>
</html>
