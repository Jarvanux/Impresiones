/* 
 Peticiones a los servles desde la pestaña y/o página ingresar.
 */


var peticionesIngresar = {
    ingresar: function(usuario, password) {
        $.ajax({
            'url': 'ingresar',
            'type': 'POST',
            'data': {'usuario': usuario, 'password': password},
            success: function(data, textStatus, jqXHR) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    if (respuesta.datos.idRol == 2) {
                        location.href = '/impresiones/panelusuario.html';
                    } else if (respuesta.datos.idRol == 1) {
                        location.href = '/impresiones/paneladministrador.html';
                    }
                } else if (respuesta.codigo == -1) {
                    $('div#mensajeL span#txtMensaje').html('Error en el servidor.');
                    $('div#mensajeL').slideDown();
                    $('p#smsReg').hide();
                    $('div#facebook').hide();
                } else {
                    $('div#mensajeL span#txtMensaje').html('Error en el usuario o la clave');
                    $('div#mensajeL').slideDown();
                    $('p#smsReg').hide();
                    $('div#facebook').hide();
                }
            }
        });
    }
}
