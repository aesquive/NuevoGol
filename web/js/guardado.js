/**
 *Consultores Quants
 *
 *Hecho por Alberto Emmanuel Esquivel Vega
 *
 *Libreria util para guardar datos a travez de varios htmls sin mandar llamar a un servlet para ello (jStorage)
 *
 *La libreria necesita de Jquery y JStorage para su correcto uso.
 *
 *
 *
 *
 *
 */


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








//ACTUALIZAR PAGINA  (para modificar)

/*
 **metodo que llena los campos de la pagina
 */
function actualizarPagina(){


        var nombres=new Array();

      nombres=$(":input").toArray()



    $.each(nombres, function(i,val ){

        tipo=val.type;

          if(tipo=="text" ||tipo=="select-one"){

              actualizarTextBox(val);
          }

          if(tipo=="textarea"){


              actualizarTextArea(val);
          }
          if(tipo=="radio" || tipo=="checkbox"){

              actualizarRadios(val);
          }


    })
}

/*
 *function que llena los textbox
 */
function actualizarTextBox(val){

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
function actualizarTextArea(val){

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
function actualizarRadios(val){

        var valor= $.jStorage.get(val.name,"") ;

        if(valor){

             val.checked = $.jStorage.get(val.name,"") == val.value;

        }

}







/*
 * metodo para incializar nuestro json , en caso de que sea modificar se debe cambiar el metodo obtenerJsonDelServlet
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


}




/*
 * metodo para obtener el json desde el servlet  (para cuando se ocupa el modificar)
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





/**
 *metodo que manda los datos al servlet para ser procesados
 */
function mandarJsonAlServlet(){

        //arreglo con todas las variables
        var variables=pasarAJson();

        //hacemos todo lo del servlet
        guardarServlet(variables);


}


/**
 *manda las variables al servlet
 */
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




/**
 * devuelve un arreglo de tal mandera que
 *
 * json[var]=valor
 * json[var2]=valor2
 */
function pasarAJson(){

    var regreso=new Object();

    var variables=new Array();

    variables=$.jStorage.index();

    for(t=0;t<variables.length;t++){

         regreso[variables[t]]=$.jStorage.get(variables[t]);


    }

    return regreso;
}
