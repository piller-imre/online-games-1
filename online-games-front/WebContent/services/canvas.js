app.service('CanvasService', [
	function(){
		var vm = this;
		
		vm.getCanvas = function(canvas){
        	canvas.height = 700;
        	canvas.width = 1000;
        	
        	var ctx = canvas.getContext("2d");
        	ctx.fillStyle = '#FEEB50';
        	ctx.fillRect(0, 0, canvas.width, canvas.height);
        	ctx.fillStyle = '#FFFFFF';
        	return ctx;
		}
}]);