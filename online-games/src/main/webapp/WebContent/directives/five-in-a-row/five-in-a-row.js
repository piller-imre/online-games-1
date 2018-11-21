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
	function(
		CanvasService, 
		$timeout,
		FiveInARowManager,
		$rootScope,
		$http,
		$interval){
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
			console.log('matchId: ' + match.matchId);

			// Add event listener for 'click' events.
			elem.addEventListener('click', function(event) {
				if(!vm.disable){

					vm.disable = true;

					var x = event.pageX - elem.offsetLeft;	// get canvas x,	elem.offsetLeft - canvas origo x
					var y = event.pageY - elem.offsetTop;	// get canvas y		elem.offsetTop - canvas origo y
					console.log(x, y);

					var field = man.getClickedField(vm.board, x, y);						// get clicked field by index

					if(field != null){
						field.value = match.fields[field.x][field.y].value;						// get value of field
						console.log(field.x + "," + field.y + ' - ' + field.value);

						if([0,4].includes(field.value)){
							match.action = field;
							console.log("send nudes");
							console.log(match.action);

							$http.post(baseUrl + '/fiveinarow/action', match)
							.then(function(result){
								console.log("valid action: " + result.data);
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
							matchId: match.matchId,
							turn: match.turn
						}
					})
					.then(function(result){
						console.log(result.data);
						if(result.data != null && result.data != ""){
							vm.initMatch = result.data;
							man.reload(vm.initMatch, match, vm.board, ctx, vm.disable);
							stopcheckAction();
						}
					});
				}, 5000, false);
			}

			function stopcheckAction(){
				$interval.cancel(vm.promise);
			}
        }
    }
}])

.factory('FiveInARowManager', ['$timeout', '$localStorage', function($timeout, $localStorage){

	function initiateMatch(initMatch, match, board, ctx){
		initMatch.fields = initMatch.boardstate == "" ? null : JSON.parse(initMatch.boardstate);
		match = {
			matchId : initMatch.id,
			player1 : initMatch.players.player1,
			player2 : initMatch.players.player2,
			activePlayer : initMatch.players.activePlayer,
			turn: initMatch.turn,
			fields : initMatch.fields ? initMatch.fields : resetFields(board, 0),
			options : initMatch.options
		};

		drawBoard(board, ctx);
		if(match.fields){
			drawFields(match, board, ctx);
		} else {
			initOptions(match, board, ctx);
		}

		return match;
	}

	function reload(initMatch, match, board, ctx, disable){
		initMatch.fields = initMatch.boardstate == "" ? null : JSON.parse(initMatch.boardstate);
		match = {
			matchId : initMatch.id,
			player1 : initMatch.players.player1,
			player2 : initMatch.players.player2,
			activePlayer : initMatch.players.activePlayer,
			turn: initMatch.turn,
			fields : initMatch.fields ? initMatch.fields : resetFields(board, 0),
			options : initMatch.options
		};

		if(initMatch.action){
			switch (initMatch.action.value) {
				case 1 || 2:
					drawCharacter(match.fields[initMatch.action.x][initMatch.action.y], board, previousPlayer, ctx);		// draw character
					break;
				case 4:
					activateTrap(match.fields[initMatch.action.x][initMatch.action.y], board, ctx);
					break;
				default:
					break;
			}
		}

		if(initMatch.win && initMatch.win > 0){
			if(initMatch.win == 1){
				$localStorage.currentUser.userid == match.player1 ? winMsg = "Gratulálok, nyertél!" : winMsg = "Sajnos vesztettél!";
			} else {
				$localStorage.currentUser.userid == match.player2 ? winMsg = "Gratulálok, nyertél!" : winMsg = "Sajnos vesztettél!";
			}
			alert(winMsg);
			$state.go('game-play');
		}

		if (match.activePlayer == 1) {
			disable = $localStorage.currentUser.userid == match.player1.id ? false : true;
		} else {
			disable = $localStorage.currentUser.userid == match.player2.id ? false : true;
		}
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
					case 1 || 2:
						drawCharacter(fields[i][j], board, player, ctx)
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
		initiateMatch: initiateMatch,
		reload: reload,
		getClickedField: getClickedField,
		// drawBoard: drawBoard,
		// initOptions: initOptions,
		// resetFields: resetFields,
		// isClickInBoard: isClickInBoard,
		// drawCharacter: drawCharacter,
		// checkWin: checkWin,
		// initWalls: initWalls,
		// initTraps: initTraps,
		// activateTrap: activateTrap
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