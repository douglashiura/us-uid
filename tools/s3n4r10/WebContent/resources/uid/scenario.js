var br = br ? br : {};
br.ufsc = br.ufsc ? br.ufsc : {};
br.ufsc.leb = br.ufsc.leb ? br.ufsc.leb : {};
br.ufsc.leb.uid = br.ufsc.leb.uid ? br.ufsc.leb.uid : {};
br.ufsc.leb.uid.scenario = br.ufsc.leb.uid.scenario ? br.ufsc.leb.uid.scenario
		: {};

br.ufsc.leb.uid.scenario.app = br.ufsc.leb.uid.scenario.app ? br.ufsc.leb.uid.scenario.app
		: {};
// إحداثيات: 21°25′21.02″N 39°49′34.58″E

br.ufsc.leb.uid.scenario.Util = function Util() {
}
br.ufsc.leb.uid.scenario.Util.filter = function(figures, type) {
	var extract = [];
	for (var int = 0; int < figures.length; int++) {
		var figure = figures[int];
		if (figure instanceof type)
			extract[extract.length] = figure;
	}
	return extract;
}
br.ufsc.leb.uid.scenario.FunctionalData = function FunctionalData(args) {
	this.result = "";
	this.model = "";
}
br.ufsc.leb.uid.scenario.app.Canvas = draw2d.Canvas.extend({
	init : function(id) {
		this._super(id, 2000, 10000);
		this.setScrollArea("#" + id);
	},
	getInteractions : function() {
		var figures = this.getFigures().asArray();
		var type = br.ufsc.leb.uid.scenario.Interacao;
		return br.ufsc.leb.uid.scenario.Util.filter(figures, type);
	}
});

br.ufsc.leb.uid.scenario.SystemOutput = draw2d.shape.basic.Text.extend({
	NAME : 'br.ufsc.leb.uid.scenario.SystemOutput',
	init : function(attr) {
		this._super(attr);
		this.installEditor(new draw2d.ui.LabelInplaceEditor());
		this.setUserData(new br.ufsc.leb.uid.scenario.FunctionalData());
		this.resetColor();
	},
	resetColor : function() {
		this.setColor(new draw2d.util.Color("#ffffff"));
		this.setFontColor(new draw2d.util.Color("#000000"));
	},
	evaluate : function(evaluator, result) {
		evaluator.evaluateSystemOutput(this, result);
	}
});

br.ufsc.leb.uid.scenario.UserInput = draw2d.shape.basic.Label.extend({
	NAME : 'br.ufsc.leb.uid.scenario.UserInput',
	init : function() {
		this._super();
		this.installEditor(new draw2d.ui.LabelInplaceEditor());
		this.setUserData(new br.ufsc.leb.uid.scenario.FunctionalData());
	},
	resetColor : function() {
		this.setColor(new draw2d.util.Color("#000000"));
	},
	evaluate : function(evaluator, result) {
		evaluator.evaluateUserInput(this, result);
	}
});

br.ufsc.leb.uid.scenario.Interacao = draw2d.shape.composite.Jailhouse.extend({
	NAME : "br.ufsc.leb.uid.scenario.Interacao",
	init : function() {
		this._super(170, 130);
		this.setRadius(500);
		this.setUserData(new br.ufsc.leb.uid.scenario.FunctionalData());
	},
	resetColor : function() {
		this.setColor(new draw2d.util.Color("#000000"));
		this.setBackgroundColor(new draw2d.util.Color("#ffffff"));
	},
	getSystemOutputs : function() {
		var type = br.ufsc.leb.uid.scenario.SystemOutput;
		var figures = this.getAssignedFigures().asArray();
		return br.ufsc.leb.uid.scenario.Util.filter(figures, type);
	},
	getUserInputs : function() {
		var type = br.ufsc.leb.uid.scenario.UserInput;
		var figures = this.getAssignedFigures().asArray();
		return br.ufsc.leb.uid.scenario.Util.filter(figures, type);
	},
	assignFigure : function(component) {
		if (!(component instanceof br.ufsc.leb.uid.scenario.Interacao))
			this._super(component);
	},
	addPrint : function(inputOrOutput) {
		inputOrOutput.setText('Text');
		inputOrOutput.setDimension(43, 10);
		this.getCanvas().addFigure(inputOrOutput, this.getX() + 35,
				this.getY() + 35);
		this.assignFigure(inputOrOutput);
	},
	newSystemOutput : function() {
		this.addPrint(new br.ufsc.leb.uid.scenario.SystemOutput());
	},
	newUserInput : function() {
		this.addPrint(new br.ufsc.leb.uid.scenario.UserInput());
	},
	getTransaction : function() {
		if (this.getInputPorts().isEmpty())
			return null;
		return this.getInputPort(0).getConnections().get(0);
	},
	newInteractionState : function() {
		var canvas = this.getCanvas();
		var source = new br.ufsc.leb.uid.scenario.Interacao();
		canvas.addFigure(source, this.getX(), this.getY() + this.getHeight()
				+ 50);
		var ligacao = new br.ufsc.leb.uid.scenario.Transaction();
		ligacao.installConnection(source, this);
		canvas.addFigure(ligacao);
	},

	newInteractionStateProgressive : function() {
		var canvas = this.getCanvas();
		var source = new br.ufsc.leb.uid.scenario.Interacao();
		canvas.addFigure(source, this.getX(), this.getY() - 100);
		var ligacao = new br.ufsc.leb.uid.scenario.Transaction();
		ligacao.installConnection(this, source);
		canvas.addFigure(ligacao);
	},

	evaluate : function(evaluator, result) {
		evaluator.evaluateInteraction(this, result);
	}
});

br.ufsc.leb.uid.scenario.Transaction = draw2d.Connection
		.extend({
			NAME : 'br.ufsc.leb.uid.scenario.Transaction',
			init : function() {
				this._super();
				this.setRouter(new draw2d.layout.connection.DirectRouter());
				this
						.setTargetDecorator(new draw2d.decoration.connection.ArrowDecorator());
				this.setUserData(new br.ufsc.leb.uid.scenario.FunctionalData());
			},
			resetColor : function() {
				this.setColor(new draw2d.util.Color("#000000"));
			},
			installConnection : function(source, target) {
				target.createPort("input",
						new br.ufsc.leb.uid.scenario.TargetLocator());
				source.createPort("output",
						new br.ufsc.leb.uid.scenario.ResourceLocator());
				this.setSource(source.getOutputPort(0));
				this.setTarget(target.getInputPort(0));
			},
			evaluate : function(evaluator, result) {
				evaluator.evaluateTransaction(this, result);
			},
			getFrom : function() {
				return this.getSource().getParent();
			}
		});

br.ufsc.leb.uid.scenario.TargetLocator = draw2d.layout.locator.InputPortLocator
		.extend({
			NAME : 'br.ufsc.leb.uid.scenario.TargetLocator',
			init : function() {
				this._super();
			},
			relocate : function(index, figure) {
				this.applyConsiderRotation(figure, figure.getParent()
						.getWidth() / 2, figure.getParent().getHeight());
			}
		});

br.ufsc.leb.uid.scenario.ResourceLocator = draw2d.layout.locator.OutputPortLocator
		.extend({
			NAME : 'br.ufsc.leb.uid.scenario.ResourceLocator',
			init : function() {
				this._super();
			},
			relocate : function(index, figure) {
				var p = figure.getParent();
				this.applyConsiderRotation(figure, figure.getParent()
						.getWidth() / 2, 0);
			}
		});

br.ufsc.leb.uid.scenario.Anotacao = function Anotacao(comentario, data) {
	this.comentario = comentario;
	this.data = data;
}
