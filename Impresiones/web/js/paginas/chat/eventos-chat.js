/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

estado = true; //true = maximisado || false = min.

var eventosChat = {
    init: function() {        
        var pagina = 'paginas/chat/chat.html';
        $("#complementChat")
                .html('')
                .append($('#cargador').clone().show());
        $.post(pagina, function(data) {
            $("#complementChat").html(data);
        });
        $('div.contenedorChat').hide();
    },
    cambio: function(divContendor) {
        var div = $('#' + divContendor);
//        console.log(div);
        if (!estado) {
            div.css({'height': '40px'});
            div.find('#cuerpoChat').hide();
            div.find('#footChat').hide();
            estado = true;
//            $('#contenedorChat').append($('div').clone().attr('id', 'xd').show());            
        } else {
            div.css({'height': '320px'});
            div.find('#cuerpoChat').show();
            div.find('#footChat').show();
            estado = false;
            chatControl.posScroll();
//            console.log(estado);
        }
    }
};

eventosChat.init();