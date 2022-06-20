$('form').off('keypress.disableAutoSubmitOnEnter').on('keypress.disableAutoSubmitOnEnter', function(event) {
    if (event.which === $.ui.keyCode.ENTER && $(event.target).is(':input:not(textarea,:button,:submit,:reset)')) {
        event.preventDefault();
    }
});

$(document).ready(function() {
  $(window).keydown(function(event){
    if(event.keyCode == 13) {
      event.preventDefault();
      return false;
    }
  });
});

/**
 * Validador Cedula
 */
function validadorCedula(element){

	var cedula = element.value;
	array = cedula.split( "" );
	num = array.length;
	if ( num == 10 ){
		total = 0;
		digito = (array[9]*1);
		for( i=0; i < (num-1); i++ )
		{
			mult = 0;
			if ( ( i%2 ) != 0 ) {
				total = total + ( array[i] * 1 );
			}
			else
			{
				mult = array[i] * 2;
				if ( mult > 9 )
					total = total + ( mult - 9 );
				else
					total = total + mult;
			}
		}
		decena = total / 10;
		decena = Math.floor( decena );
		decena = ( decena + 1 ) * 10;
		final = ( decena - total );
		if ( ( final == 10 && digito == 0 ) || ( final == digito ) ) {      
			return true;
		}
		else
		{
				alert( "La identificacion NO es v\xe1lida!!!" );
				return false;
		}
	}else if(num == 13){
		if(!validarRuc(element)){
			alert( "La identificacion NO es v\xe1lida!!!" );
			return false;
		}else{
			return true;
		}
	}else
	{
		alert("La identificacion no puede tener menos de 10 d\xedgitos");
		return false;
	}
}

function validarRuc(element){
	var number = element.value;
	var dto = number.length;
	var valor;
	var acu=0;
	if(number==""){
		console.log('No has ingresado ningún dato, porfavor ingresar los datos correspondientes.');
	}
	else{
		for (var i=0; i<dto; i++){
			valor = number.substring(i,i+1);
			if(valor==0||valor==1||valor==2||valor==3||valor==4||valor==5||valor==6||valor==7||valor==8||valor==9){
				acu = acu+1;
			}
		}
		if(acu==dto){
			while(number.substring(10,13)!=001){
				console.log('Los tres últimos dígitos no tienen el código del RUC 001.');
				alert( "La identificacion NO es v\xe1lida!!!" );
				return false;
			}
			while(number.substring(0,2)>24){    
				console.log('Los dos primeros dígitos no pueden ser mayores a 24.');
				alert( "La identificacion NO es v\xe1lida!!!" );
				return false;
			}
			console.log('El RUC está escrito correctamente');
			console.log('Se procederá a analizar el respectivo RUC.');
			var porcion1 = number.substring(2,3);
			if(porcion1<6){
				console.log('El tercer dígito es menor a 6, por lo \ntanto el usuario es una persona natural.\n');
				return true;
			}
			else{
				if(porcion1==6){
					console.log('El tercer dígito es igual a 6, por lo \ntanto el usuario es una entidad pública.\n');
					return true;
				}
				else{
					if(porcion1==9){
						console.log('El tercer dígito es igual a 9, por lo \ntanto el usuario es una sociedad privada.\n');
						return true;
					}
				}
			}
		}
		else{
			alert("ERROR: Por favor no ingrese texto");
		}
	}
}

/**
 * Permite el ingreso solo de numeros
 * @param e
 * @returns {Boolean}
 */

function soloNumeros(e){
	var key = window.Event ? e.which : e.keyCode
			// Backspace = 8, Enter = 13, ’0′ = 48, ’9′ = 57, ‘.’ = 46
			return ((key >= 48 && key <= 57) || key == 8 || key == 191 || key == 46 || key == 44)
}


function soloNumerosNoCaracteres(e){
	var key = window.Event ? e.which : e.keyCode
			// Backspace = 8, Enter = 13, ’0′ = 48, ’9′ = 57, ‘.’ = 46
			return ((key >= 48 && key <= 57) || key == 8)
}

function noCaracteresEspeciales(e) {
    tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla == 8) {
        return true;
    }

    // Patron de entrada, en este caso solo acepta numeros y letras
    patron = /^[0-9a-zA-Z]+$/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}

/**
 * Muestra Oculta un elemento
 * @param element
 */
function mostrarOcultar(element){
	var elmt = document.getElementById(element)
	if(elmt.style.display == "none") {
		elmt.style.display == "block"
	}else{
		elmt.style.display == "none"
	}
}

/**
 * Copia un los valores de un elemento a otro
 * @param origen
 * @param destino
 * @returns
 */
function copiarValores(origen, destino) {
	destino.value = origen.value;
}

function validarCorreo(element){
	//var emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
	var emailRegex = /^(?:[^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*|"[^\n"]+")@(?:[^<>()[\].,;:\s@"]+\.)+[^<>()[\]\.,;:\s@"]{2,63}$/i;
	if(!emailRegex.test(element.value)){
		element.className += " invalid";
		element.placeholder = "Correo invalido, digite nuevamente";
		element.value = "";
	}else{
		element.className = "ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all input12";
	}
}

function validaIdentificacion(element, tid){
	var elmt = document.getElementById(tid);
	var tipo = elmt.options[elmt.selectedIndex].value
	if(null != element.value && element.value != ''){
		var valida = validaCedulaRuc(element.value, tipo);
		if(!valida){
			element.className += " invalid";
			element.value = "";
			element.placeholder = "La identificación ingresada no es valida.";
		}else{
			element.className = " ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all input12";
		}
	}	
}

/**
 * 
 * @param numero
 * @returns
 */
function validaCedulaRuc(numero, tid){
	
	var suma = 0;
	var residuo = 0;
	var pri = false;
	var pub = false;
	var nat = false;
	var numeroProvincias = 24;
	var cedextranjeros = 30;
	var modulo = 11;

	/* Verifico que el campo no contenga letras */                  
	var ok=1;
	for (i=0; i<numero.length && ok==1 ; i++){
		var n = parseInt(numero.charAt(i));
		if (isNaN(n)) ok=0;
	}
	if (ok==0 && (tid != 3 && tid != 5)){
		alert("- No puede ingresar caracteres en el número");         
		return false;
	}

	/*if (numero.length < 10 || numero.length > 13){
		alert("- La identificacion ingresada no es válida, verifique el tipo seleccionado ");
		return false;
	}else*/ if((tid == 2 && numero.length > 10) || (tid == 1 && numero.length < 13)){
		alert("- La identificacion ingresada no corresponde con el tipo de identificación seleccionado");	
		return false;
	}
	
	
	if(tid == 1 || tid == 2){
		/* Los primeros dos digitos corresponden al codigo de la provincia */
		provincia = numero.substr(0,2);      
		if (provincia < 1 ){
			alert("- El código de la provincia (dos primeros dígitos) es inválido");
			return false;       
		}else if(provincia <= numeroProvincias || provincia == cedextranjeros){
			/* Aqui almacenamos los digitos de la cedula en variables. */
			d1  = numero.substr(0,1);         
			d2  = numero.substr(1,1);         
			d3  = numero.substr(2,1);         
			d4  = numero.substr(3,1);         
			d5  = numero.substr(4,1);         
			d6  = numero.substr(5,1);         
			d7  = numero.substr(6,1);         
			d8  = numero.substr(7,1);         
			d9  = numero.substr(8,1);         
			d10 = numero.substr(9,1);                
	
			/* El tercer digito es: */                           
			/* 9 para sociedades privadas y extranjeros   */         
			/* 6 para sociedades publicas */         
			/* menor que 6 (0,1,2,3,4,5) para personas naturales */ 
			if (d3==7 || d3==8){           
				alert("- El tercer dígito ingresado es inválido");                     
				return false;
			}         
	
			/* Solo para personas naturales (modulo 10) */         
			if (d3 < 6){           
				nat = true;            
				p1 = d1 * 2;  if (p1 >= 10) p1 -= 9;
				p2 = d2 * 1;  if (p2 >= 10) p2 -= 9;
				p3 = d3 * 2;  if (p3 >= 10) p3 -= 9;
				p4 = d4 * 1;  if (p4 >= 10) p4 -= 9;
				p5 = d5 * 2;  if (p5 >= 10) p5 -= 9;
				p6 = d6 * 1;  if (p6 >= 10) p6 -= 9; 
				p7 = d7 * 2;  if (p7 >= 10) p7 -= 9;
				p8 = d8 * 1;  if (p8 >= 10) p8 -= 9;
				p9 = d9 * 2;  if (p9 >= 10) p9 -= 9;             
				modulo = 10;
			}         
			/* Solo para sociedades publicas (modulo 11) */                  
			/* Aqui el digito verficador esta en la posicion 9, en las otras 2 en la pos. 10 */
			else if(d3 == 6){           
				pub = true;             
				p1 = d1 * 3;
				p2 = d2 * 2;
				p3 = d3 * 7;
				p4 = d4 * 6;
				p5 = d5 * 5;
				p6 = d6 * 4;
				p7 = d7 * 3;
				p8 = d8 * 2;            
				p9 = 0;            
			}         
	
			/* Solo para entidades privadas (modulo 11) */         
			else if(d3 == 9) {           
				pri = true;                                   
				p1 = d1 * 4;
				p2 = d2 * 3;
				p3 = d3 * 2;
				p4 = d4 * 7;
				p5 = d5 * 6;
				p6 = d6 * 5;
				p7 = d7 * 4;
				p8 = d8 * 3;
				p9 = d9 * 2;            
			}
	
			suma = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;                
			residuo = suma % modulo;                                         
			/* Si residuo=0, dig.ver.=0, caso contrario 10 - residuo*/
			digitoVerificador = residuo==0 ? 0: modulo - residuo;                
			/* ahora comparamos el elemento de la posicion 10 con el dig. ver.*/                         
			if (pub==true){           
				if (digitoVerificador != d9){                          
					alert("- El ruc de la empresa del sector público es incorrecto.");            
					return false;
				}                  
				/* El ruc de las empresas del sector publico terminan con 0001*/         
				if ( numero.substr(9,4) != '0001' ){                    
					alert("- El ruc de la empresa del sector público debe terminar con 0001");
					return false;
				}
			} else if(pri == true){         
				if (digitoVerificador != d10){                          
					alert("- El ruc de la empresa del sector privado es incorrecto.");
					return false;
				}         
				if ( numero.substr(10,3) != '001' ){                    
					alert("- El ruc de la empresa del sector privado debe terminar con 001");
					return false;
				}
			}      
			else if(nat == true){         
				if (digitoVerificador != d10){                          
					alert("- El número de cédula de la persona natural es incorrecto.");
					return false;
				}         
				if (numero.length >10 && numero.substr(10,3) != '001' ){                    
					alert("- El ruc de la persona natural debe terminar con 001");
					return false;
				}
			}      
			return true;   
		}else {
			alert("- El código de la provincia (dos primeros dígitos) es inválido");
			return false;
		}
	}
	return true;
}