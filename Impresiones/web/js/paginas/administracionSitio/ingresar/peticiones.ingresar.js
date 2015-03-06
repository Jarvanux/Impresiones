/* 
 Peticiones a los servles desde la pestaña y/o página ingresar.
 */


var peticionesIngresar = {
    ingresar: function(usuario, password) {
        password = md5(password);
        console.log(password);
        $('#mensajeL').addClass('info');
        $('#mensajeL span').html('Conectando...');
        $('#facebook').hide();
        $('#smsReg').hide();
        $('#mensajeL').show();
        $.ajax({
            'url': 'ingresar',
            'type': 'POST',
            'data': {'usuario': usuario, 'password': password},
            success: function(data, textStatus, jqXHR) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    if (posicionActual == 1 || posFormulario == 2) {
                        controlPeticiones.guardarImpresionLaser(respuesta.datos.idUsuario);
                    }
                    if (respuesta.datos.activo) {
                        if (respuesta.datos.idRol == 2) {
                            if (posicionActual == 1 || posFormulario == 2) {
                                location.href = '/impresiones/panelusuario.html?facturar';
                            } else {
                                location.href = '/impresiones/panelusuario.html';
                            }
                        } else if (respuesta.datos.idRol == 1) {
                            location.href = '/impresiones/paneloperador.html';
                        } else if (respuesta.datos.idRol == 3) {
                            location.href = '/impresiones/paneladministrador.html';
                        }
                    } else {
                        $('#mensajeL').removeClass('info');
                        $('div#mensajeL span#txtMensaje').html('Sin permisos para ingresar.');
                        $('div#mensajeL').slideDown();
                        $('p#smsReg').hide();
                        $('div#facebook').hide();
                    }
                } else if (respuesta.codigo < 0) {
                    $('#mensajeL').removeClass('info');
                    $('div#mensajeL span#txtMensaje').html('Error en el servidor.');
                    $('div#mensajeL').slideDown();
                    $('p#smsReg').hide();
                    $('div#facebook').hide();
                } else {
                    $('#mensajeL').removeClass('info');
                    $('div#mensajeL span#txtMensaje').html('Error en el usuario o la clave');
                    $('div#mensajeL').slideDown();
                    $('p#smsReg').hide();
                    $('div#facebook').hide();
                }
            }
        });
    }
};
