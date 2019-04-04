function InsertFunctions(li, data) {
	this.li = li;
	this.data = data;
	this.file = data;
}
InsertFunctions.prototype.clickRename = function() {
	return function() {
		new br.ufsc.leb.uid.scenario.io.Store().renameProject(this.file,
				this.input.value);
	}.bind(this);
}

InsertFunctions.prototype.clickDelete = function() {
	return function() {
		new br.ufsc.leb.uid.scenario.io.Store().deleteProject(this.file);
	}.bind(this);
}

InsertFunctions.prototype.eventFunction = function() {
	return function(event) {
		this.input = document.createElement("input");
		this.input.value = this.data;
		this.input.setAttribute("size", "60");
		this.input.setAttribute("id", "inputFunctions");
		this.input.setAttribute("placeholder", "ProjectName");
		var rename = document.createElement("button");
		rename.innerHTML = "Rename";
		rename.addEventListener('click', this.clickRename());

		var deleteButton = document.createElement("button");
		deleteButton.innerHTML = "Delete"
		deleteButton.addEventListener('click', this.clickDelete());

		this.section = document.createElement("section");
		this.section.setAttribute("style", "padding-bottom: 30px;");
		this.section.setAttribute("id", "sectionFunctions")
		this.section.append(this.input);
		this.section.append(rename);
		this.section.append(deleteButton);
		this.li.append(this.section);
	}.bind(this);
};

function RemoveFunctions(insert) {
	this.insert = insert;
	return function(event) {
		this.insert.data = this.insert.input.value;
		event.originalTarget.removeChild(this.insert.section);
	}.bind(this);
}

function Load() {
	return function() {
		var projects = document.getElementById("projects");
		while (projects.firstChild) {
			projects.removeChild(projects.firstChild);
		}
		var request = $.ajax({
			url : 'projects/?user=' + user,
			method : "GET",
			data : '',
			dataType : "json"

		});

		request.done($.proxy(function(data) {
			console.log(data);
			var ul = document.createElement("ul")
			this.projects.append(ul);
			var i;
			for (i = 0; i < data.length; i++) {
				var a = document.createElement("a");
				a.setAttribute("href", "App.jsp?user=" + user + "&project="
						+ data[i]);
				a.innerHTML = data[i];
				var li = document.createElement("li");
				li.append(a);
				var insert = new InsertFunctions(li, data[i]);
				li.addEventListener('mouseenter', insert.eventFunction());
				li.addEventListener('mouseleave', new RemoveFunctions(insert));
				ul.append(li);
			}
			if (data.length < 1) {
				var span = document.createElement("span");
				span.setAttribute("id", "empty_projects");
				span.innerHTML = "There is no project";
				this.projects.append(span);
			}
		}, this));
	}
}

$(window).load(new Load());
