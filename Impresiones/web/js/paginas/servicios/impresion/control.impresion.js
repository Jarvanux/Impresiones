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

//        $('#divContenido').css({"padding":"70px"});
        //Cuando se cambie la selección del combox tipo de impersión.
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
            $('#mensaje').slideDown(500);
        });
    },
    evaluarCarga: function() {
        if (cmbxTipoImpresion.val() == 1) {
            inicio.cargar('paginas/tiposImpresion/impresionLaser.html');
        }
        else if (cmbxTipoImpresion.val() == 2) {
            inicio.cargar('paginas/tiposImpresion/impresionInjec.html');
        }
        else if (cmbxTipoImpresion.val() == 3) {
            inicio.cargar('paginas/tiposImpresion/impresionGrabacionCD.html');
        }
        else if (cmbxTipoImpresion.val() == 4) {
            inicio.cargar('paginas/tiposImpresion/impresionBanner.html');
        }
        else if (cmbxTipoImpresion.val() == 4) {
            inicio.cargar('paginas/tiposImpresion/impresionPlanos.html');
        } else {
            $('#mensaje').slideDown(500);
        }
    },
    consultarDescripcionTipoSeleccionado: function() {

    }
};
controlTipoImpresion.init();