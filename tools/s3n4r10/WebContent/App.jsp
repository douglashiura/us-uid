
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
					<td><label>Package</label></td>
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
	<h2>Project</h2>
	<%=FolderToHtml.toHtml()%>
</body>
</html>
