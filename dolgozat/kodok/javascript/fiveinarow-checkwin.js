function checkWin(field, player, fields){
	var startX = field.x - 4;
	var startY = field.y - 4;
	var endX = field.x + 4;
	var endY = field.y + 4;
	
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

	// no winning found
	return false;
}