


function MM_swapImgRestore() { // v3.0
	var i, x, a = document.MM_sr;
	for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
		x.src = x.oSrc;
}

function MM_preloadImages() { // v3.0
	var d = document;
	if (d.images) {
		if (!d.MM_p)
			d.MM_p = new Array();
		var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
		for (i = 0; i < a.length; i++)
			if (a[i].indexOf("#") != 0) {
				d.MM_p[j] = new Image;
				d.MM_p[j++].src = a[i];
			}
	}
}

function MM_findObj(n, d) { // v4.01
	var p, i, x;
	if (!d)
		d = document;
	if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
		d = parent.frames[n.substring(p + 1)].document;
		n = n.substring(0, p);
	}
	if (!(x = d[n]) && d.all)
		x = d.all[n];
	for (i = 0; !x && i < d.forms.length; i++)
		x = d.forms[i][n];
	for (i = 0; !x && d.layers && i < d.layers.length; i++)
		x = MM_findObj(n, d.layers[i].document);
	if (!x && d.getElementById)
		x = d.getElementById(n);
	return x;
}

function MM_swapImage() { // v3.0
	var i, j = 0, x, a = MM_swapImage.arguments;
	document.MM_sr = new Array;
	for (i = 0; i < (a.length - 2); i += 3)
		if ((x = MM_findObj(a[i])) != null) {
			document.MM_sr[j++] = x;
			if (!x.oSrc)
				x.oSrc = x.src;
			x.src = a[i + 2];
		}
}

function setCommandValue(formReference, actionValue) {
	var frm = document.forms[formReference];
	frm.elements["command"].value = actionValue;
}
function submitForm(formReference, actionValue) {
	setCommandValue(formReference, actionValue);
	formReference.submit();
}




function aceptaMayusculasSinCaracteres(){

                             //mayusculas                                                                      //Ã‘                     //Ã±                 //space
if (event.keyCode >=65  && event.keyCode <= 90  || event.keyCode==209 || event.keyCode==241 || event.keyCode==32 ){

    event.returnValue = true;


}//minusculas
else if(event.keyCode >=97 && event.keyCode <= 122 ){

     event.keyCode = event.keyCode-32;


}

	else{

            event.returnValue = false;

        }



}





function AceptaNumerico() {
	if (event.keyCode >= 48 && event.keyCode <= 57)
		event.returnValue = true;
	else
		event.returnValue = false;

}

function muestraAnio(){
    //1946-1994
    var opciones=$("#anio").attr("options");
    var inicio=1946;

    while(inicio<=1994){
        opciones[opciones.length]=new Option(inicio,inicio);
        inicio++;
    }

}


function muestraDias() {

    var dias=["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
                "18","19","20","21","22","23","24","25","26","27","28","29","30","31"];

        var mes;
	mes = parseInt(formatoDiasMes(document.getElementById("mes").value));

        //borra lo que habia anteriormente en el combo dia
        $("#dia").find("option:gt(0)").remove();


	if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10
			|| mes == 12) {

               for(var index=0;index<dias.length;index++){
			document.getElementById("dia").options[index+1] = new Option(dias[index],dias[index]);
		}

	} else if (mes == 2) {

                var resta=0;

                var anio_b=(document.getElementById("anio").selectedIndex%4);

                if(anio_b==3){
                    //entonces es bisiesto
                   resta=2;
                }else{
                    resta=3;
                }

                 for(var index=0;index<dias.length-resta;index++){
			document.getElementById("dia").options[index+1] = new Option(dias[index],dias[index]);
		}

	} else {
		for(var index=0;index<dias.length-1;index++){
			document.getElementById("dia").options[index+1] = new Option(dias[index],dias[index]);
		}
	}
}


function crearFecNac(){

    
}





function deshabilitarFecNac(){
    document.getElementById("dia").value="-1";
    document.getElementById("mes").value="-1";
    document.getElementById("cliEdad").value = "";
}



function calcular_edad() {

        var anios=["1946","1947","1948","1949","1950","1951","1952","1953"
            ,"1954","1955","1956","1957","1958","1959","1960","1961","1962"
            ,"1963","1964","1965","1966","1967","1968","1969","1970","1971"
            ,"1972","1973","1974","1975","1976","1977","1978","1979","1980",
            "1981","1982","1983","1984","1985","1986","1987","1988","1989",
            "1990","1991","1992","1993","1994"]

         var dias=["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
                "18","19","20","21","22","23","24","25","26","27","28","29","30","31"];

	hoy = new Date();
	var ano;
	var ano_calc = parseInt(document.getElementById("anio").selectedIndex);
        ano=parseInt(anios[ano_calc-1])
	if (ano == -1) {
		return false;
	}
	var mes;
	mes = parseInt(formatoDiasMes(document.getElementById("mes").value));
	if (mes == -1) {
		return false;
	}
	var dia;
	var dia_calc = parseInt(formatoDiasMes(document.getElementById("dia").selectedIndex));
        dia=parseInt(dias[dia_calc-1])
	if (dia == -1) {
		return false;
	}
	var edad = parseInt(hoy.getFullYear() - ano - 1);
        

	if (parseInt(hoy.getMonth() + 1 - mes) < 0) {

		if (parseInt(edad) < 18) {
			document.getElementById("cliEdad").value = "";
			alert("Error en la Edad, debe tener entre 18 y 65 de edad");
                        document.getElementById("dia").value="-1";
                        document.getElementById("anio").value="-1";
                        document.getElementById("mes").value="-1";
			return false;
		} else {
			document.getElementById("cliEdad").value = edad;
                        $.jStorage.set("cliEdad",edad);
			return false;
		}
	}
	if (parseInt(hoy.getMonth() + 1 - mes) > 0) {

                edad=edad+1;

		if (parseInt(edad) < 18) {
			document.getElementById("cliEdad").value = "";
			alert("Error en la Edad, debe tener entre 18 y 65 de edad");
                        document.getElementById("dia").value="-1";
                        document.getElementById("anio").value="-1";
                        document.getElementById("mes").value="-1";
			return false;
		} else {
			document.getElementById("cliEdad").value = edad;
                        $.jStorage.set("cliEdad", edad);
			return false;
		}
	}
	if (parseInt(hoy.getUTCDate() - dia -1) >= 0) {


             edad=edad+1;

		if (parseInt(edad) < 18) {


			document.getElementById("cliEdad").value = "";
			alert("Error en la Edad, debe tener entre 18 y 65 de edad");
                        document.getElementById("dia").value="-1";
                        document.getElementById("anio").value="-1";
                        document.getElementById("mes").value="-1";
			return false;
		} else {
			document.getElementById("cliEdad").value = edad ;
                        $.jStorage.set("cliEdad", edad);
			return false;
		}
	} else {



		if (parseInt(edad) < 18) {
			document.getElementById("cliEdad").value = "";
			alert("Error en la Edad, debe tener entre 18 y 65 de edad");
                        document.getElementById("dia").value="-1";
                        document.getElementById("anio").value="-1";
                        document.getElementById("mes").value="-1";
			return false;
		} else {
			document.getElementById("cliEdad").value = edad;
                        $.jStorage.set("cliEdad",edad);
			return false;
		}
	}
	return false;
}



function formatoDiasMes(valor) {
	var numero;
	if (valor == "01")
		numero = "1";
	else if (valor == "02")
		numero = "2";
	else if (valor == "03")
		numero = "3";
	else if (valor == "04")
		numero = "4";
	else if (valor == "05")
		numero = "5";
	else if (valor == "06")
		numero = "6";
	else if (valor == "07")
		numero = "7";
	else if (valor == "08")
		numero = "8";
	else if (valor == "09")
		numero = "9";
	else
		numero = valor;
	return numero;
}


function bloqueaBackHistory() {

    
}



function generaRfc(){


}

