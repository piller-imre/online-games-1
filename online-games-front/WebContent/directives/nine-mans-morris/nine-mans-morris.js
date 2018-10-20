directives.directive('nineMansMorris', ['CanvasService', function(CanvasService){
	return {
		templateUrl : "directives/nine-mans-morris/nine-mans-morris.html",
        link: function(){
			var elem = document.getElementById("nineMansMorrisCanvas");
        	var ctx = CanvasService.getCanvas(elem);

        	var BOARD_SIZE = ctx.canvas.width * 3 / 5;
        	var SQUARE_SIZE = BOARD_SIZE / 9;
        		
        	function drawBoard(){	
        		
        		// board top left corner
        		var x = (ctx.canvas.width - BOARD_SIZE) / 2;
        		var y = (ctx.canvas.height - BOARD_SIZE) / 2;

        		// board border
        		ctx.strokeStyle = "#000000";
        		ctx.strokeRect(x, y, BOARD_SIZE, BOARD_SIZE);
        		
        		for(var i=7; i>=3; i -= 2){
        			x += SQUARE_SIZE;
            		y += SQUARE_SIZE;
            		ctx.strokeRect(x, y, SQUARE_SIZE*i, SQUARE_SIZE*i);
        		}   			
        	};
        	drawBoard();
        	
        	
        }
    };
}]);


//document.addEventListener('DOMContentLoaded', onDomLoaded, false);
//function onDomLoaded(){
//    var c = document.getElementById("chessCanvas");
//	var ctx = c.getContext("2d");
//	ctx.moveTo(0,0);
//	ctx.lineTo(200,200);
//	ctx.stroke();