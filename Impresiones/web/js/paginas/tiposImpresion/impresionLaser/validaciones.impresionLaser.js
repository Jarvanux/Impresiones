var validacionesImpresionLaser = {
    validarTipoColor: function() {
//        alert('s');
        if ($('#soloBN').is(':checked')) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[0].nombre).show(500);
            $('.spanPaso').html(ObjetoPasos.pasos[0].descripcion);
            contPaso += 1;
        } else if ($('#soloColor').is(':checked')) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[0].nombre).show(500);
            $('.spanPaso').html(ObjetoPasos.pasos[0].descripcion);
            contPaso += 1;
        } else if ($('#mixto').is(':checked')) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[0].nombre).show(500);
            $('.spanPaso').html(ObjetoPasos.pasos[0].descripcion);
            contPaso = 1;
        } else {
            $('#mensaje').show(500);
            $('#mensaje span.textMensaje').html('ERROR: No has seleccionado un tipo de color.');
        }
    },
    validarTipoColorParams: function() {
        if ($('#soloBN').is(':checked')) {
            if ($('#txtCantBN').val() > 0 || $('#txtCantVarBN').val().length > 0) {
                $('#numCopys').val(1);
                $('#mensaje').slideUp(500);
                $('#' + ObjetoElementos.elementos[1].nombre).show(500);
                $('.spanPaso').html(ObjetoPasos.pasos[2].descripcion);
                if ($('#cmbxTipoBN').val() == 1) {
                    $('#numPaginasBN').val($('#txtCantBN').val());
                    $('#numTotal').val($('#numPaginasBN').val() * $('#numCopys').val());
                    $('#numCopys').focus();
                } else if ($('#cmbxTipoBN').val() == 2) {
                    controlPeticiones.calcularPaginas('txtCantVarBN', 1);
                    $('#numTotal').val($('#numPaginasBN').val() * $('#numCopys').val());
                    $('#numCopys').focus();
                }
                contPaso = 2;
            } else {
                $('#mensaje').show(500);
                $('#mensaje span.textMensaje').html('ERROR: No has ingresado un parámetro válido para la cantidad de hojas a blanco y negro.');
                $('#infoMixto #txtCanHBN').focus();
            }
        } else if ($('#soloColor').is(':checked')) {
            if ($('#txtCantColor').val() > 0 || $('#txtCantVarColor').val().length > 0) {
                $('#mensaje').slideUp(500);
                $('#' + ObjetoElementos.elementos[1].nombre).show(500);
                $('.spanPaso').html(ObjetoPasos.pasos[2].descripcion);
                if ($('#cmbxTipoColor').val() == 1) {
                    $('#numPaginasColor').val($('#txtCantColor').val());
                    $('#numTotal').val($('#numPaginasColor').val() * $('#numCopys').val());
                    $('#numCopys').focus();
                } else if ($('#cmbxTipoColor').val() == 2) {
                    controlPeticiones.calcularPaginas('txtCantVarColor', 2);
                    $('#numTotal').val($('#numPaginasColor').val() * $('#numCopys').val());
                    $('#numCopys').focus();
                }
                $('#numCopys').focus();
                contPaso = 2;
            } else {
                $('#mensaje').show(500);
                $('#mensaje span.textMensaje').html('ERROR: No has ingresado un parámetro válido para la cantidad de hojas a color.');
                $('#infoMixto #txtCantHColor').focus();
            }
        } else if ($('#mixto').is(':checked')) {
            if ($('#txtBNMixto').val().length > 0 && $('#txtColorMixto').val().length > 0) {
                $('#mensaje').slideUp(500);
                $('#' + ObjetoElementos.elementos[1].nombre).show(500);
                $('.spanPaso').html(ObjetoPasos.pasos[2].descripcion);
                $('#numCopys').focus();
                contPaso = 2;
            } else {
                $('#mensaje').show(500);
                $('#mensaje span.textMensaje').html('ERROR: No has ingresado parámetros válidos, recuerda que debes proporcionar datos para los dos campos.');
                $('#infoMixto #txtColorMixto').focus();
            }
        } else {
            $('#mensaje').show(500);
            $('#mensaje span.textMensaje').html('ERROR: No has seleccionado un tipo de color.');
        }
    },
    validarNumCopias: function() {
        if ($('#numCopys').val() > 0) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[contPaso].nombre).show(500);
            $('.spanPaso').html(ObjetoPasos.pasos[contPaso + 1].descripcion);
            if ($('#soloBN').is(':checked')) {
                $('#numTotal').val($('#txtCantBN').val() * $('#numCopys').val());
            } else if ($('#soloColor').is(':checked')) {
                $('#numTotal').val($('#txtCantColor').val() * $('#numCopys').val());
            } else if ($('#mixto').is(':checked')) {
                $('#numTotal').val($('#txtCantColor').val() * $('#numCopys').val());
            }
            contPaso = 3;
            $('#btnsPasos').css({"margin-top": "10px"});
        } else {
            $('#mensaje').show(500);
            $('#mensaje span.textMensaje').html('ERROR: El número de copias de la misma impresión debe ser mayor a 0');
            $('#numCopys').focus();
        }
    },
    validarModoImpresion: function() {
        if ($('#cmbxModoImpresion').val() > 0) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[contPaso].nombre).show(500);
            $('.spanPaso').html(ObjetoPasos.pasos[contPaso + 1].descripcion);
            contPaso = 4;
        } else {
            $('#mensaje').show(500);
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un modo de impresión.');
            $('#cmbxModoImpresion').focus();
        }
    },
    validarTamanoImpresion: function() {
        if ($('#cmbxTipoTamano').val() > 0) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[contPaso].nombre).show(500);
            $('.spanPaso').html(ObjetoPasos.pasos[contPaso + 1].descripcion);
            contPaso = 5;
            $('#cmbxTipoPapel').focus();
        } else {
            $('#mensaje').show(500);
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un tamaño de impresión.');
            $('#cmbxTipoTamano').focus();
        }
    },
    validarTipoPapel: function() {
        valorImprePosible = true;
        if ($('#cmbxTipoPapel').val() > 0) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[contPaso].nombre).show(500);
            $('.spanPaso').html(ObjetoPasos.pasos[contPaso + 1].descripcion);
            contPaso = 5;
            $('#valoresImpre').show(500);
            $('#btnSiguiente').hide();
            $('#continuarForm').show(500);
            $('#continuarForm').css({"cursor": "pointer"});
        } else {
            $('#mensaje').show(500);
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un tipo de papel.');
            $('#cmbxTipoPapel').focus();
        }
    },
    //Validaremos el segundo formulario de servicios adicionales.
    validarSegundoForm: function() {
//        alert('Se inicia la validación del segundo formulario.');
        //Validando campos anillado.
        var contador = 0;
        if ($('input[type="checkbox"]#anillado').is(':checked')) {
            var contadorAnillado = 0;
            if ($('select#cmbxColorAnillo').val() < 0) {
                //Ejecutá el error estimado.
                validacionesImpresionLaser.informe('Se han encontrado listas despegables sin seleccionar.');
                $('select#cmbxColorAnillo').css({'border': '1px solid red'});
                validacionesImpresionLaser.ocultarElementos(1);
            } else {
                contadorAnillado++;
            }
            if ($('select#cmbxColorTapas').val() < 0) {
                validacionesImpresionLaser.informe('Se han encontrado listas despegables sin seleccionar.');
                $('select#cmbxColorTapas').css({'border': '1px solid red'});
                validacionesImpresionLaser.ocultarElementos(1);
            } else {
                contadorAnillado++;
            }
            if ($('select#cmbxColorTapas2').val() < 0) {
                validacionesImpresionLaser.informe('Se han encontrado listas despegables sin seleccionar.');
                $('select#cmbxColorTapas2').css({'border': '1px solid red'});
                validacionesImpresionLaser.ocultarElementos(1);
            } else {
                contadorAnillado++;
            }

            if (contadorAnillado == 3) {
                contador++;
                peticionesImpresionLaser.consultarValorAnillado($('input[type="number"]#numAnill').val());               
            }
        } else {
            contador++;
        }

        //Validando campos plastificado
        if ($('input[type="checkbox"]#plasti').is(':checked')) {
            var contadorPlastificado = 0;
            if ($('select#cmbxPlastificado').val() < 0) {
                validacionesImpresionLaser.informe('Debes seleccionar un tipo de plastificado.');
                $('select#cmbxPlastificado').css({'border': '1px solid red'});
                validacionesImpresionLaser.ocultarElementos(2);
            } else {
                contadorPlastificado++;
            }

            if (contadorPlastificado == 1) {                
                contador++;                                
                peticionesImpresionLaser.consultarValorPlastificado($('input[type="number"]#txtPagPlastificadas').val());                                
            }
        } else {
            contador++;
        }
        //validando campos corte
        if ($('input[type="checkbox"]#servicoCorte').is(':checked')) {
//         Inicio...
            var contadorCorte = 0;
            if ($('select#cmbxSCorte').val() < 0) {
                validacionesImpresionLaser.informe('Debes seleccionar un tipo de plastificado.');
                $('select#cmbxSCorte').css({'border': '1px solid red'});                
                validacionesImpresionLaser.ocultarElementos(3);
            } else {
                contadorCorte++;
            }

            if (contadorCorte == 1) {
                contador++;
                peticionesImpresionLaser.consultarValorCorte($('input[type="number"]#numCorte').val());                
            }

        } else {
            contador++;
        }
//        alert('Valor contador: ' + contador);
        if (contador == 3) {
            return true;
        } else {
            return false;
        }
    },
    informe: function(mensaje) {
        $('div#titulo h2').html('!Atención!');
        $('div#cuerpo p').html(mensaje);
        $('#aceptar2').hide();
        $('#aceptar').hide();
        $('#aceptar3').show();
        $('#info').show();
        $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
        $('div#content div#cuerpo').css({"width": "90%", "height": "auto"});
//        $('#cancelar').hide();
    },
    ocultarElementos: function(codigo) {
        var oculta = {
            anillado: function() {
                $('select#cmbxColorAnillo').hide();
                $('select#cmbxColorTapas').hide();
                $('select#cmbxColorTapas2').hide();
                $('#td5Anillado span').hide();
                $('#td5Anillado img').hide();
                $('#td5Anillado input').hide();
                $('.txtTapas').hide();
                $('#lblcolorAnillo').hide();
            },
            plastificado: function() {
                $('select#cmbxPlastificado').hide();
                $('#td5Add').hide();
            },
            corte: function() {
                $('select#cmbxSCorte').hide();
                $('#contentCorte').hide();
            },
            cosido: function() {
            }
        }

        var muestra = {
            anillado: function() {
                $('select#cmbxColorAnillo').show();
                $('select#cmbxColorTapas').show();
                $('select#cmbxColorTapas2').show();
                $('#td5Anillado span').show();
                $('#td5Anillado img').show();
                $('#td5Anillado input').show();
                $('.txtTapas').show();
                $('#lblcolorAnillo').show();
            },
            plastificado: function() {
                $('select#cmbxPlastificado').show();
                $('#td5Add').show();
            },
            corte: function() {
                $('select#cmbxSCorte').show();
                $('#contentCorte').show();
            },
            cosido: function() {

            }
        }

        //Código: Anillado = 1, Plastificado = 2, Corte = 3; cosido = 4;
        //Determina por el código recibido que función debe mostrar.
        if (codigo == 1) {
            muestra.anillado();
            oculta.plastificado();
            oculta.corte();
        } else if (codigo == 2) {
            muestra.plastificado();
            oculta.anillado();
            oculta.corte();
        } else if (codigo == 3) {
            muestra.corte();
            oculta.anillado();
            oculta.plastificado();
        }
//Servicio Corte...

//Cosido...
//#cosidoSi
//#cosidoNo
//#smsInformativo
    }
};
