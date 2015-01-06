include = "js/peticiones-globales/imagenes.cursor.js";
var respuestaUsuario = null;
var controlPeticiones = {
    consultarUsuarioLogeado: function() {
        var resultado = null;
        $.ajax({
            'url': 'consultarUsuarioLogeado',
            'type': 'POST',
            'async': false,
            success: function(data) {
                resultado = data;
            }
        });
//        console.log(resultado);
        var respuesta = JSON.parse(resultado);
//        console.log(respuesta);
        if (respuesta.datos != null) {
            respuestaUsuario = respuesta;
            return true;
        } else {
//            console.log("No hay nadie logueado");
            return false;
        }
    },
    calcularPaginasMixto: function(txt, txt2) {
        controlPeticiones.calcularPaginas(txt, 1);
        controlPeticiones.calcularPaginas(txt2, 1);
    },
    calcularPaginas: function(txt, tipoImpre) {
        $.ajax({
            'url': 'calcularpaginas', //Nombre de instancia del servlet.
            'type': 'POST',
            'data': {'cadena': $('#' + txt).val()},
            success: function(data) {
                var respuesta = JSON.parse(data); //Concatenamos el objeto recibido a un objeto de JSON.
                if (respuesta.codigo > 0) {
//                    $('#' + txt).val(respuesta.datos.string);
                    if (tipoImpre == 1) {
                        $('#txtCantVarBN').val(respuesta.datos.string);
                        $('#numPaginasBN').val(respuesta.datos.entero);
                        $('#numTotalBN').val($('#numPaginasBN').val() * $('#numCopys').val());
                    } else if (tipoImpre == 2) {
                        $('#txtCantVarColor').val(respuesta.datos.string);
                        $('#numPaginasColor').val(respuesta.datos.entero);
                        $('#numTotalColor').val($('#numPaginasColor').val() * $('#numCopys').val());
                    }
                } else {
                    $('#' + txt).val(respuesta.datos.string);
                    $('#mensaje span.textMensaje').html('ERROR: ' + respuesta.mensaje);
                    $('#mensaje').slideDown(500);
                }
                console.log(respuesta);
//                alert('Resultado: '+respuesta.mensaje+" "+respuesta.datos);
//                console.log(respuesta);
            }
        });
    },
    PaginasRepetidasEnCampos: function(txt, txt2) {
        $.ajax({
            'url': 'calcularpaginas', //Nombre de instancia del servlet.
            'type': 'POST',
            'data': {'cadena': $('#' + txt).val() + ',' + $('#' + txt2).val()},
            success: function(data) {
                var respuesta = JSON.parse(data); //Concatenamos el objeto recibido a un objeto de JSON.
                if (respuesta.codigo > 0) {
//                    $('#' + txt).val(respuesta.datos.string);                    
                } else {
                    $('#mensaje span.textMensaje').html('ERROR: ' + respuesta.mensaje);
                    $('#mensaje').slideDown(500);
                }
                console.log(respuesta);
//                alert('Resultado: '+respuesta.mensaje+" "+respuesta.datos);
//                console.log(respuesta);
            }
        });
    },
    //Inicio insertar.
    insertar: function(url, metodo, objetoJSON, divMensaje) {
        //Se oculta el div.
//        divMensaje.hide();
        $('#cargando').show();
        //Se lanza la petición ajax.
        var respuesta = null;
        $.ajax({
            //Url del servlet.
            'url': url,
            //Método a ejecutar.
            'type': metodo,
            'data': objetoJSON,
            success: function(data) {
                respuesta = JSON.parse(data);
                console.log(respuesta);
                switch (parseInt(respuesta.codigo)) {
                    case 1:
                        controlPeticiones.limpiar();
                        break;
                    default:
//                        divMensaje.addClass("alert-danger");
                        break;
                }
            },
            error: function(e) {
                console.log('Error: ' + e);
            }
        });
        return respuesta;
    },
    //Fin insertar.


    //Llenar combobox
    llenarCombox: function(url, cmbx, parametros) {
        $.ajax({
            'url': url, //Nombre de instancia del servlet.            
            'type': 'POST',
            success: function(data) {
                cmbx.html(''); //Reiniciamos y/o formateamos el contenido del elemento.                
                cmbx.append(new Option('Seleccionaa', '-1'));
                var respuesta = JSON.parse(data); //Concatenamos el objeto recibido a un objeto de JSON.                            
                $.each(respuesta.datos, function(indice, valor) {
                    cmbx.append(new Option(valor.tipoImpresion, valor.idTipoImpresion));
                });
            }
        });
    },
    //Fin llenar Combobox


    //Inicio limpiar.
    limpiar: function() {
        $("input[type='text'],input[type='password'],textarea").val('');
        $("select").val(-1);
    }
    //Fin limpiar.
};

