var controlAddUSer = {
    init: function() {
        controlUsers.listarRoles($('#rol'));
    },
    eventos: function() {
        $('#closeModal').click(function() {
            controlAddUSer.closeModal();
        });
    },
    closeModal: function() {
        $('#newUser').hide();
    },
    validar: function() {
        var correcto = 0;
        if ($('#rol').val() > 0) {
            correcto++;
            $('#rol').css('border', '1px solid #ccc');
        } else {
            $('#rol').css('border', '1px solid red');
        }
        if ($('#nombre').val().length > 2) {
            correcto++;
            $('#nombre').css('border', '1px solid #ccc');
        } else {
            $('#nombre').css('border', '1px solid red');
        }
        if ($('#apellido').val().length > 2) {
            $('#apellido').css('border', '1px solid #ccc');
            correcto++;
        } else {
            $('#apellido').css('border', '1px solid red');
        }
        if ($('#email').val().length > 2) {
            $('#email').css('border', '1px solid #ccc');
            correcto++;
        } else {
            $('#email').css('border', '1px solid red');
        }
        if ($('#documento').val().length > 2) {
            $('#documento').css('border', '1px solid #ccc');
            correcto++;
        } else {
            $('#documento').css('border', '1px solid red');
        }
        if ($('#celular').val().length >= 10 && $('#telefono').val().length >= 7) {
            $('#celular').css('border', '1px solid #ccc');
            $('#telefono').css('border', '1px solid #ccc');
            correcto++;
        } else {
            $('#celular').css('border', '1px solid red');
            $('#telefono').css('border', '1px solid red');
        }
        if ($('#ciudad').val() > 0) {
            $('#ciudad').css('border', '1px solid #ccc');
            correcto++;
        } else {
            $('#ciudad').css('border', '1px solid red');
        }
        if ($('#zona').val() > 0) {
            $('#zona').css('border', '1px solid #ccc');
            correcto++;
        } else {
            $('#zona').css('border', '1px solid red');
        }
        if (correcto == 8) {
            return true;
        } else {
            return false;
        }
    },
    registrar: function() {
        var response = controlAddUSer.validar();
        if (response) {
            $('#loader').show();
        }
        if (response) {
            var objetoJSON = {};
            objetoJSON.comprando = false;
            objetoJSON.nombres = $('#nombre').val();
            objetoJSON.apellidos = $('#apellido').val();
            objetoJSON.cedula = $('#documento').val();
            objetoJSON.email = $('#email').val();
            objetoJSON.telefono = $('#telefono').val();
            objetoJSON.celular = $('#celular').val();
            objetoJSON.ciudad = $('#ciudad').val();
            objetoJSON.zona = $('#zona').val();
            objetoJSON.idRol = $('#rol').val();
            objetoJSON.contrasena = $('#documento').val();
            objetoJSON.conectado = false;
            objetoJSON.activo = true;
            console.log(objetoJSON);
            objetoJSON = {'data': JSON.stringify(objetoJSON)};
            $.ajax({
                'url': 'guardarUsuario',
                'type': 'POST',
                'data': objetoJSON,
//                'async': false,
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    console.log(respuesta);
                    $('#notificaciones').hide();
                    $('#notificaciones p').html(respuesta.mensaje);
                    $('#notificaciones').show('slow');
                    $('#loader').hide();
                }
            });
        } else {
            $('#notificaciones').hide();
            $('#notificaciones p').html("Debes commpletar correctamente el formulario.");
            $('#notificaciones').show('slow');
        }
    },
    editar: function() {
        var response = controlAddUSer.validar();
        if (response) {
            $('#loader').show();
        }
        var idUsuario = $('#newUser div.body-form #idUsuarioField').val();
        if (response) {
            var objetoJSON = {};
            objetoJSON.idUsuario = idUsuario;
            objetoJSON.comprando = false;
            objetoJSON.nombres = $('#nombre').val();
            objetoJSON.apellidos = $('#apellido').val();
            objetoJSON.cedula = $('#documento').val();
            objetoJSON.email = $('#email').val();
            objetoJSON.telefono = $('#telefono').val();
            objetoJSON.celular = $('#celular').val();
            objetoJSON.ciudad = $('#ciudad').val();
            objetoJSON.zona = $('#zona').val();
            objetoJSON.idRol = $('#rol').val();
            objetoJSON.contrasena = $('#documento').val();
            objetoJSON.conectado = false;
            objetoJSON.activo = true;
            console.log(objetoJSON);
            objetoJSON = {'data': JSON.stringify(objetoJSON)};
            $.ajax({
                'url': 'editarUsuario',
                'type': 'POST',
                'data': objetoJSON,
//                'async': false,
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    console.log(respuesta);
                    $('#notificaciones').hide();
                    $('#notificaciones p').html(respuesta.mensaje);
                    $('#notificaciones').show('slow');
                    $('#loader').hide();
                }
            });
        } else {
            $('#notificaciones').hide();
            $('#notificaciones p').html("Debes commpletar correctamente el formulario.");
            $('#notificaciones').show('slow');
        }
    }
};

$(document).ready(function() {
    controlAddUSer.init();
    controlAddUSer.eventos();
});