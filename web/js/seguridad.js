
//funcion de sesion
function verificarSesion(){
   
    if(!sessvars.login){
        //si es vacia es porque la sesion no ha sido inciada        
        window.location="../login.html";
    }
    else{
        //la sesion fue iniciada
    }

}

function acceso(campo_usu,campo_pas){

    var usuario = document.getElementById(campo_usu).value;
    var pas = document.getElementById(campo_pas).value;

    if(usuario==""){
        alert("Favor de ingresar un usuario");
        return;
    }
    if(pas==""){
        alert("Favor de ingresar la contraseña");
        return;
    }
    
    //accesamos al servlet
    $.ajax({
                type:"GET",
                url:"ProcesadorServlet?command=cargaUsu&usuario="+usuario+"&pass="+pas,
                data:{data:1},                
                success:acceder,
                onrequest:espera(),
                error:error
            });
}


function espera() {
    document.getElementById("tmp_usu").value=document.getElementById("user").value;
    document.getElementById("user").disabled=true;
     document.getElementById("pass").disabled=true;
    document.getElementById("entrar").disabled=true;
    document.getElementById("user").value = "BUSCANDO...";

}

function acceder(dataResp , status ,usuario ){

    document.getElementById("user").value=document.getElementById("tmp_usu").value;
    document.getElementById("user").disabled=false;

    document.getElementById("pass").disabled=false;
    document.getElementById("entrar").disabled=false;

    if(dataResp=="-1"){
        alert("Este usuario no esta dado de alta , vuelva a intentar");
        borraUsu();
        return;
    }
    if(dataResp=="0"){
        alert("La contraseña es incorrecta , vuelva a intentar");
        borraCont();
        return;
    }

    var partir = dataResp.split(",");
    var id = partir[0].split(":")[1];
    var tipo = partir[1].split(":")[1];

    setName(id, tipo);
}



function setName(id, tipo) {

    sessvars.login = {id: id, tipo: tipo}
    alert("Bienvenido "+document.getElementById("user").value);
    //borramos los cuadros del login
    document.getElementById("user").style.display='none';
    document.getElementById("pass").style.display='none';
    document.getElementById("text_usu").style.display='none';
    document.getElementById("entrar").style.display='none';

    //activamos los botones de las calculadoras
    document.getElementById("cliente").disabled=false;;
    document.getElementById("meta").disabled=false;
    document.getElementById("infonavit").disabled=false;
    document.getElementById("riesgo").disabled=false;
    document.getElementById("comportamiento").disabled=false;
    document.getElementById("deuda").disabled=false;
    document.getElementById("utilerias").disabled=false;
    document.getElementById("wizard").disabled=false;
   
}

function error(){
    alert("Error en la busqueda de usuario");    
}

function borraUsu(){

    borra("user", true);
}

function borraCont(){

    borra("pass", false);
}

function borra(focus, user){

    user? document.getElementById("user").value = "":null;
    document.getElementById("pass").value = "";
    document.getElementById(focus).focus();
}

function salir(){
    
     sessvars.login = null;
     window.location="../login.html";
}






