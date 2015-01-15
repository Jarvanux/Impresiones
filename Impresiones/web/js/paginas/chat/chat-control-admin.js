/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var chatsAbiertos = 1;
var contChat = 1;
divContent = null;
leyendoChat = false;
var maxChat = 0;
var idConversacionChat = 0;
var idUsuarioContacto = 0;
//var idUsuario = 0;
var usuarioOpuesto = null;
var idChatText = null;
var idMensajeEnviado = 0;
$(document).ready(function() {
    controlChat.init();
});
var controlChat = {
    init: function() {
        $('#chatmodelo div.btn-close').show();
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
    chatNuevo: function(idChat) {
        if ($('div.all-chats').find('#chat' + idChat).length == 0) {
            $('div.all-chats').append($('#chatmodelo').clone().attr('id', 'chat' + idChat).show());
            $('#chat' + idChat + ' div.chat-body').attr({'id': 'cuerpo-chat' + idChat});
            $('#chat' + idChat + ' div.btn-max').attr({'onclick': "controlChat.clickMax('chat" + idChat + "')"});
            $('#chat' + idChat + ' div.btn-min').attr({'onclick': "controlChat.clickMin('chat" + idChat + "')"});
            $('#chat' + idChat + ' div.chat-footer .text-sms').attr({'onclick': "controlChat.clickPress('chat" + idChat + "')"});
            divContent = $('#chat' + idChat);
            $('div#chat' + idChat + ' div.chat-header div#mensajes-recibidos').hide();
        } else {
            $('div.all-chats').find('#chat' + idChat).show();
            $('div#chat' + idChat + ' div.chat-header .btn-max').click();
            chatsAbiertos++;
        }
    },
    crearPanelChat: function(idChat) {
        if ($('div.all-chats').find('#chat' + idChat).length == 0) {
            //Si hay espacio en la ventana simplemente se agrega visible en la misma.
            $('div.all-chats').append($('#chatmodelo').clone().attr('id', 'chat' + idChat));
            $('#chat' + idChat + ' div.chat-body').attr({'id': 'cuerpo-chat' + idChat});
            $('#chat' + idChat + ' div.btn-max').attr({'onclick': "controlChat.clickMax('chat" + idChat + "')"});
            $('#chat' + idChat + ' div.btn-min').attr({'onclick': "controlChat.clickMin('chat" + idChat + "')"});
            $('#chat' + idChat + ' div.chat-footer .text-sms').attr({'onclick': "controlChat.clickPress('chat" + idChat + "')"});
            divContent = $('#chat' + idChat);
            $('div#chat' + idChat + ' div.chat-header div#mensajes-recibidos').hide();
        }
    },
    consultaDatosChat: function(idChat) {
        $.ajax({
        });
    },
    clickClose: function(event) {
        var idChat = event.path[3];
        $(idChat).hide();
        chatsAbiertos--;
        $(idChat).find('span#leidos').attr('class', 'false'); //Se le asigna eso para saber que el usuario está leyendo ese chat.
    },
    clickMin: function(idChat) {
//        console.log(idChat);
        $('#' + idChat + ' span#leidos').attr('class', 'false'); //Se le asigna eso para saber que el usuario está leyendo ese chat.
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
    clickMax: function(idChat) {
        idChatText = idChat;
        $('#' + idChat).css({
            'height': '350px',
            'padding': '0px',
            'box-shadow': '0px 0px 4px black'
        });
        var idUnico = idChat.substring(idChat.search('-') + 1);
        $('#'+idChat).find('span.maxPrimeraVez').html("true");
        
        $('#' + idChat + ' span#leidos').attr('class', 'true'); //Se le asigna eso para saber que el usuario está leyendo ese chat.
        $('#' + idChat).find('.chatsLeidos').html(0);
        $('#' + idChat + ' div.chat-body').show();
        $('#' + idChat + ' div.chat-footer').show();
        $('#' + idChat + ' div.chat-footer #sms').focus();
        $('#' + idChat + ' div.btn-min').show();
        $('#' + idChat + ' div.btn-max').hide();
//        var id = divContent.find('div')[8].id;
//        var elemento = document.getElementById(id);
        leyendoChat = false;
//        evento = event;
        if (!leyendoChat) {
//            elemento.scrollTop = (elemento.scrollHeight - elemento.clientHeight) + 100;
        }
//        leyendoChat = true;
    },
    enviarChat: function(idUSuarioOrigen, sms) {
        var id = divContent.find('div').selector.substring(1).replace(' div', '');
        var elemento = document.getElementById('cuerpo-' + id);
        var posScroll = elemento.scrollHeight - elemento.clientHeight;


        if (idUSuarioOrigen == idUsuarioLogeado) {
            divContent.find('div.chat-body').append($('#tiposSms div.sms-emisor').clone().attr('id', 'smsSend' + idMensajeEnviado).show());
            $('#smsSend' + idMensajeEnviado + ' div.content-sms').html(refrescaClientes.buscarEmoticones(sms));
            $('#smsSend' + idMensajeEnviado + ' div.fecha').html(controlPeticiones.formatearHora(new Date()));
            divContent.find('div.chat-body div#estado').remove();
            divContent.find('div.chat-body').append($('#tiposSms div.estado-acciones').clone().attr('id', 'estado').show());
            divContent.find('div.chat-body div.estado-acciones span').hide();
//            refreshChatVisitante.posScroll();
            idMensajeEnviado++;
            var id = divContent.find('div').selector.substring(1).replace(' div', '');
            var elemento = document.getElementById('cuerpo-' + id);
            elemento.scrollTop = (elemento.scrollHeight - elemento.clientHeight) + 100;
        }

        var url = 'insertarchat';
        var metodo = 'POST';
        var objetoJSON;
        var data = {};
        data.mensaje = sms;
        data.idUsuarioOrigen = idUSuarioOrigen;
        if (idUSuarioOrigen == idUsuarioLogeado) {
            var x = idChatText;
            x = x.substring(x.search('-') + 1);
            idUsuarioContacto = x;
            data.idUsuarioDestino = idUsuarioContacto;
        } else {
            data.idUsuarioDestino = idUsuarioLogeado;
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
    DisplayIP2(idUsuarioLogeado);
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
        controlChat.enviarChat(idUsuarioLogeado, sms);
    }
}