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
<body>
	<form action="project" method="post">
		<h2>
			<span id="project_title">New project</span> <span><a
				href="UserGuide.html" id="userGuide">User Guide</a></span>
		</h2>
		<div>
			<table style="text-align: center;">
				<tbody>
					<tr>
						<td><input id="project_name" name="name" size="30"
							placeholder="Project name" /></td>
					</tr>
					<tr>
						<td><button id="project_create">Create</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	<h2>
		<span id="projets_title">Projects</span>
	</h2>
	<div id="projects"></div>
</body>
</html>
