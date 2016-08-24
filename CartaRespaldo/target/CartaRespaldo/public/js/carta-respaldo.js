function SeparadorDecimal() {
	if (event.keyCode == 46) {
		event.preventDefault();
		event.target.value += ",";
	}
}

function soloNumero() {
	if (event.keyCode < 48 || event.keyCode > 57)
		event.returnValue = false;
}

function soloLetras() {
	
	if(event.keyCode > 32 &&  (event.keyCode < 65 || event.keyCode > 90)  && (event.keyCode < 97 || event.keyCode > 122 ) && (event.keyCode <= 192 || event.keyCode >= 255) &&
			(event.keyCode < 39 || event.keyCode > 45 )) {
		event.returnValue = false;
		}
	else if (event.keyCode == 40 || event.keyCode == 41 || event.keyCode == 42 || event.keyCode == 43 || event.keyCode == 44){
		event.returnValue = false;
	}
}

