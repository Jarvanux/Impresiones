//Div que contiene los datos para insertar. (No se usa formulario).
var divContactenos=$("div#contactenos");

var vistaContactenos={
    init:function(){
        $('#cargando').hide();
        divContactenos.find('#btnContactenos').on('click',vistaContactenos.enviarMensaje);
        divContactenos.find('#btnVerSMS').on('click',vistaContactenos.leerSMS);
    },
    enviarMensaje:function(){
        var url = 'insertarmensaje';
        var metodo = 'POST';
        var divMensaje=$('div#divMensaje');
        var objetoJSON;
        var mensaje={}; //Array de configuración que llevará los datos del objeto json para insertar.
        //Parametrizamos el array.
        mensaje.nombre=divContactenos.find('input#txtNombre').val();
        mensaje.correo=divContactenos.find('input#txtCorreo').val();
        mensaje.telefono=divContactenos.find('input#txtTelefono').val();
        mensaje.asunto=divContactenos.find('input#txtAsunto').val();
        mensaje.mensaje=divContactenos.find('#txtMensaje').val();
        //Convertimos el array en el objeto que pasaremos a la función global.
        //Se compone del nombre del objeto del servlet : y el array en formato json.
        objetoJSON = {'mensaje':JSON.stringify(mensaje)};
        controlPeticiones.insertar(url,metodo,objetoJSON,divMensaje);
    },
    
    leerSMS:function(cmbTipoImpresion){
        $.ajax({
            'url': 'tipoimpresion',
            success:function(data){
                cmbTipoImpresion.html('');
                cmbTipoImpresion.append(new Option('Seleccione', '-1'));
                var respuesta=JSON.parse(data);
                $.each(respuesta.datos, function(indice, valor ) {
                    cmbTipoImpresion.append(new Option(valor.tipoImpresion, valor.idTipoImpresion));
                });
            }
        });
    },
};
vistaContactenos.init();

