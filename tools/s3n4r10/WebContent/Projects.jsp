<html xmlns:v="urn:schemas-microsoft-com:vml"
	xmlns:o="urn:schemas-microsoft-com:office:office">
<head>
<title>User scenario</title>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="resources/uid/canvas2d/jquery-1.10.2.min.js"></script>
<script src="resources/projects.js"></script>
<script type="text/javascript">
	var user = "${param.user}";
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
				id="user">/${param.user}</a></span> <span style="padding-left: 20px;"><a
				style="color: white;"
				href="http://${header['host']}${pageContext.request.contextPath}/"
				id="logout">Logout</a></span>
		</div>
	</div>
	<div id="content" style="margin: 10px;">
		<form action="newProject?user=${param.user}" method="POST">
			<h2>
				<span id="project_title">New project</span>
			</h2>
			<div>
				<table style="text-align: center;">
					<tbody>
						<tr>
							<td><span id="name_label">Name</span></td>
							<td><input id="project_name" name="name" size="30"
								placeholder="Name only with letters and numbers" /></td>
						</tr>
						<tr>
							<td><button id="project_create">Create</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<h2>
			<span id="projects_title">Projects</span>
		</h2>
		<div id="projects"></div>
	</div>
</body>
</html>
