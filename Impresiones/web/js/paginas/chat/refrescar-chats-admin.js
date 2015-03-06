/* xxxxxxxxxxxxxxxx
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    setInterval(refrescar, 2000);
//    setInterval(poscrollRelease, 1000);
    refrescaClientes.refrescar();
    refrescaClientes.eventos();
    $('div.all-chats').css('margin-right', '200px');
});
var contScroll = 2;
var chat1 = null;
var chat2 = null;
var chat3 = null;
var accion = 0;
var inicioLogin = false;
var x = 0;
var audio = new Audio();
audio.src = "sounds/chatNew2.wav";

//function poscrollRelease() {
//    refrescaClientes.posScroll('x');
//}

function refrescar() {
//    console.info('llls');
    refrescaClientes.refrescar();
//    refrescaClientes.posScroll();
}

var numVisitantes = -1;
var numSms = -1;
//var idUsuario = 0;
var refrescaClientes = {
    init: function() {
    },
    eventos: function() {
        $('div.all-chats').css('margin-right', '200px');
        $('#notificaciones').hide();
    },
    buscarEtiquetas: function(cadena) {
        cadena = cadena.replace(/\</g, '');
        cadena = cadena.replace(/\>/g, '');
        return cadena;
    },
    buscarEmoticones: function(cadena) {
        cadena = refrescaClientes.buscarEtiquetas(cadena);
        cadena = cadena.replace(/\:[)]/g, '<img src=\"img/chat/emoticones/sonrie.png\" style=\"width: 17px\"/>');
        cadena = cadena.replace(/\:D/g, '<img src=\"img/chat/emoticones/reir.png\" style=\"width: 17px\"/>');
        cadena = cadena.replace(/\:d/g, '<img src=\"img/chat/emoticones/reir.png\" style=\"width: 17px\"/>');
        cadena = cadena.replace(/\:3/g, '<img src=\"img/chat/emoticones/sonriente.png\" style=\"width: 17px\"/>');
        cadena = cadena.replace(/\;[)]/g, '<img src=\"img/chat/emoticones/pick-ojo.png\" style=\"width: 17px\"/>');
        cadena = cadena.replace(/\(y[)]/g, '<img src=\"img/chat/emoticones/like.png\" style=\"width: 17px\"/>');
        cadena = cadena.replace(/\(Y[)]/g, '<img src=\"img/chat/emoticones/like.png\" style=\"width: 17px\"/>');
        cadena = cadena.replace(/\:P/g, '<img src=\"img/chat/emoticones/muecas.png\" style=\"width: 17px\"/>');
        cadena = cadena.replace(/\:p/g, '<img src=\"img/chat/emoticones/muecas.png\" style=\"width: 17px\"/>');
        return cadena;
    },
    refrescar: function() {

        $('div.all-chats').css('margin-right', '200px');
        if (divContent != null) {
            var id = divContent.find('div').selector.substring(1).replace(' div', '');
            var elemento = document.getElementById('cuerpo-' + id);
            var posScroll = elemento.scrollHeight - elemento.clientHeight;
            if (elemento.scrollTop < (posScroll)) {
                $('#' + id).find('span.leyendoChat').html("true");
            } else {
                $('#' + id).find('span.leyendoChat').html("false");
            }
            texto = divContent.find('#sms').val();
            idUsuarioContacto = 0;
            if (texto.length > 1) {
                accion = 1;
//                console.log('Este s');
            } else {
                accion = 0;
            }
        }
        $.ajax({
            'url': 'refresPanel',
            'data': {'accion': accion, 'idUsuarioContacto': x, 'idUsuario': idUsuarioLogeado},
            success: function(data) {
                respuesta = JSON.parse(data);
//                console.log(respuesta);
                $('div#visitantes div#body').html('');
                if (respuesta.datos[2] == 2) {
                    $('div#visitantes div#header div#estado').css({'background': '#00FF00'});
                    $('div#visitantes div#header span#title').html('Tús clientes');
                } else if (respuesta.datos[2] == 3) {
                    $('div#visitantes div#header div#estado').css({'background': '#595959'});
                    $('div#visitantes div#header span#title').html('Estás Desconectado');
                } else if (respuesta.datos[2] == 4) {
                    $('div#visitantes div#header div#estado').css({'background': '#F41313'});
                    $('div#visitantes div#header span#title').html('Estás Ocupado');
                } else if (respuesta.datos[2] == 5) {
                    $('div#visitantes div#header div#estado').css({'background': '#CBB91B'});
                    $('div#visitantes div#header span#title').html('Estás Ausente');
                }

                if (!respuesta.datos[3]) {
                    controlUsuario.consultarUsuarioLogeado();
                }
                if (respuesta.datos[0].datos[0].length > numVisitantes && numVisitantes > -1) {
                    $('#noti-body p').html('Tienes un nuevo cliente en el chat: <b>' + respuesta.datos[0].datos[0][respuesta.datos[0].datos[0].length - 1].nombres + ' ' + respuesta.datos[0].datos[0][respuesta.datos[0].datos[0].length - 1].apellidos + '</b>');
                    $('#notificaciones').hide();
                    $('#notificaciones').show('explode');
                }
                numVisitantes = respuesta.datos[0].datos[0].length;
                if (numVisitantes > 0) {
                    $.each(respuesta.datos[0].datos[0], function(indice, valor) {
//                        console.log(valor.idUsuario);
                        $('div#visitantes div#body').append($('#tiposSms div.reg-visitante').clone().attr('id', 'visitante' + valor.idUsuario).show());
                        $('#visitante' + valor.idUsuario).attr({'onclick': 'controlChat.chatNuevo("-' + valor.idUsuario + '")'});
                        controlChat.crearPanelChat('-' + valor.idUsuario);
                        divContent = $('#chat-' + valor.idUsuario);
//                        refrescaClientes.posScroll('x');
                        $('#visitante' + valor.idUsuario + ' span.resumen-sms').html(divContent.find('.chatsLeidos').html());
                        if (parseInt($('#visitante' + valor.idUsuario + ' span.resumen-sms').html()) > 0) {
                            $('#visitante' + valor.idUsuario + ' div.info-sms').slideDown();
                        } else {
                            $('#visitante' + valor.idUsuario + ' div.info-sms').hide();
                        }
//                        console.log(divContent);
                        if (parseInt(divContent.find('div.chat-header div.chatsLeidos').html()) < 0) {
                            divContent.find('div.chat-body').html('');
                            $.each(respuesta.datos[1][indice], function(indice2, valor2) {
                                if (idUsuarioLogeado == respuesta.datos[1][indice][indice2].idUsuarioOrigen) {
                                    divContent.find('div.chat-body').append($('#tiposSms div.sms-emisor').clone().attr('id', 'sms' + indice2).show());
                                    $('#sms' + indice2 + ' div.name-usuario').html('Tú');
                                    $('#sms' + indice2 + ' div.content-sms').html(refrescaClientes.buscarEmoticones(respuesta.datos[1][indice][indice2].mensaje));
                                } else {
                                    divContent.find('div.chat-body').append($('#tiposSms div.sms-reseptor').clone().attr('id', 'sms' + indice2).show());
                                    $('#sms' + indice2 + ' div.name-usuario').attr('id', 'usuarioCliente');
                                    $('#sms' + indice2 + ' div.content-sms').html(refrescaClientes.buscarEmoticones(respuesta.datos[1][indice][indice2].mensaje));
                                }
                                $('#sms' + indice + ' div.fecha').html(controlPeticiones.formatearHora(respuesta.datos[1][indice][indice2].fecha) + 2);
                                divContent.find('div.chat-body div#estado').remove();
                                divContent.find('div.chat-body').append($('#tiposSms div.estado-acciones').clone().attr('id', 'estado').show());
                                divContent.find('div.chat-header div.chatsLeidos').html(0);
                            });
                        } else {
                            divContent = $('#chat-' + valor.idUsuario);
                            if ((respuesta.datos[1].length) > 0) {
                                if ((respuesta.datos[1][indice].length) > divContent.find('div.chat-header div.chatsLeidos').html()) {
                                    var indice2 = ((respuesta.datos[1][indice].length) - 1);
                                    if (idUsuarioLogeado == respuesta.datos[1][indice][indice2].idUsuarioOrigen) {
                                    } else {
                                        var numData = (divContent.find('div.chat-body div.sms-emisor').length) + (divContent.find('div.chat-body div.sms-reseptor').length)
                                        if ((respuesta.datos[1][indice].length) > numData) {
                                            var chatHabilitado = divContent.find('span#leidos').attr('class') == "true";
                                            if (!chatHabilitado) {
                                                divContent.find('.chatsLeidos').html((parseInt(divContent.find('.chatsLeidos').html())) + 1);
                                                $('#visitante' + valor.idUsuario + ' span.resumen-sms').html(divContent.find('.chatsLeidos').html());
                                                $('#visitante' + valor.idUsuario).css('background', '#D1FFCC');
                                                $('#visitante' + valor.idUsuario + ' div.info-sms').slideDown();
                                                audio.currentTime = 0;
                                                audio.play();
                                            } else {
                                                divContent.find('.chatsLeidos').html(0);
                                            }
                                            divContent.find('div.chat-body').append($('#tiposSms div.sms-reseptor').clone().attr('id', 'sms' + indice2).show());
                                            $('#sms' + indice2 + ' div.name-usuario').attr('id', 'usuarioCliente');
                                            $('#sms' + indice2 + ' div.content-sms').html(refrescaClientes.buscarEmoticones(respuesta.datos[1][indice][indice2].mensaje));
                                            $('#sms' + indice2 + ' div.fecha').html(controlPeticiones.formatearHora(respuesta.datos[1][indice][indice2].fecha));
                                            divContent.find('div.chat-body div#estado').remove();
                                            divContent.find('div.chat-body').append($('#tiposSms div.estado-acciones').clone().attr('id', 'estado').show());
                                        }

                                        for (var i = respuesta.datos[1][indice].length - 1; i >= 0; i--) {
                                            if (respuesta.datos[1][indice][i].idUsuarioOrigen != idUsuarioLogeado) {
                                                if (respuesta.datos[1][indice][i].accion == 1) {
//                                                    console.log(respuesta.datos[1][indice][i]);
                                                    divContent.find('div.chat-body div.estado-acciones span').html('Escribiendo...').show();
                                                    divContent.find('div.chat-body div.estado-acciones span').css({'margin': 'auto'});
                                                } else {
                                                    divContent.find('div.chat-body div.estado-acciones span').hide();
                                                }
                                                break;
                                                break;
                                            } else {
                                                divContent.find('div.chat-body div.estado-acciones span').hide();
                                            }
                                        }
                                    }
                                }

                                var id = divContent.find('div').selector.substring(1).replace(' div', '');
                                var elemento = document.getElementById('cuerpo-' + id);
                                var posScroll = elemento.scrollHeight - elemento.clientHeight;
                                if (divContent.find('span.maxPrimeraVez').html() == "true") {
                                    elemento.scrollTop = (elemento.scrollHeight - elemento.clientHeight) + 100;
                                    divContent.find('span.maxPrimeraVez').html("false");
                                }

                                if ($('#' + id).find('span.leyendoChat').html() == "false") {
                                    elemento.scrollTop = (elemento.scrollHeight - elemento.clientHeight) + 100;
                                }
                                x = divContent.attr('id').substring(divContent.attr('id').search('-') + 1);
                            }
                        }
                        leyendoChat = false;

                        $('#visitante' + valor.idUsuario + ' span.name').html((valor.nombres + ' ' + valor.apellidos).substring(0, 20));
                        $('#chat-' + valor.idUsuario + ' span.text-header').html((valor.nombres + ' ' + valor.apellidos).substring(0, 20));
                        $('#chat-' + valor.idUsuario + ' div.chat-body div.sms-reseptor #usuarioCliente').html(valor.nombres);
                    });
                } else {
                    $('div#visitantes div#body').append($('#tiposSms div.reg-visitante').clone().attr('id', 'visitante' + 0).show());
                    $('#visitante' + 0).html('<span class="infoClientes">No hay clientes...</span>');
                }
                $('span.numMensajes').html(respuesta.datos[0].datos[1]);
                if ($('span.numMensajes').html() > numSms && numSms > -1) {
                    $('#noti-body p').html('Revisa el panel de mensajes, ha llegado uno nuevo.');
                    $('#notificaciones').hide();
                    $('#notificaciones').show('explode');
                }

                if ($('span.numMensajes').html() > 0 && !inicioLogin) {
                    if ($('span.numMensajes').html() == 1) {
                        $('#noti-body p').html('Revisa el panel de mensajes, ha llegado uno nuevo.');
                    } else {
                        $('#noti-body p').html('Revisa el panel de mensajes, tienes algunos nuevos.');
                    }
                    $('#notificaciones').hide();
                    $('#notificaciones').show('explode');
                    inicioLogin = true;
                }

                numSms = respuesta.datos[0].datos[1];
            }
        });
//        console.log(divContent);

//        if ((respuesta.datos[1].length) > 0) {

//        }
//        console.log(x);
        if (divContent != null) {
            texto = divContent.find('#sms').val();
            idUsuarioContacto = 0;
            if (texto.length > 1) {
                accion = 1;
            } else {
                accion = 0;
            }
        }
    },
    posScroll: function(x) {
        var id = divContent.find('div').selector.substring(1).replace(' div', '');
        var elemento = document.getElementById('cuerpo-' + id);
        if (((divContent).find('span.leyendoChat').html()) == "false") {
            elemento.scrollTop = (elemento.scrollHeight - elemento.clientHeight) + 100;
        }
        divContent.find('span.leyendoChat').html("true");
    },
}