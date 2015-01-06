/* 
 * Refrescar chat usuario cliente.
 */
$(document).ready(function() {
    refreshChatVisitante.scrollModoLectura();
    setInterval(refrescarChat, 2000);
    $.ajaxSetup({cache: false});



    function refrescarChat() {
        if (controlPeticiones.consultarUsuarioLogeado()) {
            idUsuario = respuestaUsuario.datos.idUsuario;
        } else {
            idUsuario = ipVisitante;
        }
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
        var accion = 0;
        var texto = divContent.find('#sms').val();
        if (texto.length > 1) {
            accion = 1;
        } else {
            accion = 0;
        }
        $.ajax({
            'url': 'leerchat',
            'data': {'accion': accion, 'idConversacion': idConversacionChat, 'idUsuarioContacto': idUsuarioContacto, 'idUsuario': idUsuario},
            cache: false,
            success: function(data) {
                respuesta = JSON.parse(data);
//                console.log(respuesta);
//                console.log(respuesta);
                divContent.find('div.chat-body').html('');
//                console.dir(respuesta);
                if (respuesta.codigo > 0) {
//                    console.info('se realizó el cambio');
                    numSms = 0;
                    console.log(respuesta);
                    $.each(respuesta.datos[0], function(indice, valor) {
//                        console.dir(respuesta);
                        if (ipVisitante == respuesta.datos[0][indice].idUsuarioOrigen) {
                            divContent.find('div.chat-body').append($('#tiposSms div.sms-emisor').clone().attr('id', 'sms' + indice).show());
                        } else {
                            divContent.find('div.chat-body').append($('#tiposSms div.sms-reseptor').clone().attr('id', 'sms' + indice).show());
                            $('#sms' + indice + ' div.name-usuario').html(usuarioOpuesto.nombres);
                        }
                        $('#sms' + indice + ' div.content-sms').html(refreshChatVisitante.buscarEmoticones(valor.mensaje));
                        $('#sms' + indice + ' div.fecha').html(controlChat.formatearHora(valor.fecha));
                    });
                    divContent.find('div.chat-body').append($('#tiposSms div.estado-acciones').clone().attr('id', 'estado').show());
                    console.log(respuesta.datos[1]);
                    if (respuesta.datos[1] > 0) {
                        console.log('Él está escibiendo');
                        divContent.find('div.chat-body div.estado-acciones span').html('Él está escribiendo...').show();
                        divContent.find('div.chat-body div.estado-acciones span').css({'margin': 'auto'});
                    } else {
                        console.log('Él no está escibiendo');
                        divContent.find('div.chat-body div.estado-acciones span').hide();
                    }
//                    divContent.find('.mensajes-reci').show();
//                    divContent.find('.mensajes-reci').css({'background':'#48AE5C'});
//                    console.info('sdfdsf');
                    refreshChatVisitante.posScroll();
                }
            }
        });
    },
    scrollModoLectura: function() {

    },
    posScroll: function() {
        var id = divContent.find('div')[8].id;

        var elemento = document.getElementById(id);

        if (!leyendoChat) {
            elemento.scrollTop = (elemento.scrollHeight - elemento.clientHeight) + 100;
        }
        leyendoChat = true;
    },
};