//paginas del aplicativo
var paginas=["infogol","infogol_2","ingresosgol","historiagol","psicogol",

                "psicogol_2","burogol"];


//guarda los campos especiales(infogol,infogol_2,ingresosgol,historia
var especiales=["cliEdo","cliMun","cliEdad","cliGolGasOtr","gtoOtr"

                ,"relFamId","cliGolRelFamOtr","freRelId","relId","cliGolRelOtr","actUltId","cliGolActPor",

                "cliGolApoCon","cliGolParCon","apoConId","cliGolApoHij","cliGolParHij","apoHijId",
                "cliGolApoPad","cliGolParPad","apoPadId","cliGolApoOtr","cliGolParOtr","apoOtrId"
                ,"cliGolGasGas","gtoOtrHId",

                "cliGolDinExt","cliGolDesAdi","dinExtH","freDinId","pagRnt",
                "cliPagaRenta","solCre","cliGolAnoCre","cliGolMesCre",
                "tipCreId","cliGolPagAno","cliGolPagMes","cliGolTarCre","cliGolNumTar",
                "cliGolOtrCre","cliGolDesCre","cliGolCreHip","cliGolTarDeb","cliGolSegVid",
                "cliGolCtaChe","cliGolSegGmm","cliGolCrePer","cliGolCreAut"

]


/*
 * metodo que sirve para validar la pagina actual
 */
function validacionPagina(pagActual) {

    if(pagActual=="psicogol" || pagActual=="psicogol_2"){
        validarPsico(pagActual);
        return;
    }

        var bandera = true;

            var array=new Array();

            array=$(":input").toArray();

            $.each(array,function(i){

                

                if(!esEspecial(array[i])){

                        var validacion =validarCampo(array[i]);

                        if(bandera && !validacion){
                            bandera=false;
                        }
                }
                else{

                    var validada=validarEspecifico(array[i]);

                    if(bandera && !validada){
                        bandera=false;
                    }

                }
               
            })

        if (bandera == false) {

                  alert("Todos los campos son requeridos, complete y reintente.");

                return;
	}
        else {
           var siguiente=buscarSiguiente(pagActual);

           
               window.location=siguiente+".html";
           
        }
}


/*
 * manda llamar al servlet para guardar el cliente
 */
function mandarAGuardar(){

    mandarJsonAlServlet();

}

/**
 * busca la siguiente pagina para redireccionar
 */
function buscarSiguiente(pagActual){
   
    for(t=0;t<this.paginas.length;t++){

      if(this.paginas[t]==pagActual){
            return this.paginas[t+1];
        }
    }
    return null;
}


/*
 * hace casteo en los campos especiales de validacion
 */
function esEspecial(campo){

    for(k=0;k<this.especiales.length;k++){
       
        if(this.especiales[k]==campo.name){
            return true;
        }
    }
    
    return false;

}


/*
 * valida que un campo este lleno
 */
function validarCampo(campo){

    

    if(campo.value==""|| campo.value=="-1"){

        pintarCampoNombre(campo.id,"yellow" );

        return false;
    }else{
        pintarCampoNombre(campo.id,"white" );
        return true;
    }
}

/*
 * valida un los campos especiales
 */
function validarEspecifico(campo){

   
    var nombre=campo.name;
    var partido;


    if(nombre=="cliGolGasOtr"){
        
        return validacionInfogol(nombre);
    }
    
    else if(nombre=="relFamId" || nombre=="relId" || nombre=="actUltId"){
        
        return validacionInfogol2(nombre);
    }
    else if(nombre=="cliGolGasGas"){
        
        return validacionIngresos(nombre);
    }
    else if(nombre=="cliGolDinExt" || nombre=="pagRnt"
        || nombre=="solCre"){

        return validacionHistoria(nombre);
    }
    
    else if( nombre=="cliGolTarCre" || nombre=="cliGolOtrCre"||
        nombre=="cliGolTarCre" || nombre=="cliGolCreHip" || nombre=="cliGolTarDeb"
        || nombre=="cliGolSegVid" || nombre=="cliGolCtaChe" || nombre=="cliGolSegGmm"
        || nombre=="cliGolCrePer" || nombre=="cliGolOtrCre" || nombre=="cliGolCreAut"){


        return validarRadiosHistoria(nombre);
    }


   try{

       partido=nombre.toString().substr(0, 9);
       
   }catch(err){

   }

        if(partido=="cliGolApo"){

            return validacionIngresos(nombre);
        }

        return true;

}

//valida si un campo de texto tiene o no escrito (true si el campo esta lleno)
function textValidation(nombre){

    var valor=$.jStorage.get(nombre);


    if(valor!="null" && valor!=undefined && valor!=""){
        
        return true;
    }else{
        return false;
    }
}


//regresa el valor de la variable
function comboValidation(nombre ){

        var valor=$.jStorage.get(nombre);

        return valor;
}


/*
 **funcion para validar infogol
 */
function validacionInfogol(nombre){
    

    if(textValidation(nombre)){

            gto=document.getElementById("gtoOtrId");

            return validarCampo(gto);
      }
        pintarCampoNombre("gtoOtrId", "white");

    return true;
}

/*
 *funcion que valida infogol 2
 **/
function validacionInfogol2(nombre){

    var array=new Array();

    var valor=comboValidation(nombre);

    array.push(nombre);

    if(nombre=="relFamId"){

            if(valor==7){
                array.push("cliGolRelFamOtr","freRelId");
            }else if(valor==5){
                array.push("freRelId");
                pintarCampoNombre("cliGolRelFamOtr", "white");
                pintarCampoNombre("freRelId", "white");
            }else{
                
                pintarCampoNombre("cliGolRelFamOtr", "white");
                array.push("freRelId");
            }
    }
    else if(nombre=="relId"){

        if(valor==5){
                array.push("cliGolRelOtr");
            }else {
                pintarCampoNombre("cliGolRelFamOtr", "white");
            }
    }

    else if(nombre=="actUltId"){
        if(valor==6){

            array.push("cliGolActPor");
        }else{

            pintarCampoNombre("cliGolActPor", "white");

        }
    }

    validarArregloNombres(array);

    return true;
}




function validacionIngresos(nombre){

        var array=new Array();


    if(nombre=="cliGolGasGas" && textValidation(nombre)){


        array.push(nombre,"gtoOtrHId");
    }
        
    if(nombre=="cliGolApoCon" && comboValidation(nombre)=="1" ){
        
            array.push(nombre,"cliGolParCon","apoConId");
    }
    
    if(nombre=="cliGolApoHij" && comboValidation(nombre)=="1"){
            array.push(nombre,"cliGolParHij","apoHijId");
    }
    
    if(nombre=="cliGolApoPad" && comboValidation(nombre)=="1"){
            array.push(nombre,"cliGolParPad","apoPadId");
    }
    
    if(nombre=="cliGolApoOtr" && comboValidation(nombre)=="1"){
            array.push(nombre,"cliGolParOtr","apoOtrId");
    }
    
    return validarArregloNombres(array);

}




function validacionHistoria(nombre){

                var validacion=comboValidation(nombre);
                 var array=new Array();


    if((validacion==null || validacion==-1) && (nombre=="cliGolDinExt" || nombre=="pagRnt" || nombre=="solCre" )){
        pintarCampoNombre(nombre, "yellow");
        return false;
    }

    if(nombre=="cliGolDinExt" && validacion==1){
        
        array.push("cliGolDesAdi","dinExtH","freDinId");
    }
    if(nombre=="pagRnt" && validacion==1){
        array.push("cliPagaRenta");
    }
    if(nombre=="solCre" && validacion==1){
        array.push("cliGolAnoCre","cliGolMesCre");
        array.push("tipCreId","cliGolPagAno","cliGolPagMes");
    }
    



    return validarArregloNombres(array);
}


function validarRadiosHistoria(nombre){

        var valor=comboValidation(nombre);

        
        
        if(nombre=="cliGolTarCre" || nombre=="cliGolOtrCre"){
            
            if(valor==null){
                pintarRadios(nombre, "yellow");
                return false;
            }
                pintarRadios(nombre, "white");

            return validacionCreditos(nombre);
        }


    if(valor=="null" || valor==null || valor==undefined){
        pintarRadios(nombre, "yellow");
        return false;
    }else{
         pintarRadios(nombre,"white");
         return true;
    }

  

}



function validacionCreditos(nombre){

        var array=new Array();

    if(nombre=="cliGolTarCre" && comboValidation(nombre)==1){
        array.push("cliGolNumTar");
    }
    if(nombre=="cliGolOtrCre" && comboValidation(nombre)==1){
        array.push("cliGolDesCre");
    }

    return validarArregloNombres(array);

}


function validarPsico(pagActual){


    var bool=true;

    var radios=$(":radio").toArray();

    $.each(radios,function(){

        var valor=$.jStorage.get(this.name);

        if(valor==null){
            pintarRadios(this.name,"yellow");
            bool=false;
        }else{
            pintarRadios(this.name,"white");
        }

    })

    if(bool){
        var siguiente=buscarSiguiente(pagActual);
        window.location=siguiente+".html";
    }else{
        alert("Favor de llenar todos los campos");
        return;
    }

}


function pintarRadios(nombre , color){

   var radios= document.getElementsByName(nombre);

   $.each(radios,function(i){

       radios[i].style.background=color;

   })

}

/*
 * solo validacion de la pagina burogol.html
 */
function validacionBuro(){

    var buro=$(":radio").toArray();

    var camposAValidar=["cliBurMens","cliBurValr"];

    var camposDependientes=new Array();

    //agregamos campos a nuestra variable camposAValidar para despues validar todos
        camposDependientes.push("burAntId","burActId","burHisId","burUsoId","burPagId","cliBurCveMop");

        camposDependientes.push("burAbtId", "cliBurMaxAbi","cliBurMonAbi","cliBurLimAbi","cliBurSalAbi");

        camposDependientes.push("cliBurNumCer", "cliBurMaxCer","cliBurMonCer","cliBurLimCer","cliBurSalCer");

        camposDependientes.push("cliBurImpMop", "cliBurTpoMop","cliBurPagMop");

        
    var bool=validarCamposBuro(buro,camposAValidar , camposDependientes);

    if(bool){
        mandarAGuardar();
        //window.location="golindex.html";
    }

}


function validarCamposBuro(radioDeBuro,camposAValidar , camposDependientes){

    var bool=true;

    bool=validarArregloNombres(camposAValidar);

    
    //vemos si tiene buro
    if(radioDeBuro[0].checked==true){

        var validaDepe=validarArregloNombres(camposDependientes);

        if(validaDepe==false){
            bool=false;
        }

    }else{

            $.each(camposDependientes, function (i){

                pintarCampoNombre(camposDependientes[i], "white");

                bloquearCampo(camposDependientes[i], "true");

            })

    }

    return bool;
}


function validarArregloNombres(arreglo){

  

    var bandera=true;

    $.each(arreglo,function (i){

        var campo=$("#"+arreglo[i]);


        var valor=campo.attr("value");

        if(valor=="" || valor =="-1"){

            campo.css("background-Color", "yellow");
        //pintarCampoNombre(arreglo[i], "yellow");
          
            bandera=false;
        }else{

            campo.css("background-Color", "white");
            //pintarCampoNombre//(arreglo[i], "white");
        }

    })

    return bandera;
}

function pintarArregloNombres(array,color){
    
    $.each(array,function(i){
        
        pintarCampoNombre(array[i], color);
    })
    
}




/*
 * funcion que sirve para pintar algun campo
 */
function pintarCampoNombre(nombre,color){


    
    document.getElementById(nombre).style.background=color;
}

function bloquearCampo(nombre,valor){

    if(valor=="false"){
        $("#"+nombre).removeAttr("disabled");
        return;
    }

    $("#"+nombre).attr("disabled", valor);
}


/*
 * funcion para cuando aprietan el boton salir
 */
function salirGol() {
	if (confirm("Esta seguro de salir.")) {

		$.jStorage.flush();
                window.location="golindex.html"
	}
}


/*
 *funcion que revisa que siempre se comience
 */
function seguridad(){

    var seg=$.jStorage.get("seguridad");

    if(seg==undefined || seg=="undefined"){
        window.location=this.paginas[0]+".html";
    }
}