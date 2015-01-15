/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var inicio = {
    init: function() {
        if ((location.href.search('paneladministrador') < 0) && (location.href.search('login') < 0)) {
            inicio.cargar('paginas/servicios/impresion.html');
            $('#chat1').css({'margin-right':'30px'});
        }
        $('#info').hide();
        $("#lnkServicios").on('click', inicio.servicios);
        $("#lnkContactenos").on('click', inicio.contactenos);        

        $("#liServicios").on('click', inicio.servicios);
        $("#liContactenos").on('click', inicio.contactenos);
        $("#liIngresar").on('click', inicio.ingresar);
        $("#liHome").on('click', inicio.home);
        $("#liChat").on('click', inicio.chat);

        $('#btnIniciarChat').on('click', inicio.iniciarChat);

        //inicio.cargar('chat.html');

    },
    soporteContectado: function() {
        $.ajax({
            'url': 'soporteConectado',
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
                        $('#sms' + indice + ' div.fecha').html(valor.fecha);
                    });
                    chatControl.posScroll();
                }
            }
        });
    },
    home: function() {
        inicio.cargar('paginas/servicios/impresion.html');
    },
    servicios: function() {
        inicio.cargar('paginas/informacionSitio/servicios.html');
    },
    contactenos: function() {
        inicio.cargar('paginas/informacionSitio/contactenos.html');
    },
    ingresar: function() {
        inicio.cargar('paginas/administracionsSitio/ingresar.html');
    },
    cargar: function(pagina) {
        $("#divContenido")
                .html('')
                .append($('#cargador').clone().show());
        $.post(pagina, function(data) {
            $("#divContenido").html(data);
        });
    },
    chat: function() {
        var dialogo = $('#divModalChat');
        dialogo.modal();
        dialogo.find('#txtNombreChat').focus();

    },
    iniciarChat: function() {
        var dialog = $('#divModalChat');
        var nombre = dialog.find('#txtNombreChat').val().trim();
        var asunto = dialog.find('#txtAsuntoChat').val().trim();
        if (nombre !== "" && asunto !== "") {
            dialog.find('.alert-danger').addClass('hide');
            dialog.find('#txtNombreChat').val('');
            dialog.find('#txtAsuntoChat').val('');
            dialog.modal('hide');

            //OJO, esto se reemplaza por una peticion asincrona

            chat.init({
                id: 0,
                mensaje: asunto,
                hora: '10:00am',
                fecha: '27/09/2014',
                tipo: 1,
                emisor: 'Tú'
            });


        } else {
            dialog.find('.alert-danger').removeClass('hide');

        }
    }

};

inicio.init();

