/* 
 Peticiones a los servles desde la pestaña y/o página ingresar.
 */


var peticionesIngresar = {
    ingresar: function(usuario, password) {        
        $.ajax({
            'url': 'ingresar',
            'type': 'POST',
            'data': {
                'usuario': usuario,
                'password': password
            },
            success: function(data) {
                var respuesta = JSON.parse(data);                
                if (respuesta.codigo > 0) {                    
                    $('div#mensajeL').slideUp(500);
                    location.href = 'panelusuario.html'
                } else {                    
                    $('div#mensajeL span').html('Usuario o clave incorrecta!');
                    $('div#mensajeL').slideDown(500);
                }
            }
        });
    }    
};