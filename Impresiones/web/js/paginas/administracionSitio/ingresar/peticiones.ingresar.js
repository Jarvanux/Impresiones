/* 
 Peticiones a los servles desde la pestaña y/o página ingresar.
 */


var peticionesIngresar = {
    ingresar: function(usuario, password) {    
        $.ajax({
            'url' : 'ingresar',
            'type' : 'POST',
            'data' : {'usuario':usuario, 'password':password},
            success: function(data, textStatus, jqXHR) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if(respuesta.codigo > 0){
                    location.href = '/impresiones/panelusuario.html';
                }else{
                    $('div#mensajeL span#txtMensaje').html('Error en el usuario o la clave');
                    $('div#mensajeL').slideDown();
                }
            }
        });
    }
}
