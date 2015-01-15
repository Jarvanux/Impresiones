/* 
 * Refrescar chat usuario cliente.
 */
var numData = 0;
var refresh = 0;
var indicePeticion = 0;
var abc = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
    "r", "s", "t", "u", "v", "w", "x", "y", "z"];

var namePeticion = "";

var accion = 0;
var texto = null;
$(document).ready(function() {
    refreshChatVisitante.scrollModoLectura();
    setInterval(refrescarChat, 1200);
    $.ajaxSetup({cache: false});
    namePeticion = indicePeticion;
    indicePeticion++;



    function refrescarChat() {
//        if (controlPeticiones.consultarUsuarioLogeado()) {
//            if (idUsuario > 0) {
////                idUsuario = respuestaUsuario.datos.idUsuario;
//            }
//        } else {
//            idUsuario = ipVisitante;
//        }
        if (maxChat == 1) {
            if (idUsuario != 0) {
                var id = divContent.find('div')[8].id;
                var elemento = document.getElementById(id);

                var posScroll = elemento.scrollHeight - elemento.clientHeight;

                if (elemento.scrollTop < (posScroll)) {
                    leyendoChat = true;
                } else {
                    leyendoChat = false;
                }
                refreshChatVisitante.leerChat();
            }
//            console.log('Se refresco el chat.');
        } else {
//            console.log('No se refresco el chat...');
        }
    }
});

numSms = 0;
posScroll = 0;
divContent = null;
consultaEstadoRealizada = 0;
var indice = 0;
var refreshChatVisitante = {
    init: function() {
    },
    buscarEtiquetas: function(cadena) {
        cadena = cadena.replace(/\</g, '');
        cadena = cadena.replace(/\>/g, '');
        return cadena;
    },
    buscarEmoticones: function(cadena) {
        cadena = refreshChatVisitante.buscarEtiquetas(cadena);
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
    leerChat: function() {
//        console.info('Se está refrescando el chat');
        texto = divContent.find('#sms').val();
        if (texto.length > 1) {
            accion = 1;
        } else {
            accion = 0;
        }
        console.info('Sin caché2');
        $.ajax({
            url: 'leerchat',
            type: 'POST',
            data: {'accion': accion, 'idConversacion': idConversacionChat, 'idUsuarioContacto': idUsuarioContacto, 'idUsuario': idUsuario},
            cache: false,
            success: function(data) {
                respuesta = JSON.parse(data);
                console.log(respuesta);
//                console.log(respuesta);                
//                console.dir(respuesta);
                if (respuesta.codigo > 0) {
//                    console.info('se realizó el cambio');                    
                    numSms = 0;
//                    console.log(respuesta);

                    if (refresh == 0) {
                        divContent.find('div.chat-body').html('');
                        $.each(respuesta.datos[0], function(indice, valor) {
//                        console.dir(respuesta);                 
                            if (idUsuario == respuesta.datos[0][indice].idUsuarioOrigen) {
                                divContent.find('div.chat-body').append($('#tiposSms div.sms-emisor').clone().attr('id', 'sms' + indice).show());
                            } else {
                                divContent.find('div.chat-body').append($('#tiposSms div.sms-reseptor').clone().attr('id', 'sms' + indice).show());
                                $('#sms' + indice + ' div.name-usuario').html(usuarioOpuesto.nombres);
                            }
                            $('#sms' + indice + ' div.content-sms').html(refreshChatVisitante.buscarEmoticones(valor.mensaje));
                            $('#sms' + indice + ' div.fecha').html(controlChat.formatearHora(valor.fecha));
//                        });                            
                            divContent.find('div.chat-body div#estado').remove();
                            divContent.find('div.chat-body').append($('#tiposSms div.estado-acciones').clone().attr('id', 'estado').show());
                            console.log(respuesta.datos[1]);
                        });
                        refresh = 1;
                        numData = respuesta.datos[0].length;
                    } else {
                        if (respuesta.datos[4] == 2) {
                            divContent.find('div.chat-header .estado').css({'background': '#00FF00'});
                            divContent.find('div.chat-header span').html(usuarioOpuesto.nombres);
                        } else if (respuesta.datos[4] == 3) {
                            divContent.find('div.chat-header .estado').css({'background': '#595959'});
                            divContent.find('div.chat-header span').html('Desconectado');
                        } else if (respuesta.datos[4] == 4) {
                            divContent.find('div.chat-header .estado').css({'background': '#F41313'});
                            divContent.find('div.chat-header span').html('Ocupado');
                        } else if (respuesta.datos[4] == 5) {
                            divContent.find('div.chat-header .estado').css({'background': '#CBB91B'});
                            divContent.find('div.chat-header span').html('Ausente');
                        }
                        if (respuesta.datos[3]) {
//                            divContent.find('div.chat-header .estado').css({'background': '#00FF00'});
                        } else {
                            divContent.find('div.chat-header .estado').css({'background': '#595959'});
                            divContent.find('div.chat-header span').html('Desconectado');
                        }

                        if (respuesta.datos[0].length != numData) {
                            numData = respuesta.datos[0].length;
//                        $.each(respuesta.datos[0], function(indice, valor) {
//                        console.dir(respuesta);
                            var indice = numData - 1;
                            var valor = respuesta.datos[0][indice];

                            if (idUsuario == respuesta.datos[0][indice].idUsuarioOrigen) {
//                                divContent.find('div.chat-body').append($('#tiposSms div.sms-emisor').clone().attr('id', 'sms' + indice).show());
                            } else {
                                divContent.find('div.chat-body').append($('#tiposSms div.sms-reseptor').clone().attr('id', 'sms' + indice).show());
                                $('#sms' + indice + ' div.name-usuario').html(usuarioOpuesto.nombres);
                            }
                            $('#sms' + indice + ' div.content-sms').html(refreshChatVisitante.buscarEmoticones(valor.mensaje));
                            $('#sms' + indice + ' div.fecha').html(controlChat.formatearHora(valor.fecha));
//                        });                            
                            divContent.find('div.chat-body div#estado').remove();
                            divContent.find('div.chat-body').append($('#tiposSms div.estado-acciones').clone().attr('id', 'estado').show());
                            divContent.find('div.chat-body div.estado-acciones span').hide();
                        } else {

                        }

                        for (var i = respuesta.datos[0].length - 1; i >= 0; i--) {
                            if (respuesta.datos[0][i].idUsuarioOrigen != idUsuario) {
                                if (respuesta.datos[0][i].accion == 1) {
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
                    refreshChatVisitante.posScroll();
                }
            }
        });
    },
    scrollModoLectura: function() {

    },
    posScroll: function() {
//        var id = divContent.find('div')[8].id;
        var id = divContent.find('div')[8].id;
        var elemento = document.getElementById(id);
        if (!leyendoChat) {
            elemento.scrollTop = (elemento.scrollHeight - elemento.clientHeight) + 100;
        }
        leyendoChat = true;
    },
};

