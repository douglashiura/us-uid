function InsertFunctions(li, data) {
	this.li = li;
	this.data = data;

	this.clickClone = function() {
		console.log(this.data);
	};
	this.clickRename = function() {
		console.log(this.data);
		$.ajax({
			url : "rename" + app.scenarioFile,
			method : "POST",
			data : JSON.stringify(json, null, 2),
			dataType : "json"
		}).bind(this);
	}
	return function(event) {
		var input = document.createElement("input");
		input.value = this.data.replace(".us","");
		input.setAttribute("size", "60");
		input.setAttribute("id", "inputFunctions");
		var rename = document.createElement("button");
		rename.innerHTML = "Rename";
		rename.setAttribute("id", "rename");
		rename.addEventListener('click', this.clickRename.bind(this));
		var clone = document.createElement("button");
		clone.innerHTML = "Clone"
		clone.setAttribute("id", "clone");
		clone.addEventListener('click', this.clickClone.bind(this));
		var section = document.createElement("section");
		section.setAttribute("style", "padding-bottom: 30px;");
		section.setAttribute("id", "sectionFunctions")
		section.append(input);
		section.append(rename);
		section.append(clone);
		this.li.append(section);
	}.bind(this);
};

$(window).load(
		function() {
			var scenarios = $("scenarios");

			var request = $.ajax({
				url : 'scenarios',
				method : "GET",
				data : '',
				dataType : "json"

			});

			this.removeFunctions = function(event) {
				event.originalTarget.removeChild(document
						.getElementById("sectionFunctions"));
			}

			request.done($.proxy(function(data) {
				var ul = document.createElement("ul")
				this.scenarios.append(ul);
				var i;
				for (i = 0; i < data.length; i++) {
					var a = document.createElement("a");
					a.setAttribute("href", "Editor.jsp?scenario=" + data[i]);
					a.innerHTML = data[i];
					var li = document.createElement("li");
					li.append(a);
					li.addEventListener('mouseenter', new InsertFunctions(li,
							data[i]));
					li.addEventListener('mouseleave', this.removeFunctions);
					ul.append(li);
				}
			}, this));

		});