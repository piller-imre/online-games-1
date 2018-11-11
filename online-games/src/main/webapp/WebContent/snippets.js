
// DRAWING A CHESS BOARD
	var CANVAS_SIZE = 600;
	var SQUARE_SIZE = CANVAS_SIZE / 8;
	
	var canvas = document.getElementById("chessCanvas");
	var ctx = canvas.getContext("2d");
	ctx.canvas.width = CANVAS_SIZE;
	ctx.canvas.height = CANVAS_SIZE;
		
	function drawBoard(){
		var x = 0;
		var y = 0;
		
		for(var i = 0; i < 8; i++){
			x = 0;
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





document.addEventListener('DOMContentLoaded', onDomLoaded, false);
function onDomLoaded(){
    var c = document.getElementById("chessCanvas");
	var ctx = c.getContext("2d");
	ctx.moveTo(0,0);
	ctx.lineTo(200,200);
	ctx.stroke();

	
}

var hello = function(){
	alert("hello");
}
	