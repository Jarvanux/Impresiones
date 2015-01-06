/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    setInterval(refrescarChat, 1000);
    $.ajaxSetup({cache: false});
    function refrescarChat() {
//        console.log('Se ha actualizado! :D');
        chatControl.leerChat();
//        divContent = $('#cuerpoChat');
//        chatControl.enviarChat("12344","Hola");
    }
});
$(document).ready(function() {
    setInterval(refrescarEstado, 20000);
    $.ajaxSetup({cache: false});
    function refrescarEstado() {
//        console.log('Se ha actualizado! :D');
        chatInicio.determinarConectados();
//        divContent = $('#cuerpoChat');
//        chatControl.enviarChat("12344","Hola");
    }
});
numSms = 0;
modoLectura = false;
posScroll = 0;
divContent = null;
consultaEstadoRealizada = 0;
var chatControl = {
    init: function() {

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
        if(hours > 12){
            hours = hours - 12;
            formato = 'PM';
        }else{
            formato = 'AM';
        }
        var date = day + "/" + month + "/" + year + " " + hours + ":" + minutes + ":" + secounds + ' ' +formato;
        return date;
    },
    modoLectura: function() {      
        modoLectura = true;        
    },
    keyPress: function(event) {
        //Obtengo el div padre desde el cual se ejcutó el evento.
        divContent = $('#' + event.path[4].id);
        if (event.keyCode == 13) {
            chatControl.prepararChat();
        }
    },
    prepararChat: function() {
        calculaIP();
    },
    enviarChat: function(ipCliente, sms) {
        var url = 'insertarchat';
        var metodo = 'POST';
        var objetoJSON;
        var data = {};
        data.mensaje = sms;
        data.idUsuarioOrigen = ipCliente;
        objetoJSON = {'chat': JSON.stringify(data)};
        controlPeticiones.insertar(url, metodo, objetoJSON, '#footChat');
    },
    leerChat: function() {
        if ($('#cabezaChat').find(('div#chateando')).length > 0) {
            $.ajax({
                'url': 'leerchat',
                success: function(data) {
                    var respuesta = JSON.parse(data);
//                console.log(respuesta);
                    $('#cuerpoChat').html('');
                    if (respuesta.codigo > 0) {
                        numSms = 0;
                        $.each(respuesta.datos, function(indice, valor) {
//                        cmbTipoImpresion.append(new Option(valor.tipoImpresion, valor.idTipoImpresion));                       
//                        console.log(valor.mensaje);                        
                            $('#cuerpoChat').append($('#tiposSms div.sms-emisor').clone().attr('id', 'sms' + indice).show());
                            $('#sms' + indice + ' div.content-sms').html(valor.mensaje);
                            $('#sms' + indice + ' div.fecha').html(chatControl.formatearHora(valor.fecha));
                        });
                        chatControl.posScroll();
                    }
                }
            });
        } else {
            if (consultaEstadoRealizada <= 2) {
                chatInicio.determinarConectados();
                consultaEstadoRealizada++;
            }
        }
    },
    doRefresh: function() {

    },
    posScroll: function() {
        var scc = document.getElementById('cuerpoChat');        
        scc.scrollTop = scc.scrollHeight + scc.offsetHeight;
        var elemento = scc;
        elemento.scrollTop = elemento.scrollHeight - elemento.clientHeight;
        
        
        
//        var myCont = document.getElementById('cuerpoChat');
//        vary = 9999999999999999999999999999999999999999999999999;
//        if (!modoLectura) {
//            myCont.scrollTop = vary;
//        }
//        if (posScroll == $('#cuerpoChat').scrollTop()) {
//            modoLectura = false;
//        }
    },
    encontraraEmoticones: function(cadena) {
    }
};
chatControl.init();
function calculaIP() {
    var script = document.createElement("script");
    script.type = "text/javascript";
//    script.src = "http://www.telize.com/jsonip?callback=DisplayIP";
//    document.getElementsByTagName("head")[0].appendChild(script);        
    DisplayIP('982.934.234');
}
;
function DisplayIP(response) {
//    var ip = response.ip;
    ip = response.replace(/\./g, '');
//    console.log('Su ip es: ' + ip);
    var sms = divContent.find('#sms').val();
    divContent.find('#sms').val('');
    chatControl.enviarChat(ip, sms);
}
