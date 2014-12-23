var terminos = {
    init: function() {
        terminos.generarUnCodigo();
        $('#rhojasBN').html($('#numTotalBN').val());
        $('#rhojasColor').html($('#numTotalColor').val());
        $('#rnCopias').html($('#numCopys').val());
        $('#rtImpre').html(($('#numTotalBN').val() + $('#numTotalColor').val()));
        $('#rtotal').html($('#valorTotal').html());
        var valoresUnitarios = $('#valorTotal').html();
        if ($('#mixto').is(':checked')) {
            $('#rvUnitarioBN').html('$' + num1);
            $('#rvUnitarioColor').html('$' + num2);
        } else {
            if ($('#soloBN').is(':checked')) {
                $('#rvUnitarioBN').html($('#valorTotal').html());
            } else if ($('#soloColor').is(':checked')) {
                $('#rvUnitarioBN').html($('#valorTotal').html());
            }
        }        
    },
    generarUnCodigo: function() {
        $.ajax({
            'url': 'asignarDatosImpre', //Nombre de instancia del servlet.
            'type': 'POST',
            success: function(data) {
                var respuesta = JSON.parse(data); //Concatenamos el objeto recibido a un objeto de JSON.
                if (respuesta.codigo > 0) {
                    $('#codigoImpre').html(respuesta.datos.enteroGrande);
                    $('#fecha').html(format.formatearHora(respuesta.datos.fecha));
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