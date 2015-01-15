/* 
 ACTUALIZAR PERFIL
 Lo he puesto separado por que en verdad me envolate un poco ;)
 */

var actualizarPerfil = {
    init: function() {
        actualizarPerfil.consultarUsuarioLogeado();
    },
    actualizarDatos: function() {
        $('#btnActualizar').click(function() {
            var objetoJSON = {};
            objetoJSON.nombres = $('#bodyForm').find('#nombres').val();
            objetoJSON.apellidos = $('#bodyForm').find('#apellidos').val();
            objetoJSON.email = $('#bodyForm').find('#email').val();
            objetoJSON.cedula = $('#bodyForm').find('#cedula').val();
            objetoJSON.celular = $('#bodyForm').find('#telefono').val();
            objetoJSON.direccion = $('#bodyForm').find('#direccion').val();
            objetoJSON.ciudad = $('#bodyForm').find('#ciudad').val();
            objetoJSON.telefono = $('#bodyForm').find('#telefono').val();
            objetoJSON.zona = $('#bodyForm').find('#zona').val();
            objetoJSON.zona = $('#bodyForm').find('#fechaNac').val();
            $('#cargador').show();
            $.ajax({
                'url': 'actualizaPerfil',
                'type': 'POST',
                'data': {'data': JSON.stringify(objetoJSON)},
                success: function(data) {
                    var respuesta = JSON.parse(data);
//                    console.log(respuesta);
                    $('#cargador').hide();
                }
            });
        });
    },
    consultarUsuarioLogeado: function() {
        $.ajax({
            'url': 'consultarUsuarioLogeado',
            'type': 'POST',
            'async': false,
            success: function(data) {
                var respuesta = JSON.parse(data);
                var lugarPagina = location.href;
                var posSearch = lugarPagina.search("/impresiones/");
                lugarPagina = lugarPagina.substring(posSearch);
                if (respuesta.codigo > 0) {
                    if (respuesta.datos != null) {
                        idUsuarioLogeado = respuesta.datos.idUsuario;
                        if (lugarPagina == '/impresiones/') {
                            if (respuesta.datos.idRol == 2) {
                                location.href = '/impresiones/panelusuario.html';
                            } else if (respuesta.datos.idRol == 1) {
                                location.href = '/impresiones/paneladministrador.html';
                            }
                        }
                    }
                } else {
                    if (lugarPagina == '/impresiones/login.html' || lugarPagina.search('paneladministrador.html') > -1 || lugarPagina.search('panelusuario.html') > -1) {
                        location.href = '/impresiones/login.html';
                    }
                }
            }
        });
    },
};