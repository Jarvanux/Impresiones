/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var facturarcion = {
    init: function() {
        facturarcion.consultarUltimaImpresion();
        $('#cancelarPedido').click(function() {
            $('#contenidoConfirmacion').show();
        });

        $('#si-cancelar').click(function() {
            //Hace la cancelación respectiva...
//            $.ajax({
//                'url':'cancelarPedido',
//                'type':'POST',
//                success: function(data) {
//                    
//                }
//            });
            $('#contenidoConfirmacion').hide();
//            inicio.cargar('paginas/servicios/impresion.html');
        });
        $('#no-cancelar').click(function() {
            //Hace la cancelación respectiva...
            $('#contenidoConfirmacion').hide();
        });
    },
    consultarUltimaImpresion: function() {
        $.ajax({
            'url': 'ultimaImpresionLaser',
            'type': 'POST',
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    var tipo = respuesta.datos.tipoImpresion;
                    //Determinamos que tipo de impresión es...
                    if (tipo == 1) {
                        $('#tipo-impresion').html('Laser');
                    } else if (tipo == 2) {
                        $('#tipo-impresion').html('Inject');
                    } else if (tipo == 3) {
                        $('#tipo-impresion').html("Impresión y grabación de C's");
                    } else if (tipo == 4) {
                        $('#tipo-impresion').html('Plotter');
                    } else if (tipo == 5) {
                        $('#tipo-impresion').html('Planos');
                    } else if (tipo == 6) {
                        $('#tipo-impresion').html('Desconocido');
                    }

                    //Otros datos.
                    $('span#guiaImpresion').html(respuesta.datos.guiaImpresion);
                    $('span#totalImpresiones').html((respuesta.datos.hojasBn + respuesta.datos.hojasColor) * respuesta.datos.copias);


                    //Determinamos el tipo de color...
                    var tipoColor = respuesta.datos.tipoColor;
                    if (tipoColor == 0) {
                        $('span#tipoColor').html('Blanco y negro(B/N)');
                    } else if (tipoColor == 1) {
                        $('span#tipoColor').html('Color');
                    } else if (tipoColor == 2) {
                        $('span#tipoColor').html('Mixto (Color y B/N)');
                    }

                    if (respuesta.datos.rutaArchivo.length > 0) {                        
                        $('#rutaArchivo').attr('href', (respuesta.datos.rutaArchivo).substring((respuesta.datos.rutaArchivo).search("archivos-subidos")));
                    }else{
                        $('#rutaArchivo').attr('href', respuesta.datos.linkArchivo);                        
                        $('#rutaArchivo span').html('Link de descarga');
                    }

                    //Otros datos...
                    $('span#fecha').html(controlPeticiones.formatearHora(respuesta.datos.fecha));
                    $('span#nameArchivo').html(respuesta.datos.nombreArchivo);
//                    $('span#direccionEntrega').html(respuesta.datos.direccion);                                        
                    $('#notificaciones p').html('Bienvenido! Selecciona el método de pago y confirma el pedido.');
                    $('#notificaciones').show('slow');
                } else {
//                    inicio.cargar('paginas/servicios/impresion.html');
                }
            }
        });
    },
    eventos: function() {

    }
};

$(document).ready(function() {
    facturarcion.init();
    facturarcion.eventos();
    controlUsuario.consultarDirecciones();
});