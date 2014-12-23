/* 
 Generalemente este archivo ha sido creado para configurar las consultas y/o comunicaciones
 ajax que tenga la página con el servlet.
 */

var peticionImpresion = {
    tiposImpresion: function(cmbTipoImpresion) {
        $.ajax({
            'url': 'tipoimpresion',
            type: 'POST',
            success: function(data) {
                cmbTipoImpresion.html('');
                cmbTipoImpresion.append(new Option('Selecciona', '-1'));
                var respuesta = JSON.parse(data);                
                if (respuesta.codigo > 0) {
                    $.each(respuesta.datos, function(indice, valor) {
                        cmbTipoImpresion.append(new Option(valor.tipoImpresion, valor.idTipoImpresion));
                    });
                    $('#imgLoader').hide();
                    $('#smsLoader').hide();
                    $('.subTitulo1').slideDown(500);
                    $('#cmbTipoImpresion').slideDown(500);
                    $('#btnTipoImpresion').slideDown(500);                    
                }else{
                    $('#imgLoader').hide();
                    $('#smsLoader').hide();
                    $('#mensaje p').html("Error al listar datos para la lista de selección, comunícate con el administrador.");
                    $('#mensaje').slideDown(500);
                }
            }
        });
    }
};


