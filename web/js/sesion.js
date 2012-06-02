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
    if(id!=""){
        
        $.ajax({
          url: '../NuevoGolServlet?comando=login&idUsuario='+id,
          async: false,
          cache: false
         });
    }
        
    else{
        window.location="http://localhost:8084/Calculadoras";
    }
}