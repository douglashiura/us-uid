var FunctionalDataInputOutputView = Backbone.View.extend({
    initialize:function () {
        this.model.on('change:text', $.proxy(this.renderText,this));
    },
    events:{
        'keyup #property_model':       'updateModel',
        'keyup #property_text':       'updateText'
    },
    close: function(){
        this.model.off(this.renderText);
    },
    renderText: function() {
        this.$('#property_text').val(this.model.attr('text'));
     },
     updateText: function(newText) {
         this.model.attr("text",this.$('#property_text').val());
     },
    updateModel: function(newX) {
        this.model.getUserData().model=this.$('#property_model').val();
    },
   
   render:function () {
	   var html = _.template(
	            '<div id="property_position_container" class="panel panel-default">'+
	            ' <div class="panel-heading " >'+
	            '     Model'+
	            '</div>'+
	            ' <div class="panel-body" id="position_panel">'+
	            '   <div class="form-group">'+
	            '       <input id="property_model" type="text" class="form-control" value="<%= figure.getUserData().model %>"/>'+
	            '   </div>'+
	            ' </div>'+
	            '</div>'+
	            
	            '<div id="property_position_container" class="panel panel-default">'+
	            ' <div class="panel-heading " >'+
	            '     Value'+
	            '</div>'+
	            ' <div class="panel-body" id="userdata_panel">'+
	            '   <div class="form-group">'+
	            '       <div class="input-group" ></div> '+ 
	            '       <input id="property_text" type="text" class="form-control" value="<%= figure.attr("text") %>"/><br/>'+
	            '   </div>'+
	            ' </div>'+
	            '</div>', 
	            {figure:this.model});
        this.$el.html(html);
        return this;
     }
});

var FunctionalDataInteractionView = Backbone.View.extend({
    initialize:function () {
    },
    events:{
        'keyup #property_model':       'updateModel'
    },
    close: function(){
    },
    
    updateModel: function(newX) {
        this.model.getUserData().model=this.$('#property_model').val();
    },
   
   render:function () {
	   
        var html = _.template(
            '<div id="property_position_container" class="panel panel-default">'+
            ' <div class="panel-heading " >'+
            '     Model'+
            '</div>'+
            ' <div class="panel-body" id="position_panel">'+
            '   <div class="form-group">'+
            '       <input id="property_model" type="text" class="form-control" value="<%= figure.getUserData().model %>"/>'+
            '   </div>'+
            ' </div>'+
            '</div>', 
            {figure:this.model});
        this.$el.html(html);
        return this;
     }
});