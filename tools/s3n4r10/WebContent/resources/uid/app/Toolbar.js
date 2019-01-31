example.Toolbar = Class
		.extend({
			init : function(elementId, view) {
				this.html = $("#" + elementId);
				this.view = view;
				var delimiter = "<span class='toolbar_delimiter'>&nbsp;</span>";
				view.getCommandStack().addEventListener(this);
				view.on("select", $.proxy(this.onSelectionChanged, this));

				this.html.append($(delimiter));

				this.newSystemOutput = $("<button class='gray'>New system output</button>");
				this.html.append(this.newSystemOutput);
				this.newSystemOutput.click($.proxy(function() {
					this.view.getCurrentSelection().newSystemOutput();
				}, this));

				this.html.append($(delimiter));

				this.newUserInput = $("<button class='gray'>New user input</button>");
				this.html.append(this.newUserInput);
				this.newUserInput.click($.proxy(function() {
					this.view.getCurrentSelection().newUserInput();
				}, this));

				this.html.append($(delimiter));

				this.newInteractionState = $("<button class='gray'>New regressive state</button>");
				this.html.append(this.newInteractionState);
				this.newInteractionState.click($.proxy(function() {
					this.view.getCurrentSelection().newInteractionState();
				}, this));

				this.html.append($(delimiter));

				this.newInteractionState = $("<button class='gray'>New progressive state</button>");
				this.html.append(this.newInteractionState);
				this.newInteractionState.click($.proxy(function() {
					this.view.getCurrentSelection()
							.newInteractionStateProgressive();
				}, this));

				this.html.append($(delimiter));
				this.html.append($(delimiter));
				this.html.append($(delimiter));
				this.html.append($(delimiter));
				this.html.append($(delimiter));
				this.html.append($(delimiter));

				this.saveButton = $("<button class='gray'>Save</button>");
				this.html.append(this.saveButton);
				this.saveButton.click($.proxy(function() {
					var io = new br.ufsc.leb.uid.scenario.io.Store(this.view);
					io.send();
					io.getUniformity();
				}, this));

				this.html.append($(delimiter));

				this.html.append($(delimiter));
				this.resetButton = $("<button class='gray'>Reset</button>");
				this.html.append(this.resetButton);
				this.resetButton.click($.proxy(function() {
					var figures = this.view.getLines().asArray();
					for (var i = 0; i < figures.length; i++)
						figures[i].resetColor();
					figures = this.view.getFigures().asArray();
					for (var i = 0; i < figures.length; i++)
						figures[i].resetColor();
				}, this));
				this.html.append($(delimiter));
				this.html.append($(delimiter));
				this.uniformity = $("<span style='color: white;'>Unifomity:<span id='uniformity'>0</span></span>");
				this.html.append(this.uniformity);
				var io = new br.ufsc.leb.uid.scenario.io.Store(this.view);
				io.getUniformity();

			},

			onSelectionChanged : function(emitter, figure) {

			},

			stackChanged : function(event) {

			}
		});
