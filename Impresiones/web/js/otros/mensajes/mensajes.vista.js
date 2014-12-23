var _mensajes = [
    {
        id:0,
        nombre:'Juan Sebastian Vanegas',
        fecha:'09/09/2014',
        asunto:'Impresión con plotter'
    },
    {
        id:1,
        nombre:'Leonardo Rey',
        fecha:'10/09/2014',
        asunto:'Impresión a Color'
    }
];



var mensajesVista = {
    
    mensajes:[],
    
    init:function(){
        this.mensajes = _mensajes;
        this.cargarMensajes(this.mensajes);
        
    },
    
    
    cargarMensajes:function(mensajes){
        var maestro = $('#maestroMensajes');
        var template = $('#itemMensaje').html();
        mensajes.forEach(function(msg, i){
            var nuevoMensaje = $(Mustache.render(template,msg));
            nuevoMensaje.on('click', mensajesVista.onMensajeSeleccionado);
            maestro.append(nuevoMensaje);
        });
    },
    
    onMensajeSeleccionado:function(){
        var obj = null;
        for(var i = 0;i<mensajesVista.mensajes.length;i++){
            if(mensajesVista.mensajes[i].id===parseInt($(this).attr('data-id'))){
                obj = mensajesVista.mensajes[i];
            }
        }

        $('#imagenMensajes').hide();
        var divDet = $('#divMensaje').show('fast');
        divDet.find('h3').text(obj.asunto);
        divDet.find('#sRemitente').text(obj.nombre);
        divDet.find('#sCorreo').text(obj.correo);
        divDet.find('#sTelefono').text(obj.telefono);
        
    }
};

mensajesVista.init();