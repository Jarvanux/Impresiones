/* 
 * 
 */

var chatsAbiertos = 1;
var contChat = 1;
divContent = null;
leyendoChat = false;
var maxChat = 0;
var idConversacionChat = 0;
var idUsuarioContacto = 0;
var idUsuario = 0;
var usuarioOpuesto = null;
var idChatText = null;
var idMensajeEnviado = 0;
var correoVisitante = "";
var nombreVisitante = "";
$(document).ready(function() {
    controlChat.init();
});
var controlChat = {
    init: function() {
        controlChat.chatNuevo();
        $('#imgCerrar').click(function() {
            $('#info').hide('explode');
        });
        $('#aceptar').click(function() {
            $('#info').hide('explode');
        });
        $('#enviar').click(function() {
            if ($('#nombreContacto').val().length > 6 && $('#mensajeText').val().length > 20 && $('#correoE').val().length > 6) {
                $('#infoEnvio').show();
                $('#enviar').hide();
                $.ajax({
                    'url': 'mensajeDeBuzon',
                    'type': 'POST',
                    'data': {'nombre': $('#nombreContacto').val(), 'correoE': $('#correoE').val(), 'mensaje': $('#mensajeText').val()},
                    success: function(data) {
                        data = JSON.parse(data);
                        console.log(data);
                        $('#infoEnvio img').attr('src', 'img/icons/global/fine-img.png');
                        $('#infoEnvio span').html(data.mensaje);
                        $('#aceptar').show();
                    }
                });
            } else {
                $('#infoEnvio img').attr('src', 'img/icons/global/error-img.png');
                $('#infoEnvio span').html('Completa el formulario.');
                $('#infoEnvio').show();
            }
        });
    },
    showInfo: function() {
        $('div#contentModal #aceptar').css({'margin-right': '20px'});
        $('#nombreContacto').val(nombreVisitante);
        $('#correoE').val(correoVisitante);
        $('#info').slideDown();
    },
    prepararChat2: function() {
        calculaIP2();
    },
    clickPress: function(chat) {
        idChatText = chat;
    },
    keyPress: function(event) {
        divContent = $('#' + idChatText);
//        alert(idChatText+event.which);
        if (event.which == 13) {
            controlChat.prepararChat2();
        }
    },
    leerChat: function() {

    },
    chatNuevo: function() {
        var marginRight = 0;
        var tamanioVentana = $(window).width();
        var tamanioReqByChats = 330 * chatsAbiertos;
        if (tamanioReqByChats < tamanioVentana) {
            if (chatsAbiertos == 1) {
                marginRight = 0;
            } else {
                marginRight = (330 * chatsAbiertos) - 330;
            }
            //Si hay espacio en la ventana simplemente se agrega visible en la misma.
            $('div.all-chats').append($('#chatmodelo').clone().attr('id', 'chat' + contChat).show());
            $('#chat' + contChat + ' div.chat-body').attr({'id': 'cuerpo-chat' + contChat});
            $('#chat' + contChat + ' div.btn-max').attr({'onclick': "controlChat.clickMax('chat" + contChat + "')"});
            $('#chat' + contChat + ' div.btn-min').attr({'onclick': "controlChat.clickMin('chat" + contChat + "')"});
            $('#chat' + contChat + ' div.chat-footer .text-sms').attr({'onclick': "controlChat.clickPress('chat" + contChat + "')"});
            divContent = $('#chat' + contChat);
            $('div#chat1 div.chat-header div.estado').hide();
            $('div#chat1 div.chat-header div#mensajes-recibidos').hide();
            $('div#chat1 div.chat-header span').show();
            $('div#chat1 div.chat-header span').css({'font-size': '14px'});
            $('div#chat1 div.chat-header div.btn-max').show();
            $('div#chat1 div.chat-header span').html('¿Dudas? - Chatea con nosotros');
        } else {
            alert('No hay espacio en la ventana. Ventana: ' + tamanioVentana + ' Chats: ' + tamanioReqByChats);
            $('#chat' + contChat + ' div.btn-min').click();
            chatsAbiertos--;
        }
        chatsAbiertos++;
        contChat++;
//        console.log(chatsAbiertos);        
    },
    clickClose: function(event) {
        var idChat = event.path[3];
        $(idChat).hide();
        chatsAbiertos--;
//        console.log(chatsAbiertos);
    },
    clickMin: function(idChat) {
//        console.log(idChat);
        $('#' + idChat).css({
            'height': '40px',
            'padding': '310px 0px 0px 0px',
            'box-shadow': 'none'
        });

        $('#' + idChat + ' div.chat-body').hide();
        $('#' + idChat + ' div.chat-footer').hide();
        $('#' + idChat + ' div.btn-min').hide();
        $('#' + idChat + ' div.btn-max').show();
    },
    prueba: function(id) {
        alert(id);
    },
    validaDatosVisitante: function() {
        var name = divContent.find('div.chat-body div#datosVisitante #nameVisitante').val();
        var email = divContent.find('div.chat-body div#datosVisitante #correoVisitante').val();
        if ((name.length > 6) && (email.length > 6)) {
            nombreVisitante = name;
            correoVisitante = email;
            controlChat.enviarDatosVisitante();
        } else {
            divContent.find('div.chat-body div#datosVisitante div#informacion img').attr('src', 'img/icons/global/alert-icon.png');
            divContent.find('div.chat-body div#datosVisitante div#informacion span').html('La información ingresada no es válida, por favor corrígela.');
        }
    },
    enviarDatosVisitante: function() {
        divContent.find('div.chat-body div#datosVisitante span#informacion').css('margin-top', '9px');
        divContent.find('div.chat-body div#datosVisitante span#informacion').html('Enviando...');
        var name = divContent.find('div.chat-body div#datosVisitante #nameVisitante').val();
        var email = divContent.find('div.chat-body div#datosVisitante #correoVisitante').val();
        $.ajax({
            'url': 'visitanteTemporal',
            'data': {'nombre': name, 'correo': email},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    idUsuario = respuesta.datos;
                    if (maxChat == 0) {
//            console.log('Inicia la petición - Buscar asesores');
                        //Inicia la petición                                    
                        divContent.find('div.chat-body').html('');
                        divContent.find('div.chat-body').append($('#tiposSms div.buscando-soporte').clone().attr('id', 'informativo').show());
                        //Lanzamos la petición AJAX para buscar los asesores disponibles.
                        $.ajax({
                            'url': 'buscarAsesores',
                            'type': 'POST',
                            success: function(data) {
                                var respuesta = JSON.parse(data);
//                    console.log(respuesta);
                                if (respuesta.datos != null) {
                                    console.info('Se ha encontrado un asesor');
                                    idUsuarioContacto = respuesta.datos.idUsuario;
                                    usuarioOpuesto = respuesta.datos;
//                                    console.log(usuarioOpuesto);
//                        console.info('IdUsuarioContacto: ' + idUsuarioContacto);
//                        divContent.find('div.chat-body div#informativo').hide();
                                    divContent.find('div.chat-header div.estado').show();
                                    divContent.find('span.text-header').html(respuesta.datos.nombres + ' ' + respuesta.datos.apellidos);
                                    controlChat.enviarChat(idUsuarioContacto, 'Bienvenid@ ¿en que te puedo colaborar?');
                                    divContent.find('div.chat-body div#informativo span').html('Buscando soporte...');
                                    maxChat = 1;
                                    divContent.find('div.chat-footer #sms').show();
                                } else {
                                    console.warn('No se encontró un asesor');
                                    divContent.find('div.chat-body').html('');
                                    divContent.find('div.chat-footer #sms').hide();
                                    divContent.find('div.chat-body').append($('div#tiposSms div#errorbuscandoamin').clone().attr('id', 'alerta'));
                                    maxChat = 2;
                                }
                            }
                        });
                        //Finaliza la petición.            
                    }
                } else {
                    divContent.find('div.chat-body div#datosVisitante span#informacion').html('Se ha producido un error desconocido, por favor intenta de nuevo!.');
                    divContent.find('div.chat-body div#datosVisitante div#informacion img').attr('src', 'img/icons/global/error-icon.png');
                }
            }
        });
    },
    clickMax: function(idChat) {
        idChatText = idChat;
        $('#' + idChat).css({
            'height': '350px',
            'padding': '0px',
            'box-shadow': '0px 0px 4px black'
        });

        $('#' + idChat + ' div.chat-body').show();
        $('#' + idChat + ' div.chat-footer').show();
        $('#' + idChat + ' div.chat-footer #sms').focus();
        $('#' + idChat + ' div.btn-min').show();
        $('#' + idChat + ' div.btn-max').hide();
        var id = divContent.find('div')[8].id;
        var elemento = document.getElementById(id);
        leyendoChat = false;
//        evento = event;
        if (!leyendoChat) {
            elemento.scrollTop = (elemento.scrollHeight - elemento.clientHeight) + 100;
        }
        leyendoChat = true;

        //Consultamos un asesor de soporte si la variable maxChat es = 0,
        //generalmente se hace para cuando el usuario maximise la primera vez
        //la venenta del chat, se abra el recurso en el sistema, para no 
        //consumir memoria inesesaria del servidor, y sobre todo que los
        //usuarios de soporte tengan la posibilidad de no frustrarse con tantos
        //conectados. 

        if (maxChat == 0) {
            if (controlPeticiones.consultarUsuarioLogeado()) {
                idUsuario = respuestaUsuario.datos.idUsuario;
                nombreVisitante = respuestaUsuario.datos.nombres +' '+ respuestaUsuario.apellidos;
                correoVisitante = respuestaUsuario.datos.email;
                alert('Se encontro alguien logueado');
                $.ajax({
                    'url': 'buscarAsesores',
                    'type': 'POST',
                    success: function(data) {
                        var respuesta = JSON.parse(data);
//                    console.log(respuesta);
                        if (respuesta.datos != null) {
                            console.info('Se ha encontrado un asesor');
                            idUsuarioContacto = respuesta.datos.idUsuario;
                            usuarioOpuesto = respuesta.datos;
//                                    console.log(usuarioOpuesto);
//                        console.info('IdUsuarioContacto: ' + idUsuarioContacto);
//                        divContent.find('div.chat-body div#informativo').hide();
                            divContent.find('div.chat-header div.estado').show();
                            divContent.find('span.text-header').html(respuesta.datos.nombres + ' ' + respuesta.datos.apellidos);
                            controlChat.enviarChat(idUsuarioContacto, 'Bienvenid@ ¿en que te puedo colaborar?');
                            divContent.find('div.chat-body div#informativo span').html('Buscando soporte...');
                            maxChat = 1;
                            divContent.find('div.chat-footer #sms').show();
                        } else {
                            console.warn('No se encontró un asesor');
                            divContent.find('div.chat-body').html('');
                            divContent.find('div.chat-footer #sms').hide();
                            divContent.find('div.chat-body').append($('div#tiposSms div#errorbuscandoamin').clone().attr('id', 'alerta'));
                            maxChat = 2;
                        }
                    }
                });
            } else {
                divContent.find('div.chat-body').html('');
                divContent.find('div.chat-footer #sms').hide();
                divContent.find('div.chat-body').append($('div#tiposSms div#datosPersonales').clone().attr('id', 'datosVisitante'));
            }
        }
    },
    enviarChat: function(idUSuarioOrigen, sms) {
        var id = divContent.find('div')[8].id;
        var elemento = document.getElementById(id);

        var posScroll = elemento.scrollHeight - elemento.clientHeight;

        if (elemento.scrollTop < (posScroll)) {
            leyendoChat = true;
//            console.log('Es menor');
        } else {
//            console.log('Es mayor');
            leyendoChat = false;
        }

        if (idUSuarioOrigen == idUsuario) {
            divContent.find('div.chat-body').append($('#tiposSms div.sms-emisor').clone().attr('id', 'smsSend' + idMensajeEnviado).show());
            $('#smsSend' + idMensajeEnviado + ' div.content-sms').html(refreshChatVisitante.buscarEmoticones(sms));
            $('#smsSend' + idMensajeEnviado + ' div.fecha').html(controlChat.formatearHora(new Date()));
            divContent.find('div.chat-body div#estado').remove();
            divContent.find('div.chat-body').append($('#tiposSms div.estado-acciones').clone().attr('id', 'estado').show());
            divContent.find('div.chat-body div.estado-acciones span').hide();
            refreshChatVisitante.posScroll();
            idMensajeEnviado++;
        }

        var url = 'insertarchat';
        var metodo = 'POST';
        var objetoJSON;
        var data = {};
        data.mensaje = sms;
        data.idUsuarioOrigen = idUSuarioOrigen;
        if (idUSuarioOrigen == idUsuario) {
            data.idUsuarioDestino = idUsuarioContacto;
        } else {
            data.idUsuarioDestino = idUsuario;
        }
        data.idConversacion = idConversacionChat;
        objetoJSON = {'chat': JSON.stringify(data)};
//        console.log('Objeto preparado.');
//        console.log(objetoJSON);
//        var respuesta = controlPeticiones.insertar(url, metodo, objetoJSON, '#footChat');

        $.ajax({
            'url': url,
            'type': metodo,
            'data': objetoJSON,
            success: function(data) {
                var respuesta = JSON.parse(data);
                $('#smsSend' + (idMensajeEnviado - 1) + ' div.fecha').html(controlChat.formatearHora(respuesta.datos));
//                console.log(respuesta);
//                console.log(respuesta.datos);
            }
        });
    },
    formatearHora: function(dateObject) {
        var d = new Date(dateObject);
        var day = d.getDate();
        var month = d.getMonth() + 1;
        var year = d.getFullYear();
        var hours = d.getHours();
        var minutes = d.getMinutes();
        var secounds = d.getSeconds();
        var formato = 'NAV';
        if (day < 10) {
            day = "0" + day;
        }
        if (month < 10) {
            month = "0" + month;
        }
        if (hours < 10) {
            hours = "0" + hours;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (secounds < 10) {
            secounds = "0" + secounds;
        }
        if (hours > 12) {
            hours = hours - 12;
            formato = 'PM';
        } else {
            formato = 'AM';
        }
        var date = day + "/" + month + "/" + year + " " + hours + ":" + minutes + ":" + secounds + ' ' + formato;
        return date;
    },
};

function calculaIP2() {
//    script.src = "http://www.telize.com/jsonip?callback=DisplayIP";
//    document.getElementsByTagName("head")[0].appendChild(script);        
    DisplayIP2(ipVisitante);
}
;
function DisplayIP2(response) {
//    var ip = response.ip;
//    var ip = response.replace(/\./g, '');
//    console.log('Su ip es: ' + ip);
    var sms = divContent.find('#sms').val();
    divContent.find('#sms').val('');
    if (sms.length > 1) {
//        divContent.find('div.chat-body img').show();
        controlChat.enviarChat(idUsuario, sms);
    }
}