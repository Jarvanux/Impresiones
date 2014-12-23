var mensajes = [];
/*

 {
    id: 1,
    mensaje: 'Hola',
    hora: '10:00am',
    fecha: '27/09/2014',
    tipo: 1,
    emisor: 'Tú'
},
{
    id: 1,
    mensaje: 'Hola',
    hora: '10:00am',
    fecha: '27/09/2014',
    tipo: 0,
    emisor: 'Leonardo'
}*/




var chat = {
    ventanas: 0,
    init: function(mensajes) {
        chat.cargarMensajes(mensajes);
        
    },
    
    cargarMensajes: function(msg, _chat) {
        var actualizar = _chat && _chat!=="";
        var nuevoChat = (actualizar)?$('#'+_chat):$('#divChatTemplate').clone();
        
        var msgs = nuevoChat.find('div.divMensajes');
        
        if (!actualizar) {
            var id_chat = 'chat_'+chat.ventanas;
            nuevoChat.attr('id', id_chat);
            nuevoChat.css('right', (chat.ventanas*255)+10);
            chat.ventanas++;
            $('body').append(nuevoChat);
            nuevoChat.find('.btnMinChat').on('click', chat.ocultarMostrarChat);
            nuevoChat.find('.btnEnviarChat')
                    .on('click', chat.enviarMensaje)
                    .attr('data-chat', id_chat);
            nuevoChat.find('.txtNuevoChat').on('keypress', function(ev){
                var keycode = (ev.keyCode ? ev.keyCode : ev.which);
                if (keycode === 13 && !ev.shiftKey) {
                    app.detenerEvento(ev);
                    nuevoChat.find('.btnEnviarChat').click();
                }
            });
            nuevoChat.show();
        }        

        var template = $('#tplMensaje').html();
        var info = $(Mustache.render(template, msg));
        info.addClass(msg.tipo === 0 ? 'msgHost' : 'msgLocal');
        msgs.append(info);

        if(nuevoChat.find('.tituloChat').text()===''){
            if (msg.tipo===0) {
                nuevoChat.find('.tituloChat').text(msg.emisor);
            }
        }
        
        msgs.scrollTop(parseInt(msgs.css('height')));
    },
    
    ocultarMostrarChat: function() {
        var _this = $(this);
        var c = _this.parent().next();
        c.toggle('fast');
    },
    
    
    enviarMensaje:function(){
        var _this = $(this);
        var id_chat = _this.attr('data-chat');
        
        var mensaje = $('#'+id_chat+' .txtNuevoChat').val().trim();
        if (mensaje!=="") {
            $('#'+id_chat+' .txtNuevoChat').val("");
            var msg = {
                mensaje: mensaje.replace("\n","<br/>"),
                tipo: 1,
                emisor: 'Tú'
            };
            
            ///enviar chat por peticion asincrona
            chat.onMensajeEnviado(msg, id_chat);
        }
    },
    
    onMensajeEnviado:function(data, id_chat){
        //if (data.codigoRespuesta===1) {
            chat.cargarMensajes(data, id_chat);
        //}
        
    }
};