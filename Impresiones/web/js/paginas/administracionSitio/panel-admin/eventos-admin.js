/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    eventosUsuario.eventos();
});

//Variables para saber en que panel se encuentra y si se puede refrescar.
var viendoMensajes = false;
var viendoPedidos = false;
//Fin variables;

var numTr = 0;

var eventosUsuario = {
    regresarfondoLis: function() {
        $('.menu li a#lnk-inicio').removeAttr('class');
        $('.menu li a#lnk-impresiones').removeAttr('class');
        $('.menu li a#lnk-conversaciones').removeAttr('class');
        $('.menu li a#lnk-configuracion').removeAttr('class');
        $('.menu li a#lnk-perfil').removeAttr('class');
        $('.menu li a#lnk-ayuda').removeAttr('class');
        $('.menu li a#lnk-contacto').removeAttr('class');
    },
    detallesImpresion: function() {
        $('div#info').slideDown();
    },
    detallesSMS: function(idTr) {
        $('div#detallesSMS #idSMS').val(idTr.substring(idTr.search('r') + 1));
        $('div#detallesSMS #correo').val($('#' + idTr + ' .colum-2').html());
        $('div#detallesSMS #nombreCliente').val($('#' + idTr + ' .colum-3').html());
        $('div#detallesSMS #mensajeEnviado').val($('#' + idTr + ' .colum-4 span.mensajeCompleto').html());
        $('div#detallesSMS #fecha').html($('#' + idTr + ' .colum-6').html());
        $('div#detallesSMS').slideDown();
    },
    filtrarMensajesDejados: function() {
        $('#cargador').show();
        var codigoLeido = $('#cmbxEstadoLeido').val();
        var fecha = "";
        var fecha2 = "";
        var desde = "";
        if ($('#fecha-filtro').val().length > 0) {
            fecha = $('#fecha-filtro').val();
            fecha2 = $('#fecha-filtro').val();
            fecha2 = fecha2.substring(0, 8) + (+fecha2.substring(8) + 1);
        }
        if ($('#fecha-filtro-desde').val().length > 0) {
            desde = $('#fecha-filtro-desde').val();
        }

        $.ajax({
            'url': 'listaMensajesBuzon',
            'type': 'POST',
            'data': {'codigoLeido': codigoLeido, 'fecha1': fecha, 'fecha2': fecha2, 'desde': desde},
            success: function(data) {
                var respuesta = JSON.parse(data);
                var leido = 0;
                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    var modelo = $('table#tablaContent #trModelo');
                    $('table#tablaContent').html('');
                    $('table#tablaContent').append(modelo.clone().attr({'id': 'trModelo'}).hide());
                    $.each(respuesta.datos, function(indice, valor) {
                        $('table#tablaContent').append($('table#tablaContent #trModelo').clone().attr({'id': 'tr' + valor.idMensaje}).show());
                        $('#tr' + valor.idMensaje + ' .colum-1').html((indice + 1));
                        $('#tr' + valor.idMensaje + ' .colum-2').html(valor.correo);
                        $('#tr' + valor.idMensaje + ' .colum-3').html(valor.nombre + ' ' + valor.apellido);
                        $('#tr' + valor.idMensaje + ' .colum-4 span.mensajeResumido').html(valor.mensaje.substring(0, 20) + '...');
                        $('#tr' + valor.idMensaje + ' .colum-4 span.mensajeCompleto').html(valor.mensaje);
                        if (valor.idMensaje % 2 == 0) {
                            $('#tr' + valor.idMensaje).attr('class', 'data1');
                        } else {
                            $('#tr' + valor.idMensaje).attr('class', 'data2');
                        }
                        if (valor.leido == 0) {
                            leido = "No";
                            $('#tr' + valor.idMensaje).attr('class', 'noLeido');
                        } else if (valor.leido == 1) {
                            leido = "Si";
                        }
                        $('#tr' + valor.idMensaje + ' .colum-5').html(leido);
                        $('#tr' + valor.idMensaje + ' .colum-6').html(controlPeticiones.formatearHora(valor.fecha));
                        $('#tr' + valor.idMensaje).attr({'onclick': 'eventosUsuario.detallesSMS("tr' + valor.idMensaje + '")'});
                    });
                } else {
                    var modelo = $('table#tablaContent #trModelo');
                    $('table#tablaContent').html('');
                    $('table#tablaContent').append(modelo.clone().attr({'id': 'trModelo'}).hide());
                    $('table#tablaContent').append($('table#tablaContent #trModelo').clone().attr({'id': 'trInfo'}).show());
                    $('#trInfo').html('<td colspan="5" style="width: 100%; text-align: left; padding: 0px 0px 0px 30px; color: #000">No hay registros...</td>');
                }
                $('#cargador').hide();
            }
        });
    },
    filtrarMensajesDejados2: function() {
        $('#cargador').show();
        var codigoLeido = -1;
        var fecha = "";
        var fecha2 = "";
        var desde = "";
        $.ajax({
            'url': 'listaMensajesBuzon',
            'type': 'POST',
            'data': {'codigoLeido': codigoLeido, 'fecha1': fecha, 'fecha2': fecha2, 'desde': desde},
            success: function(data) {
                var respuesta = JSON.parse(data);
                var leido = 0;
                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    var modelo = $('table#tablaContent #trModelo');
                    $('table#tablaContent').html('');
                    $('table#tablaContent').append(modelo.clone().attr({'id': 'trModelo'}).hide());
                    $.each(respuesta.datos, function(indice, valor) {
                        $('table#tablaContent').append($('table#tablaContent #trModelo').clone().attr({'id': 'tr' + valor.idMensaje}).show());
                        $('#tr' + valor.idMensaje + ' .colum-1').html((indice + 1));
                        $('#tr' + valor.idMensaje + ' .colum-2').html(valor.correo);
                        $('#tr' + valor.idMensaje + ' .colum-3').html(valor.nombre + ' ' + valor.apellido);
                        $('#tr' + valor.idMensaje + ' .colum-4 span.mensajeResumido').html(valor.mensaje.substring(0, 20) + '...');
                        $('#tr' + valor.idMensaje + ' .colum-4 span.mensajeCompleto').html(valor.mensaje);
                        if (valor.idMensaje % 2 == 0) {
                            $('#tr' + valor.idMensaje).attr('class', 'data1');
                        } else {
                            $('#tr' + valor.idMensaje).attr('class', 'data2');
                        }
                        if (valor.leido == 0) {
                            leido = "No";
                            $('#tr' + valor.idMensaje).attr('class', 'noLeido');
                        } else if (valor.leido == 1) {
                            leido = "Si";
                        }
                        $('#tr' + valor.idMensaje + ' .colum-5').html(leido);
                        $('#tr' + valor.idMensaje + ' .colum-6').html(controlPeticiones.formatearHora(valor.fecha));
                        $('#tr' + valor.idMensaje).attr({'onclick': 'eventosUsuario.detallesSMS("tr' + valor.idMensaje + '")'});
                    });
                } else {
                    var modelo = $('table#tablaContent #trModelo');
                    $('table#tablaContent').html('');
                    $('table#tablaContent').append(modelo.clone().attr({'id': 'trModelo'}).hide());
                    $('table#tablaContent').append($('table#tablaContent #trModelo').clone().attr({'id': 'trInfo'}).show());
                    $('#trInfo').html('<td colspan="5" style="width: 100%; text-align: left; padding: 0px 0px 0px 30px; color: #000">No hay registros...</td>');
                }
                $('#cargador').hide();
            }
        });
    },
    marcarComoLeido: function() {
        $('div#detallesSMS').hide();
        $.ajax({
            'url': 'actualizarSMSLeido',
            'type': 'POST',
            'data': {'idSMS': $('div#detallesSMS #idSMS').val(), 'idUsuario': idUsuarioLogeado},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                eventosUsuario.filtrarMensajesDejados();
            }
        });
    },
    cerrarDetallesSMS: function() {
        $('div#detallesSMS').hide();
    },
    cambiarEstado: function(estado) {
        $.ajax({
            'url': 'cambiarEstadoAdmin',
            'type': 'POST',
            'data': {'estado': estado, 'idUsuario': idUsuarioLogeado},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                $('#noti-body p').html(respuesta.mensaje);
                $('#notificaciones').hide();
                $('#notificaciones').show('explode');
            }
        });
    },
    eventos: function() {
        inicio.cargar('paginas/resumen-impresion/impresiones.html');

        eventosUsuario.regresarfondoLis();
        $('.menu li a#lnk-inicio').attr({'class': 'fondo-rojo'});

        $('#mensajesDejados').click(function() {
            inicio.cargar('paginas/administrador/mensajesBuzon.html');
            eventosUsuario.filtrarMensajesDejados2();
            viendoMensajes = true;
        });
        $('#mensajesDejados2').click(function() {
            inicio.cargar('paginas/administrador/mensajesBuzon.html');
            eventosUsuario.filtrarMensajesDejados2();
            viendoMensajes = true;
        });

        $('a#lnk-inicio').click(function() {
            inicio.cargar('paginas/resumen-impresion/impresiones.html');
            eventosUsuario.regresarfondoLis();
            $('.menu li a#lnk-inicio').attr({'class': 'fondo-rojo'});
        });
        $('#lnk-impresiones').click(function() {
            inicio.cargar('paginas/resumen-impresion/impresiones.html');
            eventosUsuario.regresarfondoLis();
            $('.menu li a#lnk-impresiones').attr({'class': 'fondo-rojo'});
        });
        $('#lnk-conversaciones').click(function() {
            eventosUsuario.regresarfondoLis();
            $('.menu li a#lnk-conversaciones').attr({'class': 'fondo-rojo'});
        });
        $('#lnk-contacto').click(function() {
            $('#info div#cuerpo p').html(' <br/>' +
                    '<span style="display:block; margin-bottom: 10px;"><b>Teléfono:</b> 321-249-1941xx</span>' +
                    '<span style="display: block; margin-bottom: 10px;"><b>Dirección:</b>' +
                    ' Calle San-Fason carrera 22</span>' +
                    '<span style="display: block; margin-bottom: 10px;"><b>Correo:</b>' +
                    ' impresionesadomicilio.rempe.com</span>'
                    );
            $('#info').slideDown(500);
        });
        $('#lnk-telefono').click(function() {
            $('#info div#cuerpo p').html(' <br/>' +
                    '<b>Teléfono:</b> 321-249-1941');
            $('#info').slideDown(500);
        });
        $('#lnk-oficinas').click(function() {
            $('#info div#cuerpo p').html(' <br/>' +
                    '<b>Dirección:</b> Calle san-fason carrera 22');
            $('#info').slideDown(500);
        });
        $('#imgCerrar').click(function() {
            $('#info').hide();
        });
        $('#lnk-perfil').click(function() {
            inicio.cargar('paginas/administracionsSitio/panel-usuario/perfil-usuario.html');
        });
        $('#lnk-cs').click(function() {
            $(location).attr('href', '/impresiones/login.html');
        });
        $('#lnk-salir').click(function() {
            controlUsuario.cerrarSesion();
        });
        $('#lnk-perfil').click(function() {
            eventosUsuario.regresarfondoLis();
            $('.menu li a#lnk-configuracion').attr({'class': 'fondo-rojo'});
            $('.menu li a#lnk-perfil').attr({'class': 'fondo-rojo'});
            controlUsuario.editarDatosUsuario();
        });
        $(window).resize(function() {
            controlUsuario.init();
        });
        $('#btnActualizar').click(function() {
        });
    }
};