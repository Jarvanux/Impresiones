var validacionesImpresionLaser = {
    validarTipoColor: function() {
//        alert('s');
        if ($('#soloBN').is(':checked')) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[0].nombre).slideDown(500);
            $('.spanPaso').html(ObjetoPasos.pasos[0].descripcion);
            contPaso += 1;
        } else if ($('#soloColor').is(':checked')) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[0].nombre).slideDown(500);
            $('.spanPaso').html(ObjetoPasos.pasos[0].descripcion);
            contPaso += 1;
        } else if ($('#mixto').is(':checked')) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[0].nombre).slideDown(500);
            $('.spanPaso').html(ObjetoPasos.pasos[0].descripcion);
            contPaso = 1;
        } else {
            $('#mensaje').slideDown(500);
            $('#mensaje span.textMensaje').html('ERROR: No has seleccionado un tipo de color.');
        }
    },
    validarTipoColorParams: function() {
        if ($('#soloBN').is(':checked')) {
            if ($('#txtCantBN').val() > 0 || $('#txtCantVarBN').val().length > 0) {
                $('#numCopys').val(1);
                $('#mensaje').slideUp(500);
                $('#' + ObjetoElementos.elementos[1].nombre).slideDown(500);
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
                $('#mensaje').slideDown(500);
                $('#mensaje span.textMensaje').html('ERROR: No has ingresado un parámetro válido para la cantidad de hojas a blanco y negro.');
                $('#infoMixto #txtCanHBN').focus();
            }
        } else if ($('#soloColor').is(':checked')) {
            if ($('#txtCantColor').val() > 0 || $('#txtCantVarColor').val().length > 0) {
                $('#mensaje').slideUp(500);
                $('#' + ObjetoElementos.elementos[1].nombre).slideDown(500);
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
                $('#mensaje').slideDown(500);
                $('#mensaje span.textMensaje').html('ERROR: No has ingresado un parámetro válido para la cantidad de hojas a color.');
                $('#infoMixto #txtCantHColor').focus();
            }
        } else if ($('#mixto').is(':checked')) {
            if ($('#txtBNMixto').val().length > 0 && $('#txtColorMixto').val().length > 0) {
                $('#mensaje').slideUp(500);
                $('#' + ObjetoElementos.elementos[1].nombre).slideDown(500);
                $('.spanPaso').html(ObjetoPasos.pasos[2].descripcion);
                $('#numCopys').focus();
                contPaso = 2;
            } else {
                $('#mensaje').slideDown(500);
                $('#mensaje span.textMensaje').html('ERROR: No has ingresado parámetros válidos, recuerda que debes proporcionar datos para los dos campos.');
                $('#infoMixto #txtColorMixto').focus();
            }
        } else {
            $('#mensaje').slideDown(500);
            $('#mensaje span.textMensaje').html('ERROR: No has seleccionado un tipo de color.');
        }
    },
    validarNumCopias: function() {
        if ($('#numCopys').val() > 0) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[contPaso].nombre).slideDown(500);
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
            $('#mensaje').slideDown(500);
            $('#mensaje span.textMensaje').html('ERROR: El número de copias de la misma impresión debe ser mayor a 0');
            $('#numCopys').focus();
        }
    },
    validarModoImpresion: function() {
        if ($('#cmbxModoImpresion').val() > 0) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[contPaso].nombre).slideDown(500);
            $('.spanPaso').html(ObjetoPasos.pasos[contPaso + 1].descripcion);
            contPaso = 4;
        } else {
            $('#mensaje').slideDown(500);
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un modo de impresión.');
            $('#cmbxModoImpresion').focus();
        }
    },
    validarTamanoImpresion: function() {
        if ($('#cmbxTipoTamano').val() > 0) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[contPaso].nombre).slideDown(500);
            $('.spanPaso').html(ObjetoPasos.pasos[contPaso + 1].descripcion);
            contPaso = 5;
            $('#cmbxTipoPapel').focus();
        } else {
            $('#mensaje').slideDown(500);
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un tamaño de impresión.');
            $('#cmbxTipoTamano').focus();
        }
    },
    validarTipoPapel: function() {
        valorImprePosible = true;
        if ($('#cmbxTipoPapel').val() > 0) {
            $('#mensaje').slideUp(500);
            $('#' + ObjetoElementos.elementos[contPaso].nombre).slideDown(500);
            $('.spanPaso').html(ObjetoPasos.pasos[contPaso + 1].descripcion);
            contPaso = 5;
            $('#valoresImpre').slideDown(500);
            $('#btnSiguiente').hide();
            $('#continuarForm').slideDown(500);
            $('#continuarForm').css({"cursor": "pointer"});
        } else {
            $('#mensaje').slideDown(500);
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un tipo de papel.');
            $('#cmbxTipoPapel').focus();
        }
    },
    
    
    //Validaremos el segundo formulario de servicios adicionales.
    validarSegundoForm: function() {
        //Validando campos anillado.
        if ($('input[type="checkbox"]#anillado').is(':checked')) {
            if ($('select#cmbxColorAnillo').val() < 0) {
                //Ejecutá el error estimado.
                validacionesImpresionLaser.informe('Debes seleccionar un color de anillado');
                $('select#cmbxColorAnillo').css({'border': '1px solid red'});
            }            
            if ($('select#cmbxColorTapas').val() < 0) {
                validacionesImpresionLaser.informe('Debes seleccionar un color de tapas del anillado');
                $('select#cmbxColorTapas').css({'border': '1px solid red'});
            }            
            if ($('select#cmbxColorTapas2').val() < 0) {
                validacionesImpresionLaser.informe('Debes seleccionar un color de tapas del anillado');
                $('select#cmbxColorTapas2').css({'border': '1px solid red'});
            }
        }else{
            peticionesImpresionLaser.consultarValorAnillado($('input[type="number"]#numAnill').val());
        }

        //Validando campos plastificado
        if ($('input[type="checkbox"]#plasti').is(':checked')) {
            if ($('select#cmbxPlastificado').val() < 0) {
                validacionesImpresionLaser.informe('Debes seleccionar un tipo de plastificado.');
                $('select#cmbxColorTapas2').css({'border': '1px solid red'});
            }
        }else{
            peticionesImpresionLaser.consultarValorPlastificado($('input[type="number"]#txtPagPlastificadas').val());
        }
        //validando campos corte
        if ($('input[type="checkbox"]#servicoCorte').is(':checked')) {
            if ($('select#cmbxSCorte').val() < 0) {
                validacionesImpresionLaser.informe('Debes seleccionar unt tipo de corte.');
                $('select#cmbxSCorte').css({'border': '1px solid red'});
            }
        }else{
            peticionesImpresionLaser.consultarValorCorte(('input[type="number"]#numCorte').val());
        }    
    },
    informe: function(mensaje) {
        $('div#titulo h2').html('!Atención!');
        $('div#cuerpo p').html(mensaje);
        $('#aceptar2').hide();
        $('#aceptar').hide();
        $('#aceptar3').show();
        $('#info').slideDown(500);
        $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
        $('div#content div#cuerpo').css({"width": "400px", "height": "auto"});        
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
            corte: function(){
                $('select#cmbxSCorte').hide();
                $('#contentCorte').hide();                
            },
            cosido: function() {
            }
        }
        
        var muestra = {
            anillado: function() {
                $('select#cmbxColorAnillo').show();
                $('#td5Add').show();                
            },
            plastificado: function() {
                $('select#cmbxPlastificado').show();
                $('#td5Add').show();                
            },
            corte: function(){
                $('select#cmbxSCorte').show();
                $('#contentCorte').show();                
            },
            cosido: function() {

            }
        }
        
        //Código: Anillado = 1, Plastificado = 2, Corte = 3; cosido = 4;
        //Determina por el código recibido que función debe mostrar.
        if(codigo == 1){
            muestra.anillado();
            oculta.plastificado();
            oculta.corte();
        }else if(codigo == 2){
            muestra.plastificado();
            oculta.anillado();
            oculta.corte();
        }else if(codigo == 3){
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
