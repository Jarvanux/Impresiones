/* 
 * Autor: John Jaider Vanegas - Clover-Software.
 * Código libre.
 * YouTube: jhonjaider1000.
 */

var validacionesGlobales = {
    validar: function(divContent, limitDatos) {
        var respuesta = false;
        var textCompletos = false;
        var textNumCompletos = false;
        var selectsCompletos = false;
        //Valida si se encontraron input text en el contenido.
        var dataFinds = $('div#' + divContent).find('input[type="text"]');
        if (dataFinds.length > 0) {
            var completo = 0;
            //Inicio For            
            data = null;
            for (var i = (dataFinds.length - 1); i >= 0; i--) {
                data = dataFinds[i];
                if ((!(data.id == 'celular')) && (!(data.id == 'telefono'))){
                    if ($('#' + data.id).val().length > limitDatos) {
                        completo++;
                        $('#' + data.id).css({"border": "1px solid #999999"});
                    } else {
                        $('#' + data.id).css({"border": "1px solid red"});
                    }
                }
            }
            //Fin For

            //Ahora validamos los campos.
            if (dataFinds.length == (completo)) {
                textCompletos = true;
            } else {
                textCompletos = false;
            }
            //Fin valida campos.
        } else {
            textCompletos = true;
        }
        //Fin valida input text encontrados.


        //Inicio valida num
        dataFinds = $('div#bodyForm').find('input[type="number"]');
        if (dataFinds.length > 0) {
            var completo = 0;
            //Inicio For            
            data = null;
            for (var i = (dataFinds.length - 1); i >= 0; i--) {
                data = dataFinds[i];
                if ($('#' + data.id).val() > 0) {
                    completo++;
                    $('#' + data.id).css({"border": "1px solid #999999"});
                } else {
                    $('#' + data.id).css({"border": "1px solid red"});
                }
            }
            //Fin For

            //Ahora validamos los campos.
            if (dataFinds.length == (completo)) {
                textNumCompletos = true;
            } else {
                textNumCompletos = false;
            }
            //Fin valida campos.
        } else {
            textNumCompletos = true;
        }
        //Fin valida num

        //Inicio valida select
        dataFinds = $('div#bodyForm').find('select');
        if (dataFinds.length > 0) {
            var completo = 0;
            //Inicio For            
            data = null;
            for (var i = (dataFinds.length - 1); i >= 0; i--) {
                data = dataFinds[i];
                if ($('#' + data.id).val() > 0) {
                    completo++;
                    $('#' + data.id).css({"border": "1px solid #999999"});
                } else {
                    $('#' + data.id).css({"border": "1px solid red"});
                }
            }
            //Fin For

            //Ahora validamos los campos.
            if (dataFinds.length == (completo)) {
                selectsCompletos = true;
            } else {
                selectsCompletos = false;
            }
            //Fin valida campos.
        } else {
            selectsCompletos = true;
        }
        //Fin valida select      

        //Retornamos el resultado.
        if (textCompletos && selectsCompletos) {
            respuesta = true;
        } else {
            respuesta = false;
        }
        return respuesta;
    }
};

