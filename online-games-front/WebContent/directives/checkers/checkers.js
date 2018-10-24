directives.directive('chess', [function(){
	return {
		templateUrl : "directives/chess/chess.html",
        link: function(){
			
			var elem = document.getElementById("chessCanvas");
			var ctx = CanvasService.getCanvas(elem);
			
        	var BOARD_SIZE = 600;
        	var SQUARE_SIZE = BOARD_SIZE / 8;
        		
        	function drawBoard(){
        		var x = 100;
        		var y = 50;
        		
        		ctx.strokeRect(x, y, BOARD_SIZE, BOARD_SIZE);
        		
        		for(var i = 0; i < 8; i++){
        			x = 100;
        			for(var j = 0; j < 8; j++){
        				if(i % 2 == 0){
        					j % 2 == 0 ? ctx.fillStyle="#FFFFFF" : ctx.fillStyle="#000000";        					
        				} else {
        					j % 2 == 0 ? ctx.fillStyle="#000000" : ctx.fillStyle="#FFFFFF";      
        				}
        				ctx.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
        				x += SQUARE_SIZE;
        			}
        			y += SQUARE_SIZE;
        		};
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