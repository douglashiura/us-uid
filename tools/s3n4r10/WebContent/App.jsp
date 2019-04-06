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
<body style="width: 100%; padding: 0px; margin: 0px;">

	<div id="top"
		style="color: white; text-align: left; background-color: #3f51b5; padding: 10px;">
		<a id="tool" style="color: white;"
			href="http://${header['host']}${pageContext.request.contextPath}/">Sc3n4r10</a>
		<div style="float: right;">
			<span><a style="color: white;" href="UserGuide.html"
				id="userGuide">User Guide</a></span> <span style="padding-left: 20px;"><a
				style="color: white;"
				href="http://${header['host']}${pageContext.request.contextPath}/Projects.jsp?user=${param.user}"
				id="user">/${param.user}</a></span> <span><a style="color: white;"
				href="http://${header['host']}${pageContext.request.contextPath}/App.jsp?user=${param.user}&project=${param.project}"
				id="project">/${param.project}</a></span> <span style="padding-left: 20px;"><a
				style="color: white;"
				href="http://${header['host']}${pageContext.request.contextPath}/"
				id="logout">Logout</a></span>
		</div>
	</div>
	<div id="content" style="margin: 10px;">
		<form action="scenarios?user=${param.user}&project=${param.project}"
			method="post">
			<h2>
				<span id="new_scenario_title">New User Scenario</span> 
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
	</div>
</body>
</html>
