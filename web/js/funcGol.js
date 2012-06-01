
function imprimeLista(campo){

    alert("imprimiendo");

    for(var t=0;t<campo.length;t++){

        if(campo[t].selected){

            alert(campo[t].value)


        }
        
    }

}

function activarGastosOtrosInfo() {

        var otr=$("#cliGolGasOtr");
        var cmb=$("#gtoOtrId");

    if(otr.attr("value").length==0){
        bloquearCampo("gtoOtrId", "true");
        cmb.attr("value","-1");

    }else{
        
        bloquearCampo("gtoOtrId", "false");
    }

}




//CHECAR EL NUMERO DE LOS COMBOS

function relFamiliaOtra(){

    var religionFamilia=$("#relFamId");

    var frecuencia=$("#freRelId");

    var otra=$("#cliGolRelFamOtr");

    if(religionFamilia.attr("value")==7){

        bloquearCampo("cliGolRelFamOtr", "false");
        bloquearCampo("freRelId", "false");
      

    }
    else if(religionFamilia.attr("value")==5){

        bloquearCampo("cliGolRelFamOtr", "true");
        bloquearCampo("freRelId", "true");
        otra.attr("value","");
        frecuencia.attr("value","6");
    }
    else{
        bloquearCampo("cliGolRelFamOtr", "true");
        otra.attr("value","");
        bloquearCampo("freRelId", "false");
      
    }
}




function relOtra(){

        var rel=$("#relId");

        var otra=$("#cliGolRelOtr");

        if(rel.attr("value")=="5"){
           
            bloquearCampo("cliGolRelOtr", "false")

        }
        else{
            otra.attr("value","");
            bloquearCampo("cliGolRelOtr", "true");
        }

}






function habilitarEstudio(grado , anios){

    var nivel=$("#"+grado);

    var tiempo=$("#"+anios);

    bloquearCampo(anios, "false");

    if(nivel.attr("value")=="-1" ) {
        tiempo.attr("value","");
        return;
    }

    else if(nivel.attr("value")=="9" ){

        bloquearCampo(anios, "true");
       tiempo.attr("value","0");
       return;

    }
    
}


function habilitarNoTrabajo(){

        var actividadPrincipal=$("#actUltId");
        var noTrabaja=$("#cliGolActPor");

    if(actividadPrincipal.attr("value")==6){

         bloquearCampo("cliGolActPor", "false");
    }
    else{
         bloquearCampo("cliGolActPor", "true");
         noTrabaja.attr("value","");
    }
}



function cargaInfogol2(){


  
    relOtra();
    relFamiliaOtra();
    habilitarEstudio('nivEstId','cliGolEstCli');
    habilitarEstudio('nivEstPapId', 'cliGolEstPad');
    habilitarEstudio('nivEstMamId','cliGolEstMad');
    habilitarNoTrabajo();

    

}




function validaDendientes() {

        validaLimitado("cliGolNumDep", 0, 15);
}



function ingMensual(campo) {
	validaLimitado(campo, 500, 50000)
}



function checkIng(campo, campo1, campo2) {

        var cmp=$("#"+campo);
        var cmp1=$("#"+campo1);
        var cmp2=$("#"+campo2);

        

	if (cmp.attr("checked")== true) {

             bloquearCampo(campo1, "false");
            bloquearCampo(campo2, "false");
	    cmp1.focus();
            return;
	} else {
            cmp1.attr("value","");
            cmp2.attr("value","-1");
            pintarCampoNombre(campo1, "white");
            pintarCampoNombre(campo2, "white");
            bloquearCampo(campo1, "true");
            bloquearCampo(campo2, "true");
        }
}


function checarIngresos(){

    checkIng('cliGolApoCon','cliGolParCon','apoConId');
    checkIng('cliGolApoHij','cliGolParHij','apoHijId');
    checkIng('cliGolApoPad','cliGolParPad','apoPadId');
    checkIng('cliGolApoOtr','cliGolParOtr','apoOtrId');
    activarGastosOtros();
}


function activarGastosOtros(){

    var otroGasto=$("#cliGolGasGas");

    var comboOtroGasto=$("#gtoOtrHId")

  
    if(otroGasto.attr("value").length==0){
        comboOtroGasto.attr("value","-1");
       bloquearCampo("gtoOtrHId", "true");

    }else{
        bloquearCampo("gtoOtrHId", "false");
    }
}



// fin pagina3

// pagina 4

function solicitudCred(){


 var campos=["tipCreId","cliGolPagAno","cliGolPagMes","cliGolAnoCre","cliGolMesCre"];

 var diferenciador=$("#solCre").attr("value");


    if(diferenciador=="1"){
        
        bloquearArreglo(campos, "false");
    }
    else{
        bloquearArreglo(campos, "true");
        pintarArregloNombres(campos, "white");
    }
   
    
}


function bloquearArreglo(arreglo,valor){
    $.each(arreglo , function (i){

        bloquearCampo(arreglo[i], valor);

    })
}


function dineroExtraChange(){

        var comboDinExt=$("#cliGolDinExt");

    

        var campos=["cliGolDinExtDes","cliGolImpExt","freDinId"];

	if(comboDinExt.attr("value")=="1"){
		
                 bloquearCampo(campos[0], "false");
                bloquearCampo(campos[1], "false");
                bloquearCampo(campos[2], "false");

                
        }
                else{

                bloquearCampo(campos[0], "true");
                bloquearCampo(campos[1], "true");
                bloquearCampo(campos[2], "true");
                    $("#cliGolDinExtDes").attr("value","");
                    $("#cliGolImpExt").attr("value","");
                    $("#freDinId").attr("value","-1");

                    pintarArregloNombres(campos, "white");
               
	}
}



function validaInputHistoria(campo) {

    
    if(campo=="cliGolPagAno"){
        validaLimitado(campo,0,40);
        return;
    }
    else if(campo=="cliGolPagMes"){
        validaLimitado(campo,0,11);
        return;
    }
   if(campo=="cliGolNumTar"){
        validaLimitado(campo,0,20);
        return;
    }
	
}


function validaLimitado(campo , liminf , limsup){

        var cmp=$("#"+campo);

        var valor=parseInt(cmp.attr("value"));

      

        if(valor<liminf || valor>limsup){
                alert("El valor debe ser entre "+liminf+" y "+limsup);
                pintarCampoNombre(campo, "yellow");
                cmp.attr("value","");
                cmp.focus();
                return;
        }
            
            pintarCampoNombre(campo, "white");
}



function activarTarjeta(check,campo) {
       
        var tarjeta=$("#"+check);
        var cuantas=$("#"+campo);


        if(tarjeta.attr("checked")==true){
            bloquearCampo(campo, "false");
            cuantas.focus();
            cuantas.attr("value","1");
            return;
        }
        else{

            pintarCampoNombre(campo, "white")
            bloquearCampo(campo, "true");
            if(campo=="cliGolDesCre"){
                cuantas.attr("value","");
            }else{
              cuantas.attr("value","0");
            
            }
            return;
        }
}


/*
 * el campo dependiete depende del independiente
 * valores=valores que hacen que el combo independiente se active
 *
 */
function pagaRent(){
        var combo=$("#pagRnt");

        if(combo.attr("value")=="1"){
            bloquearCampo("cliGolImpRen", "false");

        }
        else{
            $("#cliGolImpRen").attr("value","");
            bloquearCampo("cliGolImpRen", "true");
            pintarCampoNombre("cliGolImpRen", "white");
        }
}



function muestraAnios(){

 
        var anios=$("#cliGolAnoCre").attr('options');

        var inicio=1947;

        var actual=new Date();
        
        while(inicio<actual.getFullYear()){
          
            anios[anios.length]=new Option(inicio,inicio);
            inicio++;
        }

    
}

// fin pagina 4

// pagina 7
function selBuroReg() {

        var buro=$(":radio").toArray();

         var camposDependientes=new Array();

    //agregamos campos a nuestra variable camposAValidar para despues validar todos
        camposDependientes.push("burAntId","burActId","burHisId","burUsoId","burPagId","cliBurCveMop");

        camposDependientes.push("burAbtId", "cliBurMaxAbi","cliBurMonAbi","cliBurLimAbi","cliBurSalAbi");

        camposDependientes.push("cliBurNumCer", "cliBurMaxCer","cliBurMonCer","cliBurLimCer","cliBurSalCer");

        camposDependientes.push("cliBurImpMop", "cliBurTpoMop","cliBurPagMop");

        $.each(camposDependientes, function (i){

                
            bloquearCampo(camposDependientes[i], !buro[0].checked==true);

                if(!buro[0].checked==true){
                    pintarCampoNombre(camposDependientes[i], "white");
                    cambiarValores(camposDependientes);
                }

        })

}


function cambiarValores(arreglo){

    $.each(arreglo,function(i){

        var campo=$("#"+arreglo[i]);

        if(campo.attr("type")=="text"){
            campo.attr("value","");
        }else{
            campo.attr("value","-1");
        }

    })

}


function buroRangos(campo) {
	validaLimitado(campo, 0, 99);
}


function valVivienda(){

        validaLimitado("cliBurValr", 100000, 3000000);
}


function valMensu(){
	
        validaLimitado("cliBurMens", 500, 50000);
}
// fin pagina 7


function rentaGto(campo) {
        validaLimitado(campo, 500, 50000);
}



function salirGol1(){
	if (confirm("Esta seguro de salir.")) {
		location.assign('golindex.jsp');
	}
}


function valIngMens(){
    var mens=parseFloat($("#cliBurMens").attr("value"));

    var ing=parseFloat($.jStorage.get("cliGolIng"));


    if(mens>ing){

        alert("La mensualidad no puede ser mayor al ingreso");

        $("#cliBurMens").attr("value","");

    }
}



function validEdad(campo){

	var edad=parseInt($.jStorage.get("cliEdad"));


        var cmp=parseInt($("#"+campo).attr("value"));

        
        if(cmp>edad){
            alert("Verificar con respecto a la edad");
            $("#"+campo).attr("value","");

            pintarCampoNombre(campo, "yellow");

            return;
        }

        pintarCampoNombre(campo, "white");
}




//validar nivel de estudios

function valNest(campo){

    var niv=parseInt($("#"+campo).attr("value"));

    if(niv>20){
        alert("El nivel de estudios no puede ser mayor a 20")

        parseInt($("#"+campo).attr("value",""));
    }

}



