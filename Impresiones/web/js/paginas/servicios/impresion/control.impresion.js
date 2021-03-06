/* 
 Generalmente este archivo se ha creado para controlar los eventos y demás cosas
 que pasen en la página.
 */
var divTipoImpresion = $('div#selectTipoImpresion');
var cmbxTipoImpresion = $('#cmbTipoImpresion');

var controlTipoImpresion = {
    init: function() {
        $('#cmbTipoImpresion').hide();
        $('#btnTipoImpresion').hide();
        $('.subTitulo1').hide();
        peticionImpresion.tiposImpresion(cmbxTipoImpresion);        
        $('#cmbTipoImpresion').change(function() {
            $('#mensaje').slideUp(500);
            $('#mensaje').css({"background": "#EFFF5E"});
            $.ajax({
                type: 'POST',
                'url': 'tipoimpresionid',
                'data': {'idTipoImpresion': $('#cmbTipoImpresion').val()},
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    if (respuesta.datos.descripcion != null) {
                        $('#mensaje p').html(respuesta.datos.descripcion);
                    } else {
                        $('#mensaje p').html("Sin descripción del papel.");
                    }
                }
            });
            $('#mensaje').show();
        });
    },    
    evaluarCarga: function() {
        if (cmbxTipoImpresion.val() == 1) {
            inicio.cargar('paginas/tiposImpresion/impresionLaser.html');
            posicionActual = 1;
        }
        else if (cmbxTipoImpresion.val() == 2) {
            inicio.cargar('paginas/tiposImpresion/impresionInjec.html');
            posicionActual = 2;
        }
        else if (cmbxTipoImpresion.val() == 3) {
            inicio.cargar('paginas/tiposImpresion/impresionGrabacionCD.html');
            posicionActual = 3;
        }
        else if (cmbxTipoImpresion.val() == 4) {
            inicio.cargar('paginas/tiposImpresion/impresionBanner.html');
            posicionActual = 4;
        }
        else if (cmbxTipoImpresion.val() == 4) {
            inicio.cargar('paginas/tiposImpresion/impresionPlanos.html');
            posicionActual = 5;
        } else {
            $('#mensaje').show(500);
        }
    },
    consultarDescripcionTipoSeleccionado: function() {

    }
};
controlTipoImpresion.init();