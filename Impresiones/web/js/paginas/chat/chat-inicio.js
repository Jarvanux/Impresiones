/* 
 Generalmente este script contiene todos los eventos y/o procesos que debe ejecutar
 el chat cuando alguien ingresa al sistema, en este caso determinar que usuario de
 soporte están conectados y asignar uno.
 */

var chatInicio = {
    init: function() {
        $('#paginasModales').hide();
        chatInicio.determinarConectados();
    },
    determinarConectados: function() {        
        $.ajax({
            'url': 'soporteconectado', //Nombre de instancia del servlet.
            'type': 'POST',
            success: function(data) {
                var respuesta = JSON.parse(data); //Concatenamos el objeto recibido a un objeto de JSON.                                                                            
//                console.log(respuesta);
                chatInicio.eventos();
                chatControl.posScroll();
                if (respuesta.codigo > 0) {
                    $('#cabezaChat').append($('div#chateando').clone().hide());

                } else {
                    $('div.cabezaChat').css({'background': 'orange'});
                    $('div.cabezaChat span').html('Desconectado - deja tú mensaje');
                    $('div.contenedorChat').show();
//                    console.log('Desconectados');
                    $('div.cuerpoChat').html('');
                    $('div.cuerpoChat').css({'height': '280px'});
                    $('div.text-sms').prop('disabled', 'true');
                    $('div.cuerpoChat').append($('div#panelRegistrate').clone().show());
                    $('div#panelRegistrate').css({'width': '100%', 'height': '100%'});
                    $('#cabezaChat').find($('div#chateando')).remove();
                    $('#textRegistrate').css({'font-size': '20px', 'font-weight': 'bold', 'display:': 'block'});
                    $('#textRegistrate').mouseout(function() {
                        $('#textRegistrate').css({'color': 'black'});
                    });
                    $('#textRegistrate').mouseover(function() {
                        $('#textRegistrate').css({'color': 'blue'});
                    });
                    $('#textRegistrate').click(function() {
                        chatInicio.registrar();
                    });
                }
            }
        });

    },
    asignarSoporte: function() {
    },
    eventos: function() {
        $('#cerrar').css({'cursor': 'pointer'});
        $('#cerrar').click(function() {
            $('#info').hide();
        });
    },
    cargarData: function(pagina) {
        $("div.body-modal p")
                .html('')
                .append($('#cargador').clone().show());
        $.post(pagina, function(data) {
            $("div.body-modal p").html(data);
        });
    },
    registrar: function() {
        chatInicio.cargarData('paginas/administracionsSitio/registrar.html');
        $('#info').slideDown(500);
        $('.contentModal').css({'width': '50%'});
//        $('.body-modal').css({'width':'50%'});
        $('div#mensaje').hide();
    },
};

chatInicio.init();