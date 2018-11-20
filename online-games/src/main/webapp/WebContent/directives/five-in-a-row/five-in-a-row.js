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


/*

{
  "id": 3,
  "players": {
    "player1": {
      "id": 6,
      "username": "elõd",
      "newMatch": []
    },
    "player2": {
      "id": 1,
      "username": "test",
      "newMatch": []
    },
    "activePlayer": 1
  },
  "gameType": {
    "gameTypeId": 2,
    "gameTypeName": "Dáma",
    "options": [
      {
        "id": 5,
        "name": "Szabály1",
        "description": "Leírás1"
      },
      {
        "id": 7,
        "name": "Szabály3",
        "description": "Leírás3"
      },
      {
        "id": 6,
        "name": "Szabály2",
        "description": "Leírás2"
      }
    ]
  },
  "turn": 1,
  "boardstate": "",
  "options": "[6,7]"
}

*/

directives.directive('fiveInARow', [
	'CanvasService', 
	'$timeout', 
	'FiveInARowManager', 
	'$rootScope',
	'$http',
	function(
		CanvasService, 
		$timeout,
		FiveInARowManager,
		$rootScope,
		$http){
	return {
		templateUrl : "directives/five-in-a-row/five-in-a-row.html",
		scope: {
			initMatch: '='
		},
        link: function(scope){
			var man = FiveInARowManager;
			var vm = scope;
			var baseUrl = $rootScope.baseUrl;
						
			var elem = document.getElementById("fiveInARowCanvas");	// get canvas element and context
        	var ctx = CanvasService.getCanvas(elem);

			var BOARD_SIZE = ctx.canvas.width * 3 / 5;		// contstants
			var SQUARE_NUM_PER_SIDE = 25;
			var SQUARE_SIZE = BOARD_SIZE / SQUARE_NUM_PER_SIDE;
			
			vm.board = {
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

			vm.initMatch.fields = vm.initMatch.boardstate == "" ? null : JSON.parse(vm.initMatch.boardstate);
			
			var match = {
				matchId : vm.initMatch.id,
				player1 : vm.initMatch.players.player1,
				player2 : vm.initMatch.players.player2,
				activePlayer : vm.initMatch.players.activePlayer,
				turn: vm.initMatch.turn,
				fields : vm.initMatch.fields ? vm.initMatch.fields : man.initFields(vm.board, 0),
				options : vm.initMatch.options
			};

			// if (match.activePlayer == 1) {
			// 	vm.disable = $localStorage.currentUser.userid == match.player1.id ? false : true;
			// } else {
			// 	vm.disable = $localStorage.currentUser.userid == match.player2.id ? false : true;
			// }
        	        	
			man.drawBoard(vm.board, ctx);
			man.initOptions(match, vm.board, ctx);
			
			// Add event listener for 'click' events.
			elem.addEventListener('click', function(event) {
				var x = event.pageX - elem.offsetLeft;	// get canvas x,	elem.offsetLeft - canvas origo x
				var y = event.pageY - elem.offsetTop;	// get canvas y		elem.offsetTop - canvas origo y
				console.log(x, y);

				// if (man.isClickInBoard(vm.board, x, y)){									// if click happened on board
				var field = man.getClickedField(vm.board, x, y);						// get clicked field by index

				if(field != null){
					field.value = match.fields[field.x][field.y].value;						// get value of field
					console.log(field.x + "," + field.y + ' - ' + field.value);

					if([0,4].includes(field.value)){
						match.action = field;
						// match.options = JSON.stringify(match.options);
						// match.boardstate = '{"fields": ' + JSON.stringify(match.fields) +'}';
						// match.boardstate = JSON.stringify(match.fields);
						console.log("send nudes");
						console.log(match.action);

						$http.post(baseUrl + '/fiveinarow/action', match)
						.then(function(result){
							console.log(result.data);
						});
					}
					
					/*
					if(field.value == 0){													// if field is empty
						match.fields[field.x][field.y].value = match.activePlayer;			// record value
						man.drawCharacter(match.fields[field.x][field.y], vm.board, match.activePlayer, ctx);		// draw character
						$timeout(function(){
							if (man.checkWin(field, match.activePlayer, match.fields)){		// check win
								alert("Player " + match.activePlayer + "wins!");
								match.fields = man.initFields(vm.board, 0);
								match.activePlayer = 1;
								man.drawBoard(vm.board, ctx);
							} else {
								match.activePlayer = match.activePlayer == 1 ? 2 : 1;		// switch player								
							}
						}, 200);
					}
					else if (field.value == 4) {
						man.activateTrap(match.fields[field.x][field.y], vm.board, ctx);
						match.activePlayer = match.activePlayer == 1 ? 2 : 1;				// switch player
					}
					*/
				}
			}, false);
        }
    }
}])

.factory('FiveInARowManager', ['$timeout', function($timeout){

	function drawBoard(board, ctx){
		var x = board.left;
		var y = board.top;
		
		ctx.fillStyle = board.backgroundColor;			// draw base
		ctx.fillRect(x, y, board.width, board.height);
		ctx.strokeStyle = board.borderColor;
		ctx.strokeRect(x, y, board.width, board.height);

		ctx.lineWidth = board.lineWidth;				// draw squares
		ctx.strokeStyle = board.lineColor;
		for(var i = 0; i < board.rows; i++){	
			x = board.left;
			for(var j = 0; j < board.cols; j++){
				ctx.strokeRect(x, y, board.squareSize, board.squareSize);
				x += board.squareSize;
			}
			y += board.squareSize;
		};
	};

	function initOptions(match, board, ctx){
		if (angular.isDefined(match.options) && match.options != null){
			if (match.options.indexOf(1) != -1){
				initTraps(match.fields);
			}
			if (match.options.indexOf(2) != -1){
				initWalls(match.fields, board, ctx);
			}
		}
	}
	
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
					xCoord : tempX,
					yCoord : tempY,
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
		if(isClickInBoard(board, x, y)){
			return {
				x : Math.floor( (x - board.left) / board.squareSize),
				y : Math.floor( (y - board.top) / board.squareSize)
			}
		}
		return null;
	}

	function drawCharacter(field, board, player, ctx){
		var char = player == 1 ? 'X' : 'O';
		var x = field.xCoord;						// coord
		var y = field.yCoord + board.squareSize;	// coord
		ctx.fillStyle = player == 1 ? "blue" : "red";
		ctx.font="30px Comic Sans MS";
		ctx.fillText(char, x, y);
	}

	function checkWin(field, player, fields){
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
		lengthSoFar = 0;
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

		// no winning found
		return false;
	}

	function initWalls(fields, board, ctx){
		var numOfWalls = 50;
		var x = 0;
		var y = 0;
		var i = 0;
		while (i<numOfWalls){
			var x = Math.floor(Math.random() * fields.length);
			var y = Math.floor(Math.random() * fields[0].length);
			if(fields[x][y].value != 3){
				fields[x][y].value = 3;

				ctx.fillStyle = "#a2c4c4";
				ctx.strokeStyle = board.lineColor;
				ctx.fillRect(fields[x][y].xCoord, fields[x][y].yCoord, board.squareSize, board.squareSize);
				ctx.strokeRect(fields[x][y].xCoord, fields[x][y].yCoord, board.squareSize, board.squareSize);

				i++;
				console.log(x + "," + y + " - " + fields[x][y].value);
			}
		}
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
		ctx.fillRect(field.xCoord, field.yCoord, board.squareSize, board.squareSize);
		ctx.strokeRect(field.xCoord, field.yCoord, board.squareSize, board.squareSize);
		
		// change field color to board color
		$timeout(function(){
			ctx.fillStyle = board.backgroundColor;
			ctx.fillRect(field.xCoord, field.yCoord, board.squareSize, board.squareSize);
			ctx.strokeRect(field.xCoord, field.yCoord, board.squareSize, board.squareSize);
			field.value = 0;
		}, 3000);
	}

	return {
		drawBoard: drawBoard,
		initOptions: initOptions,
		initFields: initFields,
		isClickInBoard: isClickInBoard,
		getClickedField: getClickedField,
		drawCharacter: drawCharacter,
		checkWin: checkWin,
		initWalls: initWalls,
		initTraps: initTraps,
		activateTrap: activateTrap
	}
}]);
