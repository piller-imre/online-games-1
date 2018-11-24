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

options values:
	1 - init bombs
	2 - init walls
	3 - random field clear
	4 - custom bombs
*/


directives.directive('fiveInARow', [
	'CanvasService', 
	'$timeout', 
	'FiveInARowManager', 
	'$rootScope',
	'$http',
	'$interval',
	'$localStorage',
	function(
		CanvasService, 
		$timeout,
		FiveInARowManager,
		$rootScope,
		$http,
		$interval,
		$localStorage){
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

			var match = null;

			match = man.initiateMatch(vm.initMatch, match, vm.board, ctx);
			
			if (match.players.activePlayer == 1) {
				vm.disable = $localStorage.currentUser.userid == match.players.player1.id ? false : true;
			} else {
				vm.disable = $localStorage.currentUser.userid == match.players.player2.id ? false : true;
			}

			if (vm.disable){
				match.turn --;
				checkAction();
			}

			// Add event listener for 'click' events.
			elem.addEventListener('click', function(event) {
				if(!vm.disable){

					vm.disable = true;

					var x = event.pageX - elem.offsetLeft;	// get canvas x,	elem.offsetLeft - canvas origo x
					var y = event.pageY - elem.offsetTop;	// get canvas y		elem.offsetTop - canvas origo y

					var field = man.getClickedField(vm.board, x, y);						// get clicked field by index

					if(field != null){
						field.value = match.fields[field.x][field.y].value;						// get value of field
						console.log(field.x + "," + field.y + ' - ' + field.value);

						if([0,4].includes(field.value)){
							match.action = field;
							var data = man.getMatchReadyToSend(match);

							$http.post(baseUrl + '/fiveinarow/action', data)
							.then(function(result){
								var field = match.fields[match.action.x][match.action.y];
								if(match.action.value == 4){
									man.activateTrap(field, vm.board, ctx);
								}
								if(match.action.value == 0){
									man.drawCharacter(field, vm.board, match.players.activePlayer, ctx);
								}
								if(result.data){
									checkAction();
								} else {
									vm.disable = false;
								}
							});
						}
						
					}
				}
			}, false);

			function checkAction(){
				vm.promise = $interval(function(){
					console.log(baseUrl + '/fiveinarow/checkaction');
					$http.get(baseUrl + '/fiveinarow/checkaction', {
						params: {
							matchId: match.id,
							turn: match.turn + 1
						}
					})
					.then(function(result){
						if(result.data != null && result.data != ""){
							vm.initMatch = result.data;
							match = man.reload(vm.initMatch, match, vm.board, ctx);
							
							if (match.players.activePlayer == 1) {
								vm.disable = $localStorage.currentUser.userid == match.players.player1.id ? false : true;
							} else {
								vm.disable = $localStorage.currentUser.userid == match.players.player2.id ? false : true;
							}

							stopcheckAction();
						}
					});
				}, 5000, false);
			}

			function stopcheckAction(){
				console.log("checkAction OFF");
				$interval.cancel(vm.promise);
			}
        }
    }
}])

.factory('FiveInARowManager', ['$timeout', '$localStorage', function($timeout, $localStorage){

	function initiateMatch(initMatch, match, board, ctx){
		
		var tempFields = resetFields(board, 0);
		initMatch.fields = JSON.parse(initMatch.boardstate);
		
		for (var i = 0; i < initMatch.fields.length; i++) {
			for (var j = 0; j < initMatch.fields[i].length; j++) {
				tempFields[i][j].value = initMatch.fields[i][j].value;
			}
		}

		initMatch.fields = tempFields;
		match = initMatch;
		
		// match = {
		// 	matchId : initMatch.id,
		// 	player1 : initMatch.players.player1,
		// 	player2 : initMatch.players.player2,
		// 	activePlayer : initMatch.players.activePlayer,
		// 	turn: initMatch.turn,
		// 	fields : tempFields,
		// 	options : initMatch.options
		// };

		drawBoard(board, ctx);
		drawFields(match, board, ctx);

		return match;
	}

	function reload(initMatch, match, board, ctx){
		initMatch.fields = initMatch.boardstate == "" ? null : JSON.parse(initMatch.boardstate);
		initMatch.action = JSON.parse(initMatch.action);
		match = initMatch;
		console.log("WIN: " + match.win);

		// convert string to int array
		if(match.options != null){
			var op = match.options;
			op = op.substring(1, op.length-1);
			op = op.split(',').map(function(item) {
				return parseInt(item, 10);
			});
			match.options = op;
		}

		if(match.action){
			var field = match.fields[match.action.x][match.action.y];

			switch (match.action.value) {
				case 0:
					var player = match.players.activePlayer == 1 ? 2 : 1;
					drawCharacter(field, board, player, ctx);		// draw character
					break;
				case 4:
					activateTrap(field, board, ctx);
					break;
				default:
					break;
			}
		}

		if(match.win && match.win > 0){
			if(match.win == 1){
				$localStorage.currentUser.userid == match.player1 ? winMsg = "Gratul�lok, nyert�l!" : winMsg = "Sajnos vesztett�l!";
			} else {
				$localStorage.currentUser.userid == match.player2 ? winMsg = "Gratul�lok, nyert�l!" : winMsg = "Sajnos vesztett�l!";
			}
			alert(winMsg);
			$state.go('game-play');
		}

		return match;
	}

	function getMatchReadyToSend(match){
		var tempMatch = angular.copy(match);
		tempMatch.matchId = match.id;
		tempMatch.player1 = match.players.player1;
		tempMatch.player2 = match.players.player2;
		tempMatch.activePlayer = match.players.activePlayer;

		delete tempMatch.id;
		delete tempMatch.gameType;
		delete tempMatch.players;

		return tempMatch;
	}

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

	function drawFields(match, board, ctx){
		var fields = match.fields;

		for (var i = 0; i < fields.length; i++) {
			for(var j = 0; j < fields[i].length; j++){
				switch (fields[i][j].value) {
					case 1:
						drawCharacter(fields[i][j], board, 1, ctx)
						break;
					case 2:
						drawCharacter(fields[i][j], board, 2, ctx)
						break;
					case 3:
						drawWall(fields[i][j], board, ctx)
						break;
					default:
						break;
				}
			}
		}
	};

	// function initOptions(match, board, ctx){
	// 	if (angular.isDefined(match.options) && match.options != null){
	// 		if (match.options.indexOf(1) != -1){
	// 			initTraps(match.fields);
	// 		}
	// 		if (match.options.indexOf(2) != -1){
	// 			initWalls(match.fields, board, ctx);
	// 		}
	// 	}
	// }
	
	function resetFields( board, defaultValue){
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

	function drawWall(field, board, ctx){
		ctx.fillStyle = "#a2c4c4";
		ctx.strokeStyle = board.lineColor;
		ctx.fillRect(field.xCoord, field.yCoord, board.squareSize, board.squareSize);
		ctx.strokeRect(field.xCoord, field.yCoord, board.squareSize, board.squareSize);

	}

	// function initWalls(fields, board, ctx){
	// 	var numOfWalls = 50;
	// 	var x = 0;
	// 	var y = 0;
	// 	var i = 0;
	// 	while (i<numOfWalls){
	// 		var x = Math.floor(Math.random() * fields.length);
	// 		var y = Math.floor(Math.random() * fields[0].length);
	// 		if(fields[x][y].value != 3 && fields[x][y].value != 4){
	// 			fields[x][y].value = 3;

	// 			ctx.fillStyle = "#a2c4c4";
	// 			ctx.strokeStyle = board.lineColor;
	// 			ctx.fillRect(fields[x][y].xCoord, fields[x][y].yCoord, board.squareSize, board.squareSize);
	// 			ctx.strokeRect(fields[x][y].xCoord, fields[x][y].yCoord, board.squareSize, board.squareSize);

	// 			i++;
	// 			console.log(x + "," + y + " - " + fields[x][y].value);
	// 		}
	// 	}
	// }

	// function initTraps(fields){
	// 	var numOfTraps = 50;
	// 	var x = 0;
	// 	var y = 0;
	// 	var i = 0;
	// 	while (i<numOfTraps){
	// 		var x = Math.floor(Math.random() * fields.length);
	// 		var y = Math.floor(Math.random() * fields[0].length);
	// 		if(fields[x][y].value != 4 && fields[x][y].value != 3){
	// 			fields[x][y].value = 4;
	// 			i++;
	// 			console.log(x + "," + y + " - " + fields[x][y].value);
	// 		}
	// 	}
	// }

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
		initiateMatch: initiateMatch,
		reload: reload,
		getClickedField: getClickedField,
		getMatchReadyToSend: getMatchReadyToSend,
		activateTrap: activateTrap,
		drawCharacter: drawCharacter,
		// drawBoard: drawBoard,
		// initOptions: initOptions,
		// resetFields: resetFields,
		// isClickInBoard: isClickInBoard,
		// checkWin: checkWin,
		// initWalls: initWalls,
		// initTraps: initTraps,
	}
}]);


/*
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
*/