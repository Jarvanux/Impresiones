/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var controlImpresionesRealizadas = {
    init: function() {
        $('#body').css('margin-left', '60px');
        $('#body td').css('padding', '5px');
        controlImpresionesRealizadas.listarImpresionesLaser();
        controlImpresionesRealizadas.eventos();
    },
    mouseDownItemLista: function(event) {
        console.log(event);
        if (event.which == 2 || event.which == 3) {
            document.oncontextmenu = function() {
                return false;
            };
            $('#menuOpcionItemLista').show();
            $('#menuOpcionItemLista').css('top', event.pageY, 'left', event.pageX, 'margin-left', event.pageX);
            $('#menuOpcionItemLista').css('margin-left', event.pageX + 'px');
        }
    },
    eventos: function() {
        var data = null;
        $('#codigo-impre').keyup(function() {
            data = $('#codigo-impre').val();
            if (data.length > 2) {
                controlImpresionesRealizadas.filtrarCodigo(data);
            } else {
                controlImpresionesRealizadas.listarImpresionesLaser();
            }
        });


        $('.eventmousedown').mousedown(function(event) {
            if (event.which == 2 || event.which == 3) {
                document.oncontextmenu = function() {
                    return false;
                };
                $('#menuSection1').show();
                $('#menuSection1').css('top', event.pageY, 'left', event.pageX, 'margin-left', event.pageX);
                $('#menuSection1').css('margin-left', event.pageX + 'px');
            }
        });

        $(window).click(function(event) {
//            console.log(event);
            $('#menuOpcionItemLista').hide();
            document.oncontextmenu = function() {
                return true;
            }
        });
    },
    filtrarCodigo: function(filtro) {
        $.ajax({
            'url': 'filtrarImpresionLaser',
            'type': 'POST',
            'data': {'idUsuario': idUsuarioLogeado, 'filtro': filtro, 'tipo': $('#consulta-tipo').val()},
            success: function(data) {
                var respuesta = JSON.parse(data);
//                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    var trModelo = $('tr#trModelo');
                    $('#body table#tablaContent').html('');
                    $('#body table#tablaContent').append(trModelo.clone());
                    $.each(respuesta.datos, function(indice, valor) {
                        $('#body table#tablaContent').append(trModelo.clone().attr('id', 'tr_' + valor.idImpresion).show());
//                        $('#body table#tablaContent tr#tr_' + valor.idImpresion).attr({'onmousedown': 'controlImpresionesRealizadas.mouseDownItemLista(event)'});
                        $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-1').html(indice + 1);
                        $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-2').html(valor.guiaImpresion);
                        $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-3').html(controlPeticiones.formatearHora(valor.fecha));

                        if (valor.rutaArchivo != "") {
                            $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-4').html('<a target="_blank" href="' + (valor.rutaArchivo).substring((valor.rutaArchivo).search("archivos-subidos")) + '">' + valor.nombreArchivo + '</a>');
                        } else {
                            $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-4').html('<a target="_blank" href="' + valor.linkArchivo + '">' + "Link de descarga" + '</a>');
                        }

                        $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-5').html((valor.hojasBn + valor.hojasColor) * valor.copias);
                        $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-6').html(valor.estado);
                    });
                } else {
                    var trModelo = $('tr#trModelo');
                    $('#body table#tablaContent').html('');
                    $('#body table#tablaContent').html('<td colspan="5" style="width: 100%; text-align: left; padding: 0px 0px 0px 30px; color: #000">No hay registros...</td>');
                    $('#body table#tablaContent').append(trModelo.clone());
                }
            }
        });
    },
    listarImpresionesLaser: function() {
        $.ajax({
            'url': 'listaImpresionesLaser',
            'type': 'POST',
            'data': {'idUsuario': idUsuarioLogeado, 'tipo': $('#consulta-tipo').val()},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    var trModelo = $('tr#trModelo');
                    $('#body table#tablaContent').html('');
                    $('#body table#tablaContent').append(trModelo.clone());
                    $.each(respuesta.datos, function(indice, valor) {
                        $('#body table#tablaContent').append(trModelo.clone().attr('id', 'tr_' + valor.idImpresion).show());
//                        $('#body table#tablaContent tr#tr_' + valor.idImpresion).attr({'onmousedown': 'controlImpresionesRealizadas.mouseDownItemLista(event)'});
                        $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-1').html(indice + 1);
                        $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-2').html(valor.guiaImpresion);
                        $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-3').html(controlPeticiones.formatearHora(valor.fecha));
                        if (valor.rutaArchivo != "") {
                            $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-4').html('<a target="_blank" href="' + (valor.rutaArchivo).substring((valor.rutaArchivo).search("archivos-subidos")) + '">' + valor.nombreArchivo + '</a>');
                        } else {
                            $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-4').html('<a target="_blank" href="' + valor.linkArchivo + '">' + "Link de descarga" + '</a>');
                        }
                        $('#body table#tablaContent tr#tr_' + valor.idImpresion + ' td.colum-5').html((valor.hojasBn + valor.hojasColor) * valor.copias);
                    });
                } else {
                    var trModelo = $('tr#trModelo');
                    $('#body table#tablaContent').html('');
                    $('#body table#tablaContent').html('<td colspan="5" style="width: 100%; text-align: left; padding: 0px 0px 0px 30px; color: #000">No hay registros...</td>');
                    $('#body table#tablaContent').append(trModelo.clone());
                }
            }
        });
    }
};
$(document).ready(function() {
    controlImpresionesRealizadas.init();
    controlImpresionesRealizadas.eventos();
});