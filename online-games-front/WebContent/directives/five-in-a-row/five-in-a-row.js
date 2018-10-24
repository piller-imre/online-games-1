directives.directive('fiveInARow', ['CanvasService', '$timeout', function(CanvasService, $timeout){
	return {
		templateUrl : "directives/five-in-a-row/five-in-a-row.html",
        link: function(){
			var elem = document.getElementById("fiveInARowCanvas");	// get canvas element and context
        	var ctx = CanvasService.getCanvas(elem);

			var BOARD_SIZE = ctx.canvas.width * 3 / 5;		// contstants
			var SQUARE_NUM_PER_SIDE = 25;
			var SQUARE_SIZE = BOARD_SIZE / SQUARE_NUM_PER_SIDE;
			
			var board = {
				left: (ctx.canvas.width - BOARD_SIZE) / 2,
				top: (ctx.canvas.height - BOARD_SIZE) / 2,
				width: BOARD_SIZE,
				height: BOARD_SIZE,
				rows: SQUARE_NUM_PER_SIDE,
				cols: SQUARE_NUM_PER_SIDE,
				squareSize: SQUARE_SIZE,
				lineWidth: 1,
				lineColor: "#66bcff",
				borderColor: "#000000",
				backgroundColor: "#f9f9f9"
			}

			// TODO create GameManager to pass real data! initInfo is dummy now.
			// TODO make sure this gets refreshed regularly
			// TODO check winning status every time
			var initInfo = {
				matchId : 33,		// match id
				player1 : 11,		// user id
				player2 : 12,		// user id
				activePlayer : 1,	// player 1
				fields : null,		// board state, first status null
				rules : [1]
			}
			
			var match = {
				matchId : initInfo.matchId,
				player1 : initInfo.player1,
				player2 : initInfo.player2,
				activePlayer : initInfo.activePlayer,
				fields : initInfo.fields ? initInfo.fields : initFields(board, 0),
				rules : initInfo.rules
			};
        	        	
			drawBoard(board, ctx);
			if (angular.isDefined(match.rules) && match.rules != null){
				if (match.rules.indexOf(1) != -1){
					initTraps(match.fields);
				}
			}
			
			// Add event listener for 'click' events.
			elem.addEventListener('click', function(event) {
				var x = event.pageX - elem.offsetLeft;	// get canvas x,	elem.offsetLeft - canvas origo x
				var y = event.pageY - elem.offsetTop;	// get canvas y		elem.offsetTop - canvas origo y
				console.log(x, y);

				if (isClickInBoard(board, x, y)){									// if click happened on board
					var field = getClickedField(board, x, y);						// get clicked field by index
					field.value = match.fields[field.x][field.y].value;				// get value of field
					console.log(field.x + "," + field.y + ' - ' + field.value);
					if(field.value == 0){											// if field is empty
						match.fields[field.x][field.y].value = match.activePlayer;					// record value
						drawCharacter(match.fields[field.x][field.y], board, match.activePlayer, ctx);		// draw character
						$timeout(function(){
							if (checkWin(field, match.activePlayer, match.fields)){						// check win
								alert("Player " + match.activePlayer + "wins!");
								match.fields = initFields(board, 0);
								match.activePlayer = 1;
								drawBoard(board, ctx);
							} else {
								match.activePlayer = match.activePlayer == 1 ? 2 : 1;	// switch player								
							}
						}, 200);
					}
					else if (field.value == 4) {
						activateTrap(match.fields[field.x][field.y], board, ctx);
						match.activePlayer = match.activePlayer == 1 ? 2 : 1;	// switch player
					}
				}
			}, false);
			

			//-----------------------------------------------------------
			// FUNCTION DECLARATIONS	- TODO remove unnecessary parapeters if not move functions to factory

        	function drawBoard(board, ctx){
				var x = board.left;
				var y = board.top;
				
				ctx.fillStyle = board.backgroundColor;			// draw base
				ctx.fillRect(x, y, board.width, board.height);
				ctx.strokeStyle = board.borderColor;
				ctx.strokeRect(x, y, board.width, board.height);

        		ctx.lineWidth = board.lineWidth;				// draw squares
        		ctx.strokeStyle = board.lineColor;
        		for(var i = 0; i < SQUARE_NUM_PER_SIDE; i++){	
        			x = board.left;
        			for(var j = 0; j < SQUARE_NUM_PER_SIDE; j++){
        				ctx.strokeRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
        				x += SQUARE_SIZE;
        			}
        			y += SQUARE_SIZE;
        		};
			};
			
			function initFields( board, defaultValue){
				var fields = [];
				var tempX = board.left;	
				var tempY = board.top;	

				for(var i=0; i < board.rows; i++){		
					fields.push([]);							// create rows
					tempY = board.top;
					for(var j=0; j < board.cols; j++){
						fields[i].push([]);						// create cols
						fields[i][j] = {						// initialize
							x : tempX,
							y : tempY,
							value : defaultValue
						}
						tempY += board.squareSize;
					}
					tempX += board.squareSize;
				}
				return fields;
			}

			function isClickInBoard(board, x, y){
				return	y > board.top && 
						y < board.top + board.height && 
						x > board.left && 
						x < board.left + board.width
						? true
						: false;
			}

			function getClickedField(board, x, y){				
				return {
					x : Math.floor( (x - board.left) / board.squareSize),
					y : Math.floor( (y - board.top) / board.squareSize)
				}
			}

			function drawCharacter(field, board, player, ctx){
				var char = player == 1 ? 'X' : 'O';
				var x = field.x;						// coord
				var y = field.y + board.squareSize;		// coord
				ctx.fillStyle = player == 1 ? "blue" : "red";
				ctx.font="30px Comic Sans MS";
				ctx.fillText(char, x, y);
			}

			function checkWin(field, player, fields){	// TODO if parameters get deleted, change fields to match.fields!!!
				var startX = field.x - 4;			// init min and max indexes, so we check fields in board
				var startY = field.y - 4;
				var endX = field.x + 4;
				var endY = field.y + 4;
				
				// diagonals first, there is a bigger chance to win this way, save some energy
				// diagonal from top-left
				var lengthSoFar = 0;
				for(var i = startX, j = startY; i<=endX && j<=endY; i++){
					if (i >= 0 && i < fields.length && j >= 0 && j < fields[0].length){
						if (fields[i][j].value == player){
							lengthSoFar++;
						} else if (lengthSoFar < 5){
							lengthSoFar = 0;
						}
					}
					j++;
				}
				if (lengthSoFar >= 5){ return true; }

				// diagolal from bottom-left
				lengthSoFar = 0;
				for(var i = startX, j = endY; i<=endX && j>=startY; i++){
					if (i >= 0 && i < fields.length && j >= 0 && j < fields[0].length){
						if (fields[i][j].value == player){
							lengthSoFar++;
						} else if (lengthSoFar < 5){
							lengthSoFar = 0;
						}
					}
					j--;
				}
				if (lengthSoFar >= 5){ return true; }

				// vertical
				lengthSoFar = 0;
				for(var j=startY; j<=endY ; j++){
					if (j >= 0 && j < fields[0].length){
						if (fields[field.x][j].value == player){
							lengthSoFar++;
						} else if (lengthSoFar < 5){
							lengthSoFar = 0;
						}
					}
				}
				if (lengthSoFar >= 5){ return true; }

				// horizontal
				var lengthSoFar = 0;
				for(var i=startX; i<=endX ; i++){
					if (i >= 0 && i < fields.length){
						if (fields[i][field.y].value == player){
							lengthSoFar++;
						} else if (lengthSoFar < 5){
							lengthSoFar = 0;
						}
					}
				}
				if (lengthSoFar >= 5){ return true; }

				return false;
			}

			function initTraps(fields){
				var numOfTraps = 50;
				var x = 0;
				var y = 0;
				var i = 0;
				while (i<numOfTraps){
					var x = Math.floor(Math.random() * fields.length);
					var y = Math.floor(Math.random() * fields[0].length);
					if(fields[x][y].value != 4){
						fields[x][y].value = 4;
						i++;
						console.log(x + "," + y + " - " + fields[x][y].value);
					}
				}
			}

			function activateTrap(field, board, ctx){
				// change field value
				field.value = 3;

				// change field color to red
				ctx.fillStyle = "#FF0000";
				ctx.strokeStyle = board.lineColor;
				ctx.fillRect(field.x, field.y, board.squareSize, board.squareSize);
				ctx.strokeRect(field.x, field.y, board.squareSize, board.squareSize);
				
				// change field color to board color
				$timeout(function(){
					ctx.fillStyle = board.backgroundColor;
					ctx.fillRect(field.x, field.y, board.squareSize, board.squareSize);
					ctx.strokeRect(field.x, field.y, board.squareSize, board.squareSize);
					field.value = 0;
				}, 3000);
			}
        }
    }
}]);

/* NOTES

field indexes:
	fields[row][col] = {
		x (coord),
		y (coord),
		value
	}

field values:
	0 - empty	-- null
	1 - o		-- player 1
	2 - x		-- player 2
	3 - forbidden -- put elsewhere, 3xx http status codes
	4 - trap	-- client error, client die

rule values:
	0 - init walls
	1 - init bombs
	2 - custom bombs
*/