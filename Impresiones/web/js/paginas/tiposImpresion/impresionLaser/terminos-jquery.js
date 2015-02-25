$(document).ready(function() {
    var logger = false;
    $('#btnAceptarTerminos').click(function() {
        logger = controlPeticiones.consultarUsuarioLogeado();
    });
    if (!logger) {

    }
});

var terminos = {
    init: function() {
        terminos.generarUnCodigo();
        $('#rhojasBN').html($('#numTotalBN').val());
        $('#rhojasColor').html($('#numTotalColor').val());
        $('#rnCopias').html($('#numCopys').val());
        $('#rtImpre').html(($('#numTotalBN').val() + $('#numTotalColor').val()));
        $('#rtotal').html($('#valorTotal').html());
        $('#valorImpresionPedido1').html('Valor: ' + $('#rtotal').html());
        $('#codigoImpre').css({'color': 'blue', 'font-weight': 'bold'});
        $('#rtotal').css({'color': 'blue', 'font-weight': 'bold'});
        if ($('#mixto').is(':checked')) {
            $('#rvUnitarioBN').html('$' + controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(num1)));
            $('#rvUnitarioColor').html('$' + controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(num1)));
        } else {
            if ($('#soloBN').is(':checked')) {
                $('#rvUnitarioBN').html('$'+controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal($('#valorTotal').html().replace('$',''))));
            } else if ($('#soloColor').is(':checked')) {
                $('#rvUnitarioBN').html('$'+controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal($('#valorTotal').html().replace('$',''))));
            }
        }
        var pagina = "paginas/administracionsSitio/ingresar.html";
        $("div#loginPedido").html('').append($('#cargador').clone().show());
        $.post(pagina, function(data) {
            $("div#loginPedido").html(data);
        });
        
        //Volvemos a validar los campos del formulario anterior solo para
        //Adquirir los precios de anillado y demás cosas seleccionadas.
        validacionesImpresionLaser.validarSegundoForm();
    },
    generarUnCodigo: function() {
        $.ajax({
            'url': 'asignarDatosImpre', //Nombre de instancia del servlet.
            'type': 'POST',
            success: function(data) {
                var respuesta = JSON.parse(data); //Concatenamos el objeto recibido a un objeto de JSON.
                if (respuesta.codigo > 0) {
                    $('#codigoImpre').html(respuesta.datos.enteroGrande);
                    $('#fecha').html(controlPeticiones.formatearHora(respuesta.datos.fecha));
                } else {
                    $('div#titulo h2').html('!Atención!');
                    $('div#cuerpo p').html('Se ha producido un error inesperado y no \n\
                        se ha podido asignar un código a la impresión, por favor vuelve atrás y \n\
                        finaliza el pedido nuevamente para reenviar la solicitud al servidor.');
                    $('#aceptar').hide();
                    $('#aceptar2').hide();
                    $('#aceptar3').show();
                    $('#info').slideDown(500);
                    $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
                    $('div#content div#cuerpo').css({"width": "400px", "height": "auto"});
                }
                console.log(respuesta);
            }
        });
    }
};

terminos.init();