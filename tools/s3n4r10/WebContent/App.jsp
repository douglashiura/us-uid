<html xmlns:v="urn:schemas-microsoft-com:vml"
	xmlns:o="urn:schemas-microsoft-com:office:office">
<head>
<title>User scenario</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="resources/uid/canvas2d/jquery-1.10.2.min.js"></script>
<script src="resources/app.js"></script>
<script src="resources/uid/io.js"></script>
<script type="text/javascript">
	var user = "${param.user}";
	var project = "${param.project}"
</script>
</head>
<body>
	<form action="scenarios?user=${param.user}&project=${param.project}" method="post">
		<h2>
			<span id="new_scenario_title">New User Scenario</span> <span><a id="userGuide" href="UserGuide.html">User
					Guide</a></span>
		</h2>
		<table style="padding-left: 50px;">
			<tbody>
				<tr>
					<td><label id="package">Package</label></td>
					<td><input id="folder" name="folder" size="50"
						placeholder="org.eclipse.plugin" /></td>
				</tr>
				<tr>
					<td><label id="name">Name </label></td>
					<td><input id="scenario" name="scenario" size="50"
						placeholder="UpperFirstCharacter" /></td>
				</tr>
				<tr>
					<td></td>
					<td><button id="new_scenario">New scenario</button></td>
				</tr>
			</tbody>
		</table>
	</form>
	<br />
	<h2 id="project_title">User scenarios</h2>
	<div id="scenarios"></div>
</body>
</html>
