
/*
 **metodo que llena los campos de la pagina
 */
function actualizarPagina(){

       
        var nombres=new Array();
    
      nombres=$(":input").toArray()

        

    $.each(nombres, function(i,val ){

        tipo=val.type;

          if(tipo=="text" ||tipo=="select-one"){
                
              llenarTextBox(val);
          }

          if(tipo=="textarea"){


              llenarTextArea(val);
          }
          if(tipo=="radio" || tipo=="checkbox"){

              llenarRadios(val);
          }
            

    })
}

/*
 *function que llena los textbox
 */
function llenarTextBox(val){

            var nombre = val.name;



            var valor=$.jStorage.get(nombre);


                 //si existe el valor en el json entonces
                if(valor!=null && valor!="null" && valor!="NULL"){

                    val.value=valor;
                }


}


/*
 *funcion que llena text areas
 */
function llenarTextArea(val){

             nombre = val.name;



            var valor=$.jStorage.get(nombre);

          

                 //si existe el valor en el json entonces
                if(valor){

                    val.value=valor;
                }


}




/*
 **actualiza los radios de las paginas
 */
function llenarRadios(val){

        var valor= $.jStorage.get(val.name,"") ;

        if(valor){

             val.checked = $.jStorage.get(val.name,"") == val.value;

        }

}



/*
 * funcion que sirve para obtener el json del servlet y actualizar la pagina si es que existe
 * el valor
 *
 */
function crearJson(){

    var accion=$.jStorage.get("accion");

    var seguridad=$.jStorage.get("seguridad");


        if(seguridad!="true"){

             $.jStorage.flush();

             $.jStorage.set("seguridad", "true");

        }


          //variable para manejar la seguridad


    if(accion!="add" && seguridad!="true"){

          obtenerJsonDelServlet();

    }


    actualizarPagina();

}



/*
 * metodo para obtener el json desde el servlet
 */
function obtenerJsonDelServlet(){

             $.ajax({
                  url: 'otro.json',
                  dataType: 'json',
                  async:false,
                  success: function(json) {

                    

                       $.each(json, function llenado(i , val){

                            $.jStorage.set(i, val);

                       })

                       
                  },
                  cache: false
               });


}


/*
 * function que sirve para que en el evento onblur se mande la informacion para ser guardada
 * en el json
 */
function autollenado(){


       
    $("*").live('blur',function() {

        var tipo=this.type;


        if(tipo=="select-one" || tipo=="text"){

             llenarCampoTexto(this);
        }

        if(tipo=="textarea"){

            llenarCampoTextArea(this);
        }

       

    });

}



/*
 * metodo que sirve para ir llenando nuestro json   CON CAMPOS DE TEXTO
 */
function llenarCampoTexto(campo){


    var nombre=campo.name;

    var valor=campo.value;


    $.jStorage.set(nombre,valor);


}


/*
 * metodo que sirve para ir llenando nuestro json   CON CAMPOS DE text area
 */
function llenarCampoTextArea(campo){



    

    var nombre=campo.name;


    var valor=campo.value;


    $.jStorage.set(nombre,valor);

   
}




/*
 *hace lo mismo que autollenado pero solo para radios
 */
function autoLlenadoRadios(){

    
    $(":radio").click(function() {

        
        llenarCampoRadio($(this).attr("name") , $(this).attr("value"));

    });


}

function autoLlenadoCheckBox(){

    $(":checkbox").click(function() {

        var valor;

        if($(this).attr("checked")==true){
            valor=1;
        }else{
            valor=0;
        }
        llenarCampoRadio($(this).attr("name") , valor);


    });
}



function llenarCampoRadio(name , valor){

        var gol=$.jStorage.set(name,valor);

       
           
}





function mandarJsonAlServlet(){

        //arreglo con todas las variables
        var variables=pasarAJson();

        //hacemos todo lo del servlet
        guardarServlet(variables);

        
}


function guardarServlet(variables){

     $.ajax({
          url: '../NuevoGolServlet',
          async: false,
          data:  variables,
          cache: false,
          success:function(){
              window.location="golindex.html";
          }
         });


}





function pasarAJson(){

    var regreso=new Object();

    var variables=new Array();

    variables=$.jStorage.index();

    for(t=0;t<variables.length;t++){

         regreso[variables[t]]=$.jStorage.get(variables[t]);


    }

    return regreso;
}


/*
 * para ver que opcion fue elegida en un radiobutton
 */
function elegida(campo){

    radios = document.getElementsByName(campo);

	for (  i = 0; i < radios.length; i++) {
		if (radios[i].checked) {
			return i+1;
		}
	}
	return -1;

}


//combos

/**
 * Carga el combo con el id "nombre" y con los valores "valores".
 * "valores" es un arreglo asociativo, donde la llave es el contenido
 * y el valor el id.
 */
function cargaCombo(id, valores) {

    var opciones = $("#" + id).attr("options");

    if(opciones!=undefined){


            $.each(valores, function(ind, val){

               opciones[opciones.length]=new Option(ind,val);

            });


    }

    
}




function cargaCombos(){

        var selects=$("select").toArray();

    $.each(selects, function(){

            if(esCampoServicio(this)){
        
                llenarComboServicio(this,null,null);
                return;
            }

            var filtrado= filtrarNombre(this.id);

                if(filtrado!=null){


                    var lista=obtenLista(this.id,filtrado);

                }
    });
}


function filtrarNombre(nombre){

    if(nombre.substr(nombre.length-2, nombre.lenth)!="Id"){
        return null;
    }

    var mayus=nombre.substr(0,1).toUpperCase()+nombre.substr(1, nombre.length);

    var quitarId=mayus.substr(0, mayus.length-2);

    
    return quitarId;
}


function obtenLista(id,filtrado) {

    var cal=obtenerCal(filtrado);


$.ajax({
                  url: '../CargaCombos?combo='+filtrado+"&cal="+cal,
                  dataType: 'json',
                  async:false,
                  success: function(json) {
                            cargaCombo(id, json);

                  },
                  cache: true
               });


}



function obtenerCal(variable){

    var cal;

    $.ajax({
                  url: "../recursos/cals.json",
                  dataType: 'json',
                  async:false,
                  success: function(json) {
                           cal= buscarCal(variable,json);
                  },
                  cache: true
               });
     return cal;

}


function buscarCal(variable,json){

    
    if(json!=null){

        return json[variable];

    }
    
    return null;
    
}


//cliSector



//verificamos si pertence a cliSector
function esCampoServicio(campo){

    return campo.id=="srvId";

}

//si si pertenece obtenemos todo el combo de cliSector
function llenarComboServicio(campo,inferior,superior){


    if(superior==null && inferior==null){

      inferior=obtenerInferior(campo);

      superior=obtenerSuperior(campo);

    }

   
    $.ajax({
                  url: '../ComboServicio?limInf='+inferior+"&limSup="+superior+"&nom="+campo.name,
                  dataType: 'json',
                  async:false,
                  success: function(json) {
                        
                            cargaComboServ(campo, json);

                  },
                  cache: true
               });




}

//vamos a cargar el combo dependiendo el nombre de la variable
function cargaComboServ(campo, valores){


    var opciones = campo.options;


    if(opciones!=undefined ){


            $.each(valores, function(ind, val){


               opciones[opciones.length]=new Option(ind,val);

            });


    }


}


function obtenerInferior(campo){
    
    if(campo.name=="servInd"){
        return 18;
    }
    if(campo.name=="cliSector"){
        return 35;
    }
    
    
}

function obtenerSuperior(campo){

    if(campo.name=="servInd"){
        return 28;
    }
    if(campo.name=="cliSector"){
        return 40;
    }

}


function filtrarSector(campo){
    
    //para filtrar cliSector
    if(campo.name=="cliTipoAct"){

  
        filtrarSrv(campo);

    }
    //para filtrar cliSubsector
    if(campo.name=="cliSector"){

        filtrarOcp(campo);

    }

}


function filtrarSrv(campo){

    var tipEmpSeleccionado=campo.value;

    var cliSector=document.getElementById("srvId");



    bloquearCampo(cliSector.id, "true");


    var inferior=obtenerInferiorTipEmp(tipEmpSeleccionado);

    var superior=obtenerSuperiorTipEmp(tipEmpSeleccionado);

   //VACIAMOS EL COMBO     
        borrarCombo(cliSector);

        //agregamos el option de seleccione
        cliSector.options[0]=new Option("-Seleccione-","-1");

        if(inferior!=-1 && superior!=-1){

            //llenamos el combo por si hubo un cambio de opcion
            llenarComboServicio(cliSector,inferior,superior);
            bloquearCampo(cliSector.id, "false");

            return;

        }

    cliSector.options[1]=new Option("-Seleccione-","2");

    //si se eligio no remunerado o sin ocupacion bloqueamos cliSector y cliSubsector
    cliSector.value="2";

    
   document.getElementById("ocpId").options[1]=new Option("-Seleccione-","2");


    $("#ocpId").attr("value","2");

bloquearCampo("ocpId", "true");


}



function obtenerInferiorTipEmp(tipEmpSeleccionado){

    if(tipEmpSeleccionado=="1"){
        return 35;
    }
    if(tipEmpSeleccionado=="2"){
        return 39;
    }
    //para cuando se elige sin ocupacion o no remunerado
    else{
        return -1;
    }
}

function obtenerSuperiorTipEmp(tipEmpSeleccionado){

    if(tipEmpSeleccionado=="1"){
        return 38;
    }
    if(tipEmpSeleccionado=="2"){
        return 40;
    }
    //para cuando se eleige no remunerado o sin ocupacion
    else{
        return -1;
    }

}

function borrarCombo(campo){

    var opciones=campo.options;

var index=0;

while(opciones[index]!=null){

    opciones[index]=null;
}

}




function filtrarOcp(campo){

    $.jStorage.set("cliSector",campo.value);

    var srvSeleccionado=$.jStorage.get("cliSector");


    var cliSubsector=document.getElementById("ocpId");

  
    bloquearCampo(cliSubsector.id, "true");


    var inferior=obtenerInferiorOcp(srvSeleccionado);

    var superior=obtenerSuperiorOcp(srvSeleccionado);

   //VACIAMOS EL COMBO
        borrarCombo(cliSubsector);

        //agregamos el option de seleccione
        cliSubsector.options[0]=new Option("-Seleccione-","-1");

        if(inferior!=-1 && superior!=-1){

            //llenamos el combo por si hubo un cambio de opcion
            llenarComboServicio(cliSubsector,inferior,superior);
            bloquearCampo(cliSubsector.id, "false");

            return;

        }


        cliSubsector.options[1]=new Option("-Seleccione-","2");

    //si se eligio no remunerado o sin ocupacion bloqueamos cliSector y cliSubsector
    cliSubsector.value="2";

    bloquearCampo("ocpId", "true");

    cliSubsector.attr("value","2");




}


function obtenerInferiorOcp(valorSrv){
    //agricola ganadera
    if(valorSrv==35){
        return 86;
    }
    //comercio
    if(valorSrv==36){
        return 60;
    }
    //servicios
    if(valorSrv==37){
        return 64;
    }
    //profesionista
    if(valorSrv==38){
        return 74;
    }
    //jefes y supervisores
    if(valorSrv==39){
        return 81;
    }
    //coordinadores
    if(valorSrv==40){
        return 84;
    }else{
        return -1;
    }

}



function obtenerSuperiorOcp(valorSrv){

    //agricola ganadera
    if(valorSrv==35){
        return 86;
    }
    //comercio
    if(valorSrv==36){
        return 63;
    }
    //servicios
    if(valorSrv==37){
        return 73;
    }
    //profesionista
    if(valorSrv==38){
        return 80;
    }
    //jefes y supervisores
    if(valorSrv==39){
        return 83;
    }
    //coordinadores
    if(valorSrv==40){
        return 85;
    }else{
        return -1;
    }
}



//CODPOS

function cargaCp(codpos){


        var valor=codpos.value;

    mandarCodPos(valor,codpos);
        
    
        return;

    

    

}


function mandarCodPos(valor,codpos){

      $.ajax({
                  url: '../ComboCodPos?cp='+valor,
                  dataType: 'json',
                  async:false,
                  success: function(json) {
                            ponerCp(json,codpos);

                  },
                  cache: true
               });



}

function ponerCp(json,codpos){

    if(json==null){
        pintarCampoNombre(codpos.id, "yellow");
        codpos.value="";

        $("#cliMun").attr("value","");

        $("#cliEdo").attr("value","");

        alert("Codigo Postal incorrecto");

        return;
    }

    pintarCampoNombre(codpos.id, "white");
    $("#cliMun").attr("value",json.mun);

     $("#cliEdo").attr("value",json.edo);

     $.jStorage.set("cliMun",json.mun);

     $.jStorage.set("cliEdo",json.edo);
}




//cargarClientes
function cargarClientesGol(){



    $.ajax({
                  url: "../AccionesServlet?accion=lista",
                  dataType: 'json',
                  async:true,
                  success: function(json) {
                                
                            llenaLista( json);

                  },
                  cache: false
               });


}


function llenaLista(json) {


   for(var index in json){

        var opt = $("#listaG").attr('options');

        opt[opt.length] = new Option(json[index].nombre, json[index].idGol);


    }
}


//CALCULO

function mandarCalcular(){
    $.ajax({
          url: '../AccionesServlet?accion=irCalcular',
          async: false,
          cache: false,
          success:function(){
              window.location="calcgol.jsp";
          }
         });

}



function calcularGol(campo) {
	var idG=campo.value.toString();

	if (idG == "") {
		alert("No ha seleccionado un registro para calcular.");
	} else {

            traerCalculo(idG)
            return;
	}
}


function traerCalculo(idG){

            $.ajax({
          url: '../AccionesServlet?accion=calcularGol&idG='+idG,
          async: false,
          cache: true,
          dataType:"json",
          success:function(json){
              pintarGrafica(json);
          }
         });


}

function pintarGrafica(json){


    var varsCalculo=["desGenero","desComportamiento","desArraigo","desAspectos","desGeneroClase1",
                    "desGeneroClase2","desCompTex1","desCompTex2","desArrTex1","desArrTex2","desAspTex1","noUtilizado","resultado"
                    ,"score","nombre"];


                    var indexLista=2;

     $.each(varsCalculo, function(index){

        $.jStorage.set(varsCalculo[index],json[indexLista]);

         indexLista++;

     })


window.location="calcgol.jsp";
     
}


function cargarJStorage(){

    if($.jStorage.get("desGenero")==null){
        return;
    }

    var variables=$.jStorage.index();

    $.each(variables,function(index){

        var elemento=$("#"+variables[index]);


        elemento.attr("value",$.jStorage.get(variables[index]));


        
    })

}


