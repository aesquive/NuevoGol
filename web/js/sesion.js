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
        $.jStorage.set("idUsuario",id);
        window.location="./gol/golindex.html";
        
    }else{
        window.location="http://localhost:8084/Calculadoras";
    }
}