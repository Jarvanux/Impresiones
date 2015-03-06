/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var controlUsers = {
    init: function() {
        controlUsers.listarRoles($('#roles'));
        $('#roles').val(1);
        controlUsers.listarUsuarios($('#roles').val());
        controlUsers.eventos();
    },
    cargarRegistro: function() {
        var response = false;
        $.ajax({
            'url': 'paginas/administracionsSitio/registrarUsuario.html',
            'type': 'POST',
            'async': false,
            success: function(data) {
                $('#registroContent').html(data);
                $('#btnRegistrarU').show();
                $('#btnEditarU').hide();
                response = true;
            },
            error: function(data) {
                $('#registroContent').html('<h2>No se pudo cargar la página.</h2><br/><h3>Detalles del error:</h3><br/>' + data);
                response = false;
            }
        });
        return response;
    },
    confirmarBloqueo: function(id) {
        console.log('ID: ' + id + ' IDL:' + idUsuarioLogeado);
        if ((idUsuarioLogeado) == id) {
            console.log('Igual');
            $($('#confirmacion').find('span')).hide();
            $($('#confirmacion').find('span')[2]).show();
        }
        $('#confirmacion #idUsuario').val(id);
        $('#confirmacion').show();
    },
    confirmarActive: function(id) {
        $('#confirmarActive #idUsuario').val(id);
        $('#confirmarActive').show();
    },
    bloquearCancelado: function() {
        $('#confirmacion #idUsuario').val(0);
        $('#confirmacion').hide();
        $('#confirmarActive #idUsuario').val(0);
        $('#confirmarActive').hide();
    },
    bloquearConfirmado: function() {
        $('#confirmacion').hide();
        controlUsers.bloquearUsuario($('#confirmacion #idUsuario').val());
    },
    activarConfirmado: function() {
        $('#confirmarActive').hide();
        controlUsers.activarUsuario($('#confirmarActive #idUsuario').val());
    },
    activarUsuario: function(id) {
        $.ajax({
            'url': 'activarUsuario',
            'type': 'POST',
            'data': {'id': id},
            success: function(data) {
                var respuesta = JSON.parse(data);
                $('#notificaciones').hide();
                $('#notificaciones p').html(respuesta.mensaje);
                $('#notificaciones').show('slow');
                $('#confirmacion #idUsuario').val(0);
            }
        });
    },
    bloquearUsuario: function(id) {
        $.ajax({
            'url': 'bloquearUsuario',
            'type': 'POST',
            'data': {'id': id},
            success: function(data) {
                var respuesta = JSON.parse(data);
                $('#notificaciones').hide();
                $('#notificaciones p').html(respuesta.mensaje);
                $('#notificaciones').show('slow');
                $('#confirmacion #idUsuario').val(0);
            }
        });
    },
    viewUser: function(idUsuario) {
        if (controlUsers.cargarRegistro()) {
            $.ajax({
                'url': 'consultarUsuario',
                'type': 'POST',
                'async': false,
                'data': {'idUsuario': idUsuario},
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    if (respuesta.codigo > 0) {
                        $('#newUser div.header-form span').html('Detalles de usuario.');
                        $('#newUser div.body-form select#rol').val(respuesta.datos.idRol);
                        $('#newUser div.body-form #nombre').val(respuesta.datos.nombres);
                        $('#newUser div.body-form #apellido').val(respuesta.datos.apellidos);
                        $('#newUser div.body-form #email').val(respuesta.datos.email);
                        $('#newUser div.body-form #documento').val(respuesta.datos.cedula);
                        $('#newUser div.body-form #celular').val(respuesta.datos.celular);
                        $('#newUser div.body-form #telefono').val(respuesta.datos.telefono);
                        $('#newUser div.body-form #ciudad').val(respuesta.datos.ciudad);
                        $('#newUser div.body-form #zona').val(respuesta.datos.zona);
                        $('#newUser div.body-form input[type="text"]').prop('disabled', true);
                        $('#newUser div.body-form select').prop('disabled', true);
                        $('#btnRegistrarU').hide();
                        $('#btnEditarU').hide();
                    } else {
                        $('#notificaciones').hide();
                        $('#notificaciones p').html('Se ha producido un error al consultar el usuario.');
                        $('#notificaciones').show('slow');
                    }
                }
            });
        }
    },
    editUser: function(idUsuario) {
        if (controlUsers.cargarRegistro()) {
            $.ajax({
                'url': 'consultarUsuario',
                'type': 'POST',
                'async': false,
                'data': {'idUsuario': idUsuario},
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    if (respuesta.codigo > 0) {
                        $('#newUser div.header-form span').html('Edición de usuario.');
                        $('#newUser div.body-form select#rol').val(respuesta.datos.idRol);
                        $('#newUser div.body-form #idUsuarioField').val(respuesta.datos.idUsuario);
                        $('#newUser div.body-form #nombre').val(respuesta.datos.nombres);
                        $('#newUser div.body-form #apellido').val(respuesta.datos.apellidos);
                        $('#newUser div.body-form #email').val(respuesta.datos.email);
                        $('#newUser div.body-form #documento').val(respuesta.datos.cedula);
                        $('#newUser div.body-form #celular').val(respuesta.datos.celular);
                        $('#newUser div.body-form #telefono').val(respuesta.datos.telefono);
                        $('#newUser div.body-form #ciudad').val(respuesta.datos.ciudad);
                        $('#newUser div.body-form #zona').val(respuesta.datos.zona);
                        $('#newUser div.body-form input[type="text"]').prop('disabled', false);
                        $('#newUser div.body-form select').prop('disabled', false);
                        $('#btnRegistrarU').hide();
                        $('#btnEditarU').show();
                    } else {
                        $('#notificaciones').hide();
                        $('#notificaciones p').html('Se ha producido un error al consultar el usuario.');
                        $('#notificaciones').show('slow');
                    }
                }
            });
        }
    },
    eventos: function() {
        $('div#search #filtro').keydown(function() {
            controlUsers.filtrarUsuarios();
        });
        $('#roles').change(function() {
            controlUsers.listarUsuarios($('#roles').val());
        });
        $('#newUserBtn').click(function() {
            if (controlUsers.cargarRegistro()) {
                $('#newUser div.body-form input[type="text"]').prop('disabled', false);
                $('#newUser div.body-form select').prop('disabled', false);
            }
        });
        $('#filtro').focusin(function() {
            $(this).css('width', '195px');
            $('#search img').hide();
        });
        $('#filtro').focusout(function() {
            $(this).css('width', '170px');
            $('#search img').show();
        });
        $('#search img').click(function() {
            $('#filtro').focus();
        });
    },
    listarRoles: function(cmbx) {
        $.ajax({
            'url': 'listarRoles',
            'type': 'POST',
            'async': false,
            success: function(data) {
                var respuesta = JSON.parse(data);
                cmbx.html(''); //Reiniciamos y/o formateamos el contenido del elemento.                
                cmbx.append(new Option('Selecciona', '-1'));
                $.each(respuesta.datos, function(indice, valor) {
                    cmbx.append(new Option(valor.descripcion, valor.idRol));
                });
            }
        });
    },
    listarUsuarios: function(rol) {
        if ($('div#search #filtro').val().length == 0) {
            $.ajax({
                'url': 'listarUsuarios',
                'type': 'POST',
                'async': false,
                'data': {'rol': rol},
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    if (respuesta.codigo > 0) {
                        var trModelo = $('tr#trModel');
                        var usuarioConectado = false;
                        var activo = true;
                        $('table#table-list').find('.trData').remove();
                        $.each(respuesta.datos, function(indice, valor) {
                            $('table#table-list').append(trModelo.clone().attr('id', 'tr' + valor.idUsuario).show());
                            $('#tr' + valor.idUsuario).html('');
                            $.each(valor, function(ind, dat) {
                                if (ind.search('conectado') >= 0) {
                                    usuarioConectado = dat;
                                }
                                if (ind.search('activo') >= 0) {
                                    activo = dat;
                                }
                                if ((ind.search('nombre') >= 0) || (ind.search('apellido') >= 0)
                                        || (ind.search('email') >= 0) || (ind.search('cedula') >= 0)
                                        || (ind.search('celular') >= 0)
                                        ) {
                                    $('#tr' + valor.idUsuario).append(trModelo.find('td#tdModel').clone().attr('id', 'td' + ind).html(dat).show());
                                }
                            });
                            //Se agregan las opciones.
                            $('#tr' + valor.idUsuario).append(trModelo.find('td#estado').clone().attr('id', 'estado').show());
                            if (!usuarioConectado) {
                                $('#tr' + valor.idUsuario + ' td#estado div').css('background', '#ccc');
                                $('#tr' + valor.idUsuario + ' td#estado span').html('Desconectado.');
                            }
                            if (!activo) {
                                $('#tr' + valor.idUsuario + ' td#estado div').css('background', 'red');
                                $('#tr' + valor.idUsuario + ' td#estado span').html('Bloqueado.');
                            }
                            $('#tr' + valor.idUsuario + ' td#estado').css('width', '30px !important');
                            $('#tr' + valor.idUsuario).attr('class', 'trData');
                            $('#tr' + valor.idUsuario).append(trModelo.find('td#options').clone().attr('id', 'options' + valor.idUsuario).show());
                            $('#options' + valor.idUsuario + ' img#bloq').attr('onclick', 'controlUsers.confirmarBloqueo("' + valor.idUsuario + '")');
                            $('#options' + valor.idUsuario + ' img#active').attr('onclick', 'controlUsers.confirmarActive("' + valor.idUsuario + '")');
                            $('#options' + valor.idUsuario + ' img#edit').attr('onclick', 'controlUsers.editUser("' + valor.idUsuario + '")');
                            $('#options' + valor.idUsuario + ' img#view').attr('onclick', 'controlUsers.viewUser("' + valor.idUsuario + '")');
                            if (activo) {
                                $('#options' + valor.idUsuario + ' img#active').remove();
                            } else {
                                $('#options' + valor.idUsuario + ' img#bloq').remove();
                            }
                        });
                    } else {
                        $('table#table-list').find('.trData').remove();
                        $('table#table-list').append('<tr class="trData"><td colspan="7" >No hay registros.</td></tr>');
                    }
                }
            });            
        }
    },
    filtrarUsuarios: function() {
        console.log('Se inicia el filtro');
        $.ajax({
            'url': 'filtrarUsuarios',
            'type': 'POST',
            'async': false,
            'data': {'rol': $('select#roles').val(), 'palabra': $('div#search #filtro').val()},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    var trModelo = $('tr#trModel');
                    var usuarioConectado = false;
                    var activo = true;
                    $('table#table-list').find('.trData').remove();
                    $.each(respuesta.datos, function(indice, valor) {
                        $('table#table-list').append(trModelo.clone().attr('id', 'tr' + valor.idUsuario).show());
                        $('#tr' + valor.idUsuario).html('');
                        $.each(valor, function(ind, dat) {
                            if (ind.search('conectado') >= 0) {
                                usuarioConectado = dat;
                            }
                            if (ind.search('activo') >= 0) {
                                activo = dat;
                            }
                            if ((ind.search('nombre') >= 0) || (ind.search('apellido') >= 0)
                                    || (ind.search('email') >= 0) || (ind.search('cedula') >= 0)
                                    || (ind.search('celular') >= 0)
                                    ) {
                                $('#tr' + valor.idUsuario).append(trModelo.find('td#tdModel').clone().attr('id', 'td' + ind).html(dat).show());
                            }
                        });
                        //Se agregan las opciones.
                        $('#tr' + valor.idUsuario).append(trModelo.find('td#estado').clone().attr('id', 'estado').show());
                        if (!usuarioConectado) {
                            $('#tr' + valor.idUsuario + ' td#estado div').css('background', '#ccc');
                            $('#tr' + valor.idUsuario + ' td#estado span').html('Desconectado.');
                        }
                        if (!activo) {
                            $('#tr' + valor.idUsuario + ' td#estado div').css('background', 'red');
                            $('#tr' + valor.idUsuario + ' td#estado span').html('Bloqueado.');
                        }
                        $('#tr' + valor.idUsuario + ' td#estado').css('width', '30px !important');
                        $('#tr' + valor.idUsuario).attr('class', 'trData');
                        $('#tr' + valor.idUsuario).append(trModelo.find('td#options').clone().attr('id', 'options' + valor.idUsuario).show());
                        $('#options' + valor.idUsuario + ' img#bloq').attr('onclick', 'controlUsers.confirmarBloqueo("' + valor.idUsuario + '")');
                        $('#options' + valor.idUsuario + ' img#active').attr('onclick', 'controlUsers.confirmarActive("' + valor.idUsuario + '")');
                        $('#options' + valor.idUsuario + ' img#edit').attr('onclick', 'controlUsers.editUser("' + valor.idUsuario + '")');
                        $('#options' + valor.idUsuario + ' img#view').attr('onclick', 'controlUsers.viewUser("' + valor.idUsuario + '")');
                        if (activo) {
                            $('#options' + valor.idUsuario + ' img#active').remove();
                        } else {
                            $('#options' + valor.idUsuario + ' img#bloq').remove();
                        }
                    });
                } else {
                    $('table#table-list').find('.trData').remove();
                    $('table#table-list').append('<tr class="trData"><td colspan="7" >No hay registros.</td></tr>');
                }
            }
        });
    }
};
function verConectados() {
    controlUsers.listarUsuarios($('#roles').val());
}
$(document).ready(function() {
    controlUsers.init();
    setInterval(verConectados, 3000);
});
