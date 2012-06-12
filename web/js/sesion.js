function sacarAtributoUrl( name ){
    var regexS = "[\\?&]"+name+"=([^&#]*)";
    var regex = new RegExp ( regexS );
    var tmpURL = window.location.href;
    var results = regex.exec( tmpURL );
    if( results == null )
        return"";
    else
        return results[1];
}


function cargarSesion(){
    
    var id=sacarAtributoUrl("id");
    llamadaGuardadoServlet(id);
}


function llamadaGuardadoServlet(id){
    
              
        $.ajax({
          url: '../NuevoGolServlet?comando=login&idUsuario='+id,
          async: false,
          cache: false,
          success: function(text){
              if(text=="t"){
                    return;
              }else{
                      
                    window.location="http://50.112.124.249:8080/Calculadoras";
    
              }
          }
         });
    
}

/**
 * 
 */