/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
comprando = false;
var inicio = {
    init: function() {
        if ((location.href.search('paneladministrador') < 0) && (location.href.search('login') < 0) && (location.href.search('paneloperador') < 0)) {
            if (location.href.search('facturar') >= 0) {
                inicio.cargar('paginas/administracionsSitio/facturacion.html');
            } else {
                if (!location.href.search('facturar') >= 0) {
                    inicio.cargar('paginas/servicios/impresion.html');
                    $('#chat1').css({'margin-right': '30px'});
                }
            }

            if (comprando) {
                inicio.cargar('paginas/administracionsSitio/facturacion.html');
            }
            if (location.href.search('facturar') >= 0) {
                inicio.cargar('paginas/administracionsSitio/facturacion.html');
            }
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
        posicionActual = 0;
    },
    servicios: function() {
        inicio.cargar('paginas/informacionSitio/servicios.html');
        posicionActual = 8;
    },
    contactenos: function() {
        posicionActual = 7;
        inicio.cargar('paginas/informacionSitio/contactenos.html');
    },
    ingresar: function() {
        posicionActual = 6;
        inicio.cargar('paginas/administracionsSitio/ingresar.html');
    },
    recargar: function(pagina) {

    },
    cargar: function(pagina) {
        $("#divContenido").html('').append($('#cargador').clone().show());
        $.ajax({
            'url': pagina,
            'type': 'POST',
            success: function(data) {
                $("#divContenido").html(data);
            },
            error: function(data) {
                console.error(data);
                $('#divContenido').html('');
                $('#divContenido').append($('#error-data').clone().show());
            }
        });

//        $.ajaxSetup({'async': 'false'});
//        $("#divContenido").html('').append($('#cargador').clone().show());
////        $.post(pagina, function(data)
////        {
////            $("#divContenido").html(data);
////        }
////        );
//        $("#divContenido")
//                .html('')
//                .append($('#cargador').clone().show());
//        $.post(pagina, function(data) {
//            $("#divContenido").html(data);
//        });
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

