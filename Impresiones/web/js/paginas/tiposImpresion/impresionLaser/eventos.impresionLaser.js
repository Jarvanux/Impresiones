/* 
 * Eventos...
 */
valorImprePosible = false;
tipoColorSeleccionado = 0;

var eventosImpresionLaer = {
    calcularPrecioImpresion: function() {
        //Evaluamos que todo el formulario esté completo para brindarle la 
        //información necesaria al sistema para que tome los respectivos 
        //calculos.
        if (valorImprePosible) {
            if ($('#soloBN').is(':checked')) {
                eventosImpresionLaer.precioImpresionUnSoloTIPO();
            } else if ($('#soloColor').is(':checked')) {
                eventosImpresionLaer.precioImpresionUnSoloTIPO();
            } else if ($('#mixto').is(':checked')) {
                eventosImpresionLaer.precioImpresionDosTipos();
            }
        }
    },
    precioImpresionUnSoloTIPO: function() {
        var datosValidos = 0;

        if ($('#cmbxTipoPapel').val() > 0) {
            datosValidos += 1;
        } else {
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un tipo de papel de impresión.');
            $('#mensaje').slideDown(500);
        }

        if ($('#cmbxTipoTamano').val() > 0) {
            datosValidos += 1;
        } else {
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un tamaño de impresión.');
            $('#mensaje').slideDown(500);
        }

        if ($('#cmbxModoImpresion').val() > 0) {
            datosValidos += 1;
        } else {
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un modo de impresión.');
            $('#mensaje').slideDown(500);
        }

        if ($('#numCopys').val() > 0) {
            datosValidos += 1;
        } else {
            $('#mensaje span.textMensaje').html('ERROR: La cantidad de número copias debe ser  mayor a 0.');
            $('#mensaje').slideDown(500);
        }

        if ($('#soloBN').is(':checked')) {
            if ($('#numPaginasBN').val() > 0) {
                datosValidos += 1;
            } else {
                $('#mensaje span.textMensaje').html('ERROR: La cantidad de número de hojas a blanco y negro debe ser  mayor a 0.');
                $('#mensaje').slideDown(500);
            }
        }

        if ($('#soloColor').is(':checked')) {
            if ($('#numPaginasColor').val() > 0) {
                datosValidos += 1;
            } else {
                $('#mensaje span.textMensaje').html('ERROR: La cantidad de número de hojas a color debe ser  mayor a 0.');
                $('#mensaje').slideDown(500);
            }
        }


        //Validamos si la variable datosValidos es = a 5, de lo contrario, se
        //genera un error.

        if (datosValidos == 5) {
            peticionesImpresionLaser.consultarValorImpre();
        }
    },
    precioImpresionDosTipos: function() {
        var datosValidos = 0;

        if ($('#cmbxTipoPapel').val() > 0) {
            datosValidos += 1;
        } else {
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un tipo de papel de impresión.');
            $('#mensaje').slideDown(500);
        }

        if ($('#cmbxTipoTamano').val() > 0) {
            datosValidos += 1;
        } else {
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un tamaño de impresión.');
            $('#mensaje').slideDown(500);
        }

        if ($('#cmbxModoImpresion').val() > 0) {
            datosValidos += 1;
        } else {
            $('#mensaje span.textMensaje').html('ERROR: Debes seleccionar un modo de impresión.');
            $('#mensaje').slideDown(500);
        }

        if ($('#numCopys').val() > 0) {
            datosValidos += 1;
        } else {
            $('#mensaje span.textMensaje').html('ERROR: La cantidad de número copias debe ser  mayor a 0.');
            $('#mensaje').slideDown(500);
        }

        if ($('#soloBN').is(':checked')) {
            if ($('#numPaginasBN').val() > 0) {
                datosValidos += 1;
            } else {
                $('#mensaje span.textMensaje').html('ERROR: La cantidad de número de hojas a blanco y negro debe ser  mayor a 0.');
                $('#mensaje').slideDown(500);
            }
        }

        if ($('#soloColor').is(':checked')) {
            if ($('#numPaginasColor').val() > 0) {
                datosValidos += 1;
            } else {
                $('#mensaje span.textMensaje').html('ERROR: La cantidad de número de hojas a color debe ser  mayor a 0.');
                $('#mensaje').slideDown(500);
            }
        }
        if ($('#mixto').is(':checked')) {
            if (($('#numPaginasBN').val() > 0) && ($('#numPaginasColor').val() > 0)) {
                datosValidos += 1;
            } else {
                $('#mensaje span.textMensaje').html('ERROR: La cantidad de número de hojas a color debe ser  mayor a 0.');
                $('#mensaje').slideDown(500);
            }
        }


        //Validamos si la variable datosValidos es = a 5, de lo contrario, se
        //genera un error.

        if (datosValidos == 5) {
//            totalPaginas,tipoColor,txtUnit
            peticionesImpresionLaser.consultarValorImpreMix2(0);
            peticionesImpresionLaser.consultarValorImpreMix2(1);

//            peticionesImpresionLaser.consultarValorImpreMix($('#numTotalBN').val(), 0, 'valorBN');
//            peticionesImpresionLaser.consultarValorImpreMix($('#numTotalColor').val(), 1, 'valorColor');                                   
        }
    },
//    cambiarImg: function(idImg, color) {
//        $(idImg).remove("src");
//        if (color == 1) {
//            $(idImg).attr({"src": ""});
//        }
//        if (color == 2) {
//            $(idImg).attr({"src": "img/data/paginas/impresionLaser/conversionesIMG/imgAzul1.png"});
//        }
//        
//    },
    cargarTerminos: function(pagina) {
        $("#contentD3")
                .html('')
                .append($('#cargador').clone().show());
        $.post(pagina, function(data) {
            $("#contentD3").html(data);
        });
    },
    cargarSubForm: function(pagina) {
        $("div#cuerpo p")
                .html('')
                .append($('#cargador').clone().show());
        $.post(pagina, function(data) {
            $("div#cuerpo p").html(data);
        });
    },
    lnkAtras: function() {
        $('#contentD3').hide();
        $('input[type="number"]#valorAnillado').val(0);
        $('input[type="number"]#valorPlastificado').val(0);
        $('input[type="number"]#valorCorte').val(0);
        $('input[type="number"]#valorCosido').val(0);
        $('input[type="number"]#valorTotalConServicios').val(0);
        $('#contentD2').show();
    },
    modalSubForm: function() {
        $('div#titulo h2').html('Configuración de servicios adicionales');
        eventosImpresionLaer.cargarSubForm('paginas/tiposImpresion/subFormModalLaser.html');
        $('div#content').css({"width": "800px", "height": "500px", "margin-top": "20px"});
        $('body').css({"background": "black"});
        $('div#content div#cuerpo').css({"height": "390px", "overflow": "auto", "width": "90%"});
        $('div#info div#cuerpo').css({"overflow": "auto"});
        $('#aceptar').hide();
        $('#aceptar2').hide();
        $('#aceptar3').show();
        $('#info').slideDown(500);
    },
    eventos: function() {
        //Evento finalizar formulario. - guardará los datos ingresados en la BD.               
//          $('#' + data.id).css({"border": "1px solid #999999"});

        $('select').change(function(event) {
            if ($('#' + event.currentTarget.id).val() > 0) {
                $('#' + event.currentTarget.id).css({"border": "1px solid #999999"});
            }
        });
        $('#btnFin').click(function() {
            eventosImpresionLaer.cargarTerminos('paginas/tiposImpresion/terminosYcondiciones.html');
            if (validacionesImpresionLaser.validarSegundoForm()) {
                $('#contentD2').hide();
                $('#contentD3').show();
                $('div#nums').hide();
            } else {
            }
        });
        $('#btnFinModal').click(function() {
            if (validacionesImpresionLaser.validarSegundoForm()) {
                $('#contentD2').hide();
                $('#contentD3').show();
                eventosImpresionLaer.cargarTerminos('paginas/tiposImpresion/terminosYcondiciones.html');
            } else {
                alert('Sucedió algo.');
            }
        });

        $('#cambiar').css({"cursor": "pointer"});
        $('#cambiar').click(function() {
            $('div#titulo h2').html('!Atención!');
            $('div#cuerpo p').html('Los datos ingresados de todo el formulario se perderán al completar la operación ¿estás deacuerdo?');
            $('#aceptar2').hide();
            $('#aceptar3').hide();
            $('#aceptar').show();
            $('#info').slideDown(500);
            $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
            $('div#content div#cuerpo').css({"width": "90%", "height": "auto"});
        });
        $('#aceptar').click(function() {
            inicio.cargar('paginas/servicios/impresion.html');
        });
        $('#aceptar2').click(function() {
            $("#info").hide();
        });
        $('#aceptar3').click(function() {
            $("#info").hide();
        });
        $('.btn-cancelar').click(function() {
            $("#info").hide();
        });
        $('#imgCerrar').click(function() {
            $("#info").hide();
        });

        $('#btnAceptarTerminos').click(function() {

        });
        //Fin envento guardar form.
        //Eventos cambio de color de imagenes.
        $('#cmbxColorAnillo').change(function() {
            peticionesImpresionLaser.consultarColoresAnilloPorID($('#cmbxColorAnillo').val());
            $('#img1').attr({"src": colorAnilloConsultado.urlImg});
            $('#img1-v').attr({"src": colorAnilloConsultado.urlImg});
            $('#img1_2').attr({"src": colorAnilloConsultado.urlImg});
            $('#img1_2-v').attr({"src": colorAnilloConsultado.urlImg});
        });

        $('#cmbxSCorte').change(function() {
            if ($('#cmbxSCorte').val() == 1) {
                $('#imgCorte').attr({'src': 'img/data/paginas/impresionLaser/tipo-corte/servicio de corte-01.jpg'});
                $('#imgCorte-v').attr({'src': 'img/data/paginas/impresionLaser/tipo-corte/servicio de corte-01.jpg'});
            } else if ($('#cmbxSCorte').val() == 2) {
                $('#imgCorte').attr({'src': 'img/data/paginas/impresionLaser/tipo-corte/servicio de corte-02.jpg'});
                $('#imgCorte-v').attr({'src': 'img/data/paginas/impresionLaser/tipo-corte/servicio de corte-02.jpg'});
            } else if ($('#cmbxSCorte').val() == 3) {
                $('#imgCorte').attr({'src': 'img/data/paginas/impresionLaser/tipo-corte/servicio de corte-03.jpg'});
                $('#imgCorte-v').attr({'src': 'img/data/paginas/impresionLaser/tipo-corte/servicio de corte-03.jpg'});
            } else if ($('#cmbxSCorte').val() == 4) {
                $('#imgCorte').attr({'src': 'img/data/paginas/impresionLaser/tipo-corte/servicio de corte-04.jpg'});
                $('#imgCorte-v').attr({'src': 'img/data/paginas/impresionLaser/tipo-corte/servicio de corte-04.jpg'});
            } else {
                $('#imgCorte').removeAttr("src");
                $('#imgCorte-v').removeAttr("src");
            }
        });

        $('#cmbxColorTapas').change(function() {
            peticionesImpresionLaser.consultarColoresPorID($('#cmbxColorTapas').val());
            $('#img5').attr({"src": colorConsultado.urlImg + '1.png'});
            $('#img5-v').attr({"src": colorConsultado.urlImg + '1.png'});
        });
        $('#cmbxColorTapas2').change(function() {
            peticionesImpresionLaser.consultarColoresPorID($('#cmbxColorTapas2').val());
            $('#img10').attr({"src": colorConsultado.urlImg + '2.png'});
            $('#img10-v').attr({"src": colorConsultado.urlImg + '2.png'});
        });
        //Fin eventos cambio de color imagenes.
        //Eventos form2
        $('#btnAtras').click(function() {
            $('#contentD2').hide();
            $('#contentD').show();
        });
        //Fin eventos form2
        //Eventos de los checked's y radios del formulario 2.        
        //Eventos Anillado.
        $('#anilladoSi').click(function() {
            $("#anillado").prop("checked", true);
            $('#anilladoImgs').show();
            anillado();
        });

        $('#anilladoNo').click(function() {
            $("#anillado").prop("checked", false);
            anillado();
            $('#anilladoImgs').hide();
        });

        $('#anillado').click(function() {
            $("#anilladoSi").prop("checked", true);
            anillado();
        });

        function anillado() {
            if ($('#anillado').is(':checked')) {
                $("#anilladoSi").prop("checked", true);
                $("#colorAnillo").show();
                $("#colorTapas").show();
                $("#colorTapas2").show();
                $('#anilladoImgs').show();
                $('#vistaAnillado').show();
                $('#corteImgs').hide();
                $('#td5Anillado span,#numAnill,#td5Anillado img.imgAyuda').show();
                validacionesImpresionLaser.ocultarElementos(1);
            } else {
                $("#anilladoNo").prop("checked", true);
                $("#colorAnillo").hide();
                $("#colorTapas").hide();
                $("#colorTapas2").hide();
                $('#anilladoImgs').hide();
                $('#vistaAnillado').hide();
                $('#td5Anillado span,#numAnill,#td5Anillado img.imgAyuda').hide();
                if ($('#servicoCorte').is(':checked')) {
                    $('#corteImgs').show();
                }
            }
        }
        //Fin anillado.


        //Eventos plastificado.        
        $('#plastificadoSi').click(function() {
            $("#plastificado").prop("checked", true);
            plastificado();
        });

        $('#plastificadoNo').click(function() {
            $("#plastificado").prop("checked", false);
            plastificado();
        });

        $('#plastificado').click(function() {
            $("#plastificadoSi").prop("checked", true);
            plastificado();
        });

        function plastificado() {
            if ($('#plastificado').is(':checked')) {
                $("#plastificadoSi").prop("checked", true);
            } else {
                $("#plastificadoNo").prop("checked", true);
            }
        }

        //Fin plastificado.

        //Eventos corte.
        $('#servicoCorteSi').click(function() {
            $('#servicoCorte').prop("checked", true);
            corte();
        });
        $('#servicoCorteNo').click(function() {
            $('#servicoCorte').prop("checked", false);
            corte();
        });
        $('#servicoCorte').click(function() {
            corte();
        });
        function corte() {
            if ($('#servicoCorte').is(':checked')) {
                $("#servicoCorteSi").prop("checked", true);
                $("#cmbxSCorte").show();
                $("#anilladoImgs").hide();
                $("#corteImgs").show();
                $("#servicioCorte").show();
                $("#contentCorte").show();
                validacionesImpresionLaser.ocultarElementos(3);
            } else {
                $("#servicoCorteNo").prop("checked", true);
                $("#cmbxSCorte").hide();
                $("#corteImgs").hide();
                $("#servicioCorte").hide();
                $("#contentCorte").hide();
            }
        }
        //Fin corte.

        //Plastificado
        $('#plastiSi').click(function() {
            $('#plasti').prop("checked", true);
            plasti();
        });
        $('#plastiNo').click(function() {
            $('#plasti').prop("checked", false);
            plasti();
        });
        $('#plasti').click(function() {
            plasti();
        });
        function plasti() {
            if ($('#plasti').is(':checked')) {
                $("#plastiSi").prop("checked", true);
                $("#cmbxPlastificado").show();
                $('#td5Add').show();
                validacionesImpresionLaser.ocultarElementos(2);
            } else {
                $("#plastiNo").prop("checked", true);
                $("#cmbxPlastificado").hide();
                $('#td5Add').hide();
            }
        }
        //Fin Plastificado    

        //TipoRevista
        $('#cosidoSi').click(function() {
            $('#cosido').prop("checked", true);
            cosido();
        });
        $('#cosidoNo').click(function() {
            $('#cosido').prop("checked", false);
            cosido();
        });
        $('#cosido').click(function() {
            cosido();
        });
        function cosido() {
            if ($('#cosido').is(':checked')) {
                $("#cosidoSi").prop("checked", true);
                $("#smsInformativo").show();
            } else {
                $("#cosidoNo").prop("checked", true);
                $("#smsInformativo").hide();
            }
        }
        //Fin tipoRevista

        $('#numAnill').change(function() {
            if ($('#numAnill').val() > $('#numCopys2').val()) {
//                $('div#content').css({"width": "400px", "height": "auto", "margin-top": "30px"});
                $('div#titulo h2').html('!Atención!');
                $('div#cuerpo p').html('El número de anillados no puede superar el número de copias seleccionado para la impresión, si desea cambiar el número de copias use el botón atrás de la parte inferior.');
                $('#numAnill').val($('#numCopys').val());
                $('#aceptar').hide();
                $('#aceptar2').hide();
                $('#aceptar3').show();
                $('#info').slideDown(500);
                $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
                $('div#content div#cuerpo').css({"width": "90%", "height": "auto"});
            } else if ($('#numAnill').val() < 1) {
                $('#numAnill').val($('#numCopys').val());
                $('div#titulo h2').html('!Atención!');
                $('div#cuerpo p').html('El número de libros anillados debe ser mayor a 0.');
                $('#aceptar').hide();
                $('#aceptar2').hide();
                $('#aceptar3').show();
                $('#info').slideDown(500);
                $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
                $('div#content div#cuerpo').css({"width": "90%", "height": "auto"});
            }
        });
        //Fin eventos formulario 2.
        $('#continuarForm').click(function() {
            $('#valorImpresionPedido1').html('Valor: ' + $('#valorTotal').html());
            $('#valorUnitario2').html($('#valorUnitario').html());
            $('#valorTotal2').html($('#valorTotal').html());
            $('#contentD').css({'display': 'none'});
            $('#contentD2').show();
            $('#numCopys2').val($('#numCopys').val());
            $('#numCopys2').val($('#numCopys').val());
            $('#numAnill').val($('#numCopys').val());
            $('#txtPagPlastificadas').val(parseInt($('#numTotalBN').val()) + parseInt($('#numTotalColor').val()));
            $('#numCorte').val(parseInt($('#numTotalBN').val()) + parseInt($('#numTotalColor').val()));
            if ($('#soloBN').is(':checked')) {
                $('#totalImpre2').val($('#numTotalBN').val());
            } else if ($('#soloColor').is(':checked')) {
                $('#totalImpre2').val($('#numTotalColor').val());
            } else if ($('#mixto').is(':checked')) {
                $('#totalImpre2').val($('#numTotalBN').val() + ' || ' + $('#numTotalColor').val());
            } else {
                $('#totalImpre2').val(0);
            }

            $('#nums').slideDown(500);
        });

        $('#txtPagPlastificadas').change(function() {
            if ($('#txtPagPlastificadas').val() > (parseInt($('#numTotalBN').val()) + parseInt($('#numTotalColor').val()))) {
//                $('div#content').css({"width": "400px", "height": "auto", "margin-top": "30px"});
                $('div#titulo h2').html('!Atención!');
                $('div#cuerpo p').html('El número de páginas no puede superar el número total de páginas de la misma impresión.');
                $('#txtPagPlastificadas').val(parseInt($('#numTotalBN').val()) + parseInt($('#numTotalColor').val()));
                $('#aceptar').hide();
                $('#aceptar2').hide();
                $('#aceptar3').show();
                $('#info').slideDown(500);
                $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
                $('div#content div#cuerpo').css({"width": "90%", "height": "auto"});
            } else if ($('#txtPagPlastificadas').val() < 1) {
                $('#txtPagPlastificadas').val(parseInt($('#numTotalBN').val()) + parseInt($('#numTotalColor').val()));
                $('div#titulo h2').html('!Atención!');
                $('div#cuerpo p').html('El número de páginas debe ser mayor a 0.');
                $('#aceptar').hide();
                $('#aceptar2').hide();
                $('#aceptar3').show();
                $('#info').slideDown(500);
                $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
                $('div#content div#cuerpo').css({"width": "90%", "height": "auto"});
            }
        });
        $('#numCorte').change(function() {
            if ($('#numCorte').val() > (parseInt($('#numTotalBN').val()) + parseInt($('#numTotalColor').val()))) {
//                $('div#content').css({"width": "400px", "height": "auto", "margin-top": "30px"});
                $('div#titulo h2').html('!Atención!');
                $('div#cuerpo p').html('El número de páginas no puede superar el número total de páginas de la impresión.');
                $('#numCorte').val(parseInt($('#numTotalBN').val()) + parseInt($('#numTotalColor').val()));
                $('#aceptar').hide();
                $('#aceptar2').hide();
                $('#aceptar3').show();
                $('#info').slideDown(500);
                $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
                $('div#content div#cuerpo').css({"width": "90%", "height": "auto"});
            } else if ($('#numCorte').val() < 1) {
                $('#numCorte').val(parseInt($('#numTotalBN').val()) + parseInt($('#numTotalColor').val()));
                $('div#titulo h2').html('!Atención!');
                $('div#cuerpo p').html('El número de páginas debe ser mayor a 0.');
                $('#aceptar').hide();
                $('#aceptar2').hide();
                $('#aceptar3').show();
                $('#info').slideDown(500);
                $('div#content').css({"width": "400px", "height": "auto", "margin-top": "15%"});
                $('div#content div#cuerpo').css({"width": "90%", "height": "auto"});
            }
        });


        $('#txtCantVarBN').change(function() {
            $('#mensaje').slideUp(500);
            controlPeticiones.calcularPaginas('txtCantVarBN', 1);
            eventosImpresionLaer.calcularPrecioImpresion();
        });
        $('#txtCantVarColor').change(function() {
            $('#mensaje').slideUp(500);
            controlPeticiones.calcularPaginas('txtCantVarColor', 2);
            eventosImpresionLaer.calcularPrecioImpresion();
        });

        $('#txtBNMixto').change(function() {
            $('#mensaje').slideUp(500);
            controlPeticiones.calcularPaginas('txtBNMixto', 1);
            controlPeticiones.PaginasRepetidasEnCampos('txtBNMixto', 'txtColorMixto');
            eventosImpresionLaer.calcularPrecioImpresion();
        });
        $('#txtColorMixto').change(function() {
            $('#mensaje').slideUp(500);
            controlPeticiones.calcularPaginas('txtColorMixto', 2);
            controlPeticiones.PaginasRepetidasEnCampos('txtBNMixto', 'txtColorMixto');
            eventosImpresionLaer.calcularPrecioImpresion();
        });


        $('#txtCantBN').keydown(function(event) {
            return tipoCampos.permite(event, 'num');
        });
        $('#txtCantColor').keydown(function(event) {
            return tipoCampos.permite(event, 'num');
        });

//            if(String.fromCharCode(event.keyCode) == ","){
//                globalColor += 1;
//                $('.txtTolClr').html(globalColor);                
//            }
//        $('#txtCantVarColor').keypress(function(event) {
//            calcularPaginas(String.fromCharCode(event.keyCode));
//            return tipoCampos.permite(event, 'nl');
//        });
//        $('#txtCantVarBN').keypress(function(event) {
//            calcularPaginasBN(String.fromCharCode(event.keyCode));
//            return tipoCampos.permite(event, 'nl');
//        });
//        $('#txtBNMixto').keypress(function(event) {
//            calcularPaginas(String.fromCharCode(event.keyCode));
//            return tipoCampos.permite(event, 'nl');
//        });
//        $('#txtColorMixto').keypress(function(event) {
//            calcularPaginas(String.fromCharCode(event.keyCode));
//            return tipoCampos.permite(event, 'nl');
//        });
//        $('#txtBNMixto').keypress(function(event) {
//            calcularPaginasBNMix(String.fromCharCode(event.keyCode));
//            return tipoCampos.permite(event, 'nl');
//        });
//        $('#txtColorMixto').keypress(function(event) {
//            calcularPaginasColorMix(String.fromCharCode(event.keyCode));
//            return tipoCampos.permite(event, 'nl');
//        });


        $('#txtBNMixto').keypress(function(event) {
            return tipoCampos.permite(event, 'nl');
        });
        $('#txtColorMixto').keypress(function(event) {
            return tipoCampos.permite(event, 'nl');
        });
        $('#numCopys').keypress(function(event) {
            return tipoCampos.permite(event, 'nl');
        });
        $('#numCopys').keydown(function(event) {
            return tipoCampos.permite(event, 'num');
        });
        $('#txtCantVarBN').keypress(function(event) {
            return tipoCampos.permite(event, 'nl');
        });
        $('#txtCantVarColor').keypress(function(event) {
            return tipoCampos.permite(event, 'nl');
        });

//        function calcularPaginasBN(ultimaTecla) {
//            var cadena = $('#txtCantVarBN').val();
//            var num = cadena.split(",");
//            $('.txtTolBN').html(num.length);
//        }
//        function calcularPaginasBNMix(ultimaTecla) {
//            var cadena = $('#txtBNMixto').val();
//            var num = cadena.split(",");
//            $('.txtTolBN').html(num.length);
//        }
//        function calcularPaginasColorMix(ultimaTecla) {
//            var cadena = $('#txtColorMixto').val();
//            var num = cadena.split(",");
//            $('.txtTolClr').html(num.length);
//        }


        $('#btnCargar,#txtArchivosCargados,#imgCargar').click(function() {
            $('#fileUP').click();
        });
        $('#imgCloseHelp').click(function() {
            $('#pop-Ayuda').hide();
        });


        $('.ayuda').mouseover(function() {
            $('#pop-Ayuda').show();
            $('#pop-Ayuda').focus();
            if ($('#cmbxTipoBN').val() == 1) {
            } else if ($('#cmbxTipoBN').val() == 2) {
                $('#txtInfoBN').html('Hola que hace!.' +
                        '');
            }
        });


        $('.txtAtras').click(function() {
            $('#cmbxTipoBN').val(-1);
            $('#cmbxTipoColor').val(-1);
            $('.txtInfoBN').html('Debes seleccionar la forma en la que deseas ingresar las hojas que vas a imprimir.<br/><br/><b>Opciones</b><br/> - Todo el archivo.</br> - Algunas las hojas del archivo.');
            $('#lblSelecBN').slideDown(500);
            $('#cmbxTipoBN').slideDown(500);
            $('#lblCantBN').css({"display": "none"});
            $('#txtCantBN').css({"display": "none"});
            $('#imgRegresarBN').css({"display": "none"});
            $('#txtAtras').css({"display": "none"});
            $('#imgRegresarBN2').css({"display": "none"});
            $('#txtAtras2').css({"display": "none"});
            $('#txtBNVar').css({"display": "none"});
            $('#txtCantVarBN').css({"display": "none"});

            $('.txtInfoColor').html('Debes seleccionar la forma en la que deseas ingresar las hojas que vas a imprimir.<br/><br/><b>Opciones</b><br/> - Todo el archivo.</br> - Algunas las hojas del archivo.');
            $('#lblSelecColor').slideDown(500);
            $('#cmbxTipoColor').slideDown(500);
            $('#lblCantColor').css({"display": "none"});
            $('#txtCantColor').css({"display": "none"});
            $('#imgRegresarColor').css({"display": "none"});
            $('#txtAtras').css({"display": "none"});
            $('#imgRegresarColor2').css({"display": "none"});
            $('#imgRegresarColor2').css({"display": "none"});
            $('#txtAtras2').css({"display": "none"});
            $('#txtColorVar').css({"display": "none"});
            $('#txtCantVarColor').css({"display": "none"});
            $('#txtAtrasColor').css({"display": "none"});
            $('#txtAtrasColor2').css({"display": "none"});

        });

        $('.imgRegresar').click(function() {
            $('#cmbxTipoBN').val(-1);
            $('#cmbxTipoColor').val(-1);
            $('.txtInfoBN').html('Debes seleccionar la forma en la que deseas ingresar las hojas que vas a imprimir.<br/><br/><b>Opciones</b><br/> - Todo el archivo.</br> - Algunas hojas del archivo.');
            $('#lblSelecBN').slideDown(500);
            $('#cmbxTipoBN').slideDown(500);
            $('#lblCantBN').css({"display": "none"});
            $('#txtCantBN').css({"display": "none"});
            $('#imgRegresarBN').css({"display": "none"});
            $('#txtAtras').css({"display": "none"});
            $('#imgRegresarBN2').css({"display": "none"});
            $('#txtAtras2').css({"display": "none"});
            $('#txtBNVar').css({"display": "none"});
            $('#txtCantVarBN').css({"display": "none"});

            $('.txtInfoColor').html('Debes seleccionar la forma en la que deseas ingresar las hojas que vas a imprimir.<br/><br/><b>Opciones</b><br/> - Todo el archivo.</br> - Algunas hojas del archivo.');
            $('#lblSelecColor').slideDown(500);
            $('#cmbxTipoColor').slideDown(500);
            $('#lblCantColor').css({"display": "none"});
            $('#txtCantColor').css({"display": "none"});
            $('#imgRegresarColor').css({"display": "none"});
            $('#imgRegresarColor2').css({"display": "none"});
            $('#txtAtras').css({"display": "none"});
            $('#imgRegresarColor2').css({"display": "none"});
            $('#txtAtras2').css({"display": "none"});
            $('#txtColorVar').css({"display": "none"});
            $('#txtCantVarColor').css({"display": "none"});
            $('#txtAtrasColor').css({"display": "none"});
            $('#txtAtrasColor2').css({"display": "none"});

        });

        $('#cmbxTipoBN').change(function() {
            if ($('#cmbxTipoBN').val() == 1) {
                $('.txtInfoBN').html('Debes ingresar con un número entero la cantidad de hojas que contiene el archivo.<br/><br/> <b>Nota:</b>todas las páginas del mismo serán cobradas, si quieres especificar que hojas deseas imprimir usa la segunda opción.');
                $('#lblSelecBN').css({"display": "none"});
                $('#cmbxTipoBN').css({"display": "none"});
                $('#lblCantBN').slideDown(500);
                $('#txtCantBN').slideDown(500);
                $('#imgRegresarBN').slideDown(500);
                $('#txtAtras').slideDown(500);
                $('#txtCantBN').focus();
                $('#descripParamas').hide();
            } else if ($('#cmbxTipoBN').val() == 2) {
                $('.txtInfoBN').html('<b>Separación de Hojas (por coma):</b><br/><b> - Ejemplo:</b> 1,2,3,4 <br/>' +
                        '<br/><b>Incluir Rangos (Pag inicial-Pag final):</b><br/>' +
                        '<b> - Ejemplo:</b> 2-10, 11-20.<br/>' +
                        '<b><br/>Ejemplos concretos:</b><br/>' +
                        '<b> - Ejemplo 1:</b> Tienes un archivo de 10 hojas<br/>' +
                        'y Deseas imprimir la pag 1, la 3 y la 5.' +
                        ' <b>Solución:</b>' +
                        '1,3,5.' +
                        '<br/><br/><b> - Ejemplo 2:</b>Tienes un archivo de 20 hojas<br/>' +
                        'y deseas imprimir de la pág 1 a la pág 5 y de la 11 a la 15.<br/>' +
                        '<b>Solución:</b>' +
                        '1-5, 11-15<br/><br/>' +
                        '<b>Nota:</b>También Puedes integrar las dos opciones, ejemplo; páginas 1,2, y 3 al 10: ' +
                        '1,2,3-10');
                $('#lblSelecBN').css({"display": "none"});
                $('#cmbxTipoBN').css({"display": "none"});
                $('#txtBNVar').slideDown(500);
                $('#txtCantVarBN').slideDown(500);
                $('#imgRegresarBN2').slideDown(500);
                $('#txtAtras2').slideDown(500);
                $('#descripParamas').slideDown(500);
                $('#div2').hide();
                $('#div1').show();
            }
        });

        $('#cmbxTipoColor').change(function() {
            if ($('#cmbxTipoColor').val() == 1) {
                $('.txtInfoColor').html('Debes ingresar con un número entero la cantidad de hojas que contiene el archivo.<br/><br/> <b>Nota:</b>todas las páginas del mismo serán cobradas, si quieres especificar que hojas deseas imprimir usa la segunda opción.');
                $('#lblSelecColor').css({"display": "none"});
                $('#cmbxTipoColor').css({"display": "none"});
                $('#imgRegresarColor2').css({"display": "none"});
                $('#lblCantColor').slideDown(500);
                $('#txtCantColor').slideDown(500);
                $('#imgRegresarColor').slideDown(500);
                $('#txtAtrasColor').slideDown(500)
                $('#descripParamas').hide();
            } else if ($('#cmbxTipoColor').val() == 2) {
                $('.txtInfoColor').html('<b>Separación de Hojas (por coma):</b><br/><b> - Ejemplo:</b> 1,2,3,4 <br/>' +
                        '<br/><b>Incluir Rangos (Pag inicial-Pag final):</b><br/>' +
                        '<b> - Ejemplo:</b> 2-10, 11-20.<br/>' +
                        '<b><br/>Ejemplos concretos:</b><br/>' +
                        '<b> - Ejemplo 1:</b> Tienes un archivo de 10 hojas<br/>' +
                        'y Deseas imprimir la pag 1, la 3 y la 5.' +
                        ' <b>Solución:</b>' +
                        '1,3,5.' +
                        '<br/><br/><b> - Ejemplo 2:</b>Tienes un archivo de 20 hojas<br/>' +
                        'y deseas imprimir de la pág 1 a la pág 5 y de la 11 a la 15.<br/>' +
                        '<b>Solución:</b>' +
                        '1-5, 11-15<br/><br/>' +
                        '<b>Nota:</b>También Puedes integrar las dos opciones, ejemplo; páginas 1,2, y 3 al 10: ' +
                        '1,2,3-10');
                $('#lblSelecColor').css({"display": "none"});
                $('#cmbxTipoColor').css({"display": "none"});
                $('#txtColorVar').slideDown(500);
                $('#txtCantVarColor').slideDown(500);
                $('#imgRegresarColor2').slideDown(500);
                $('#txtAtras2').slideDown(500);
                $('#txtAtrasColor2').slideDown(500);
                $('#descripParamas').slideDown(500);
                $('#div1').hide();
                $('#div2').show();
            }
        });

        $('#numCopys').keypress(function(event) {
            return aceptaSoloNumeros(event);
        });


        $('#txtCantBN').change(function() {
            $('#numPaginasBN').val($('#txtCantBN').val());
            $('#numTotalBN').val($('#numPaginasBN').val() * $('#numCopys').val());
            $('#mensaje').slideUp(500);
            if ($('#txtCantBN').val() < 0) {
                $('#txtCantBN').val(0);
            }
            eventosImpresionLaer.calcularPrecioImpresion();
        });
        $('#txtCantColor').change(function() {
            $('#numPaginasColor').val($('#txtCantColor').val());
            $('#numTotalColor').val($('#numPaginasColor').val() * $('#numCopys').val());
            $('#mensaje').slideUp(500);
            if ($('#txtCantColor').val() < 0) {
                $('#txtCantColor').val(0);
            }
            eventosImpresionLaer.calcularPrecioImpresion();
        });
//        $('#txtCantBN').keypress(function(event) {
//            return aceptaSoloNumeros(event);
////            $('#mensaje').slideUp(500);
////            eventosImpresionLaer.calcularPrecioImpresion();
//        });
//        $('#txtCantColor').keypress(function(event) {
//            return aceptaSoloNumeros(event);
//            $('#mensaje').slideUp(500);
//        });
//        $('#txtCantBNMix').keypress(function(event) {
//            return aceptaNumerosYSeparadores(event);
//            $('#mensaje').slideUp(500);
//        });
//        $('#txtCantColorMix').keypress(function(event) {
//            return aceptaNumerosYSeparadores(event);
//            $('#mensaje').slideUp(500);
//        });

        function aceptaSoloNumeros(e)
        {
            var keynum = window.event ? window.event.keyCode : e.which;
            if ((keynum == 8) || (keynum == 46))
                if ($('#soloBN').is(':checked')) {
                    $('#numTotal').val($('#txtCantBN').val() * $('#numCopys').val());
                    eventosImpresionLaer.calcularPrecioImpresion();
                } else if ($('#soloColor').is(':checked')) {
                    $('#numTotal').val($('#txtCantColor').val() * $('#numCopys').val());
                    eventosImpresionLaer.calcularPrecioImpresion();
                } else if ($('#mixto').is(':checked')) {
                    $('#numTotal').val($('#txtCantColor').val() * $('#numCopys').val());
                    eventosImpresionLaer.calcularPrecioImpresion();
                }
            return true;
            return /\d/.test(String.fromCharCode(keynum));
        }
        function aceptaNumerosYSeparadores(e)
        {
            var keynum = window.event ? window.event.keyCode : e.which;
            if ((keynum == 8) || (keynum == 46) || (keynum == 44) || (keynum == 45) && (keynum != 46))
                return true;
            return /\d/.test(String.fromCharCode(keynum));
        }


        function validarCadena() {
            alert($('#txtCantColorMix').val());
        }
        //Verificar tipo de archivo.
        $('#cmbxTipoCarga').change(function() {
            if ($('#cmbxTipoCarga').val() == 1) {
                $('#imgCargar').hide();
                $('#txtLinkArchivos').hide();
                $('#imgCargar').removeAttr("src");
                $('#imgCargar').attr({"src": "img/icons/global/cargaArchivo.jpg"});
                $('#imgCargar').attr({"title": "Adjunta un archivo desde tú equipo."});
                $('#imgCargar').slideDown(500);
                $('#txtArchivosCargados').slideDown(500);
                $('#btnCargar').slideDown(500);
                $('#tipoCargaSelect').slideDown(500);
            } else {
                $('#imgCargar').hide();
                $('#txtArchivosCargados').hide();
                $('#btnCargar').hide();
                $('#imgCargar').removeAttr("src");
                $('#imgCargar').attr({"src": "img/icons/global/compartirLink.jpg"});
                $('#imgCargar').attr({"title": "Compartenos un link de un archivo en la nube."});
                $('#imgCargar').slideDown(500);
                $('#txtLinkArchivos').slideDown(500);
                $('#tipoCargaSelect').slideDown(500);
                $('#txtLinkArchivos').focus();
            }
        });
//        $('#txtCantBN').keypress(function(data) {
//            txtCantBN = $('#txtCantBN').val();
//            if (data.keyCode >= 48 && data.keyCode <= 57) {
//
//            }
//        });
        //Fin validando números.


        $('#txtCanHBN').change(function() {
            if ($('#soloBN').is(':checked')) {
                $('#numTotal').val($('#txtCanHBN').val() * $('#numCopys').val());
                eventosImpresionLaer.calcularPrecioImpresion();
            } else if ($('#soloColor').is(':checked')) {
                $('#numTotal').val($('#txtCantHColor').val() * $('#numCopys').val());
                eventosImpresionLaer.calcularPrecioImpresion();
            } else if ($('#mixto').is(':checked')) {
                $('#numTotal').val($('#txtCantHColor').val() * $('#numCopys').val());
                eventosImpresionLaer.calcularPrecioImpresion();
            }
        });

        $('#imgCompartirLink').click(function() {
            $('#link').removeAttr("disabled");
            $('#link').focus();
        });
        $('#imgContinuarPedido').click(function() {
            eventosImpresionLaer.insertarPrimerFormulario();
//            $("#contentD")
//                    .html('')
//                    .append($('#cargador').clone().show());
//            $.post("paginas/tiposImpresion/impresionLaser2.html", function(data) {
//                $("#contentD").html(data);
//            });
        });
        $('#chat').click(function() {
            inicio.chat();
        });

        $('#imgCloseSMS').click(function() {
            $('#mensaje').slideUp(500);
        });
        $('#cmbxModoImpresion').change(function() {
            $('#mensaje').slideUp();
            peticionesImpresionLaser.consultarImgModoImpre();
            validacionesImpresionLaser.validarModoImpresion();
            eventosImpresionLaer.calcularPrecioImpresion();
        });
        $('#cmbxTipoTamano').change(function() {
            $('#mensaje').slideUp(500);
            validacionesImpresionLaser.validarTamanoImpresion();
            eventosImpresionLaer.calcularPrecioImpresion();
        });
        $('#cmbxTipoPapel').change(function() {
            $('#mensaje').slideUp(500);
            validacionesImpresionLaser.validarTipoPapel();
            eventosImpresionLaer.calcularPrecioImpresion();
        });

        //Funciones radio-Buttons
        $('#soloBNt').click(function() {
            eventosImpresionLaer.checkSoloBNClick();
        });

        $('#soloColort').click(function() {
            eventosImpresionLaer.checkSoloColorClick();
        });

        $('#mixtot').click(function() {
            eventosImpresionLaer.checkMixtoClick();
        });
        $('#soloBN').click(function() {
            eventosImpresionLaer.checkSoloBNClick();
            $('#numTotal').val($('#numPaginasBN').val() * $('#numCopys').val());
        });

        $('#numCopys').change(function() {
            if ($('#soloBN').is(':checked')) {
                $('#numTotalBN').val($('#numPaginasBN').val() * $('#numCopys').val());
            } else
            if ($('#soloColor').is(':checked')) {
                $('#numTotalColor').val($('#numPaginasColor').val() * $('#numCopys').val());
            } else
            if ($('#mixto').is(':checked')) {
                $('#numTotalBN').val($('#numPaginasBN').val() * $('#numCopys').val());
                $('#numTotalColor').val($('#numPaginasColor').val() * $('#numCopys').val());
            }
            //Ejecutamos el calculo.
            eventosImpresionLaer.calcularPrecioImpresion();
        });

        $('#soloColor').click(function() {
            eventosImpresionLaer.checkSoloColorClick();
            $('#numTotal').val($('#numPaginasColor').val() * $('#numCopys').val());
        });

        $('#mixto').click(function() {
            eventosImpresionLaer.checkMixtoClick();
            $('#numTotal').val(0);
        });
        //Fin funciones radio-buttons

        $('#btnSiguiente').click(function() {
            controlImpresionLaser.siguientePaso();
        });
    },
    checkSoloBNClick: function() {
        if (!valorImprePosible) {
            $('#btnSiguiente').slideDown(500);
        }
        tipoColorSeleccionado = 1;
        $('#lblTotalBN').css({"margin-top": "8px"});
        $('#numTotalColor').val(0);
        $('#txtBNMixto').val('');
        $('#txtColorMixto').val('');

        $('#lblTotalBN').show();
        $('#numTotalBN').show();

        $('#lblTotalColor').hide();
        $('#numTotalColor').hide();

        $('span.txtTolBN').html('0');
        $('span.txtTolClr').html('0');
        $('#descripParamas').hide();
        $('.txtInfoBN').html('Debes seleccionar la forma en la que deseas ingresar las hojas que vas a imprimir.<br/><br/><b>Opciones</b><br/> - Todo el archivo.</br> - Algunas las hojas del archivo.');
        $('#soloBN').attr({"checked": "checked"});
        //Mostrar el elemento infoMixto.
        $('#contentTemp').css({"display": "none"});
        $('#tipoColor').slideDown(500);
        $('#divSoloColor').hide();
        $('#divMixto').hide();
        $('#divSoloBN').slideDown(500);
        $('#btnsPasos').slideDown(500);
        $('#infoMixto #txtCanHBN').removeAttr("disabled");
        $('#infoMixto #txtCanHBN').focus();
        $('#infoMixto #txtCanHBN').css({"background": "#fff"});
        //Cambiar estado de componentes.
        //bloqueamos los input text de mixto y cambiamos el color de fondo.
        $('#infoMixto #txtColorMixto').attr({"disabled": "disabled"});
        $('#infoMixto #txtColorMixto').css({"background": "#ccc"});
        $('#infoMixto #txtBNMixto').attr({"disabled": "disabled"});
        $('#infoMixto #txtBNMixto').css({"background": "#ccc"});
        $('#infoMixto #txtCantHColor').attr({"disabled": "disabled"});
        $('#infoMixto #txtCantHColor').css({"background": "#ccc"});
        controlImpresionLaser.pasoDos();
        huboCambioColor = true;
        $('#mensaje').slideUp(500);
    },
    checkSoloColorClick: function() {
        if (!valorImprePosible) {
            $('#btnSiguiente').slideDown(500);
        }
        $('#numTotalBN').val(0);
        $('#txtBNMixto').val('');
        $('#txtColorMixto').val('');
        tipoColorSeleccionado = 2;
        $('#lblTotalColor').css({"margin-top": "8px"});
        $('#lblTotalBN').hide();
        $('#numTotalBN').hide();
        $('#lblTotalColor').show();
        $('#numTotalColor').show();

        $('span.txtTolBN').html('0');
        $('span.txtTolClr').html('0');
        $('#descripParamas').hide();
        $('.txtInfoColor').html('Debes seleccionar la forma en la que deseas ingresar las hojas que vas a imprimir.');
        $('#soloColor').attr({"checked": "checked"});
        $('#contentTemp').css({"display": "none"});
        $('#tipoColor').slideDown(500);
        $('#infoMixto').slideDown(500);
        $('#btnsPasos').slideDown(500);
        $('#divSoloBN').hide();
        $('#divMixto').hide();
        $('#divSoloColor').slideDown(500);
        $('#infoMixto #txtCantHColor').removeAttr("disabled");
        $('#infoMixto #txtCantHColor').css({"background": "#fff"});
        $('#infoMixto #txtCantHColor').focus();
        $('#infoMixto #txtColorMixto').attr({"disabled": "disabled"});
        $('#infoMixto #txtColorMixto').css({"background": "#ccc"});
        $('#infoMixto #txtBNMixto').attr({"disabled": "disabled"});
        $('#infoMixto #txtBNMixto').css({"background": "#ccc"});
        $('#infoMixto #txtCanHBN').attr({"disabled": "disabled"});
        $('#infoMixto #txtCanHBN').css({"background": "#ccc"});
        controlImpresionLaser.pasoDos();
        huboCambioColor = true;
        $('#mensaje').slideUp(500);
    },
    checkMixtoClick: function() {
        if (!valorImprePosible) {
            $('#btnSiguiente').slideDown(500);
        }
        $('#numTotalBN').val(0);
        $('#numTotalColor').val(0);
        tipoColorSeleccionado = 3;
        $('#lblTotalBN').css({"margin-top": "0px"});
        $('#lblTotalBN').show();
        $('#numTotalBN').show();
        $('#lblTotalColor').show();
        $('#numTotalColor').show();
        $('#descripParamas').slideDown(500);
        $('#div1').show();
        $('#div2').show();
        $('.txtInfoMixto').html('Debes ingresar y especificar las hojas que vas a imprimir a blanco y negro(B/N) y a color, para esto implementa el uso de las comas (,) para separar páginas y para especificar rangos utiliza un guión(-).<br/><br/> <b>Separación de Hojas (por coma):</b><br/><b> - Ejemplo:</b> 1,2,3,4 <br/>' +
                '<br/><b>Incluir Rangos (Pag inicial-Pag final):</b><br/>' +
                '<b> - Ejemplo:</b> 2-10, 11-20.<br/>' +
                '<b><br/>Ejemplos concretos:</b><br/>' +
                '<b> - Ejemplo 1:</b> Tienes un archivo de 10 hojas<br/>' +
                'y Deseas imprimir la pag 1, la 3 y la 5.' +
                ' <b>Solución:</b>' +
                '1,3,5.' +
                '<br/><br/><b> - Ejemplo 2:</b>Tienes un archivo de 20 hojas<br/>' +
                'y deseas imprimir de la pág 1 a la pág 5 y de la 11 a la 15.<br/>' +
                '<b>Solución:</b>' +
                '1-5, 11-15<br/><br/>' +
                '<b>Nota:</b>También Puedes integrar las dos opciones, ejemplo; páginas 1,2, y 3 al 10: ' +
                '1,2,3-10');
        $('#mixto').attr({"checked": "checked"});
        $('#contentTemp').css({"display": "none"});
        $('#tipoColor').slideDown(500);
        $('#infoMixto').slideDown(500);
        $('#btnsPasos').slideDown(500);
        $('#divSoloColor').hide();
        $('#divSoloBN').hide();
        $('#divMixto').slideDown(500);
        $('#infoMixto #txtColorMixto').removeAttr("disabled");
        $('#infoMixto #txtColorMixto').focus();
        $('#infoMixto #txtColorMixto').css({"background": "#fff"});
        $('#infoMixto #txtBNMixto').removeAttr("disabled");
        $('#infoMixto #txtBNMixto').css({"background": "#fff"});

        $('#infoMixto #txtCanHBN').attr({"disabled": "disabled"});
        $('#infoMixto #txtCanHBN').css({"background": "#ccc"});
        $('#infoMixto #txtCantHColor').attr({"disabled": "disabled"});
        $('#infoMixto #txtCantHColor').css({"background": "#ccc"});
        controlImpresionLaser.pasoDos();
        huboCambioColor = true;
        $('#mensaje').slideUp(500);
    },
    calculoImpresion: function() {
        //Inicialmente debemos enviamos el modo de impresión, ya que se restará el valor de la hoja.               
        validacionesImpresionLaser.calculoImpresion();
    },
};


