/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var agregados = 0;
var primerVisita = true;
var preciosControl = {
    init: function() {
        if (primerVisita) {
            primerVisita = false;
            $('#cargador').show();
        }
        /**
         * Parametrizamos los elementos necesarios para las tablas.
         */
        $('td#t00_clipColor input').val(($('td#t00_clipColor span').html()).replace('$', ''));
        $('td#t00_rIColor input').val(($('td#t00_rIColor span').html()).replace('$', ''));
        $('td#t00_operColor input').val(($('td#t00_operColor span').html()).replace('$', ''));

        $('td#t00_clipBn input').val(($('td#t00_clipBn span').html()).replace('$', ''));
        $('td#t00_rIBn input').val(($('td#t00_rIBn span').html()).replace('$', ''));
        $('td#t00_operBn input').val(($('td#t00_operBn span').html()).replace('$', ''));
        //Consultamos los precios para la primera tabla(Costos-mantenimiento).
        preciosControl.consultarPrecios();
    },
    checkboxClick: function(idTr)
    {
        if ($('#' + idTr).find('input[type="checkbox"]').is(':checked')) {
            $('#' + idTr).attr('class', 'trSelect');
            $('#' + idTr + ' td.precio-pliego').attr('class', 'precio-pliego2');
            $('#' + idTr + ' td.descrip').attr('class', 'descrip2');
            $('#' + idTr + ' td.tdcbx').attr('class', 'tdcbx2');
        } else {
            $('#' + idTr).attr('class', 'trdata');
            $('#' + idTr + ' td.precio-pliego2').attr('class', 'precio-pliego');
            $('#' + idTr + ' td.descrip2').attr('class', 'descrip');
            $('#' + idTr + ' td.tdcbx2').attr('class', 'tdcbx');
        }
    },
    mostrarUnInput: function(idTd) {
        $('#' + idTd + ' span').hide();
        $('#' + idTd + ' input').show();
        $('#' + idTd + ' input').focus();
    },
    cambiarEstadoInput: function(idTd) {
        $('#' + idTd + ' input').hide();
        $('#' + idTd + ' span').html('$' + controlPeticiones.formatearValor($('#' + idTd + ' input').val()));
        $('#' + idTd + ' span').show();
        //El código de referencia 't00_' que he incluido al nombre para cada tr
        //en la tabla sobre el html, es para saber desde que tabla se está
        //llamando esta función, pues para ahorrar recursos no quiero que
        //la misma ejecute todos los calculos de las tablas ya que tienen
        //directa comunicación con el servidor para actualizar cambios
        //automaticamente despues de realizar un cambio.
        console.warn(idTd);
        if (idTd.search('t00_') >= 0) {
            preciosControl.calcularCostosMantenimiento();
        }
        if (idTd.search('Add') >= 0) {

        }
        if (idTd.search('t01_precio_pliego') >= 0) {
            var idPapel = idTd.replace('t01_precio_pliego', '');
            var nombreTr = 'tr' + idPapel;
            idPapel = $('#' + nombreTr + ' b').html();
            var nuevoPrecio = $('#' + idTd + ' input[type="text"]').val();
            preciosControl.actualizarPrecioPapel(idPapel, nuevoPrecio, nombreTr);
        }
    },
    cambiarEstadoInputText: function(idTd) {
        console.log($('#' + idTd + ' input'));
        $('#' + idTd + ' input').hide();
        $('#' + idTd + ' span').html($('#' + idTd + ' input').val());
        $('#' + idTd + ' span').show();
        if (idTd.search('t01_descrip') >= 0) {
            var idPapel = idTd.replace('t01_descrip', '');
            var nombreTr = 'tr' + idPapel;
            idPapel = $('#' + nombreTr + ' b').html();
            var nuevoNombre = $('#' + idTd + ' input[type="text"]').val();
            preciosControl.actualizarNombrePapel(idPapel, nuevoNombre);
        }
    },
    actualizarNombrePapel: function(idPapel, nuevoNombre) {
        $.ajax({
            'url': 'actualizarNombrePapel',
            'type': 'POST',
            'data': {'idPapel': idPapel, 'nuevoNombre': nuevoNombre},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.info(respuesta);
            }
        });
    },
    actualizarPrecioPapel: function(idPapel, nuevoPrecio, nombreTr) {
        $.ajax({
            'url': 'actualizarPrecioPapel',
            'type': 'POST',
            'data': {'idPapel': idPapel, 'nuevoPrecio': nuevoPrecio},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.info(respuesta);
                if (respuesta.codigo > 0) {
                    preciosControl.actualizarCostos(nombreTr);
                }
            }
        });
    },
    nuevoPapel: function() {
        var objetoJSON = {};
        $.ajax({
            'utl': 'nuevoPapel',
            'type': 'POST',
            'data': objetoJSON,
            success: function(data) {

            }
        });
    },
    //A continuación - Funciones relacionadas con costos de mantenimiento.
    consultarPrecios: function() { //Todos los precios.
        $.ajax({
            'url': 'consultarPrecios',
            'type': 'POST',
            success: function(data) {
                var respuesta = JSON.parse(data);
                if (respuesta.codigo > 0) {
                    console.info(respuesta);

                    var bn = null;
                    var color = null;

                    //Lo siguiente para mostrar los costos de mantenimiento:
                    if (respuesta.datos[0][0].idCostosMantenimiento == 1) {
                        bn = respuesta.datos[0][0];
                        color = respuesta.datos[0][1];
                    } else {
                        bn = respuesta.datos[0][1];
                        color = respuesta.datos[0][0];
                    }

                    $('td#t00_clipColor input').val(color.clipColor);
                    $('td#t00_rIColor input').val(color.recuInversion);
                    $('td#t00_operColor input').val(color.operadores);

                    $('td#t00_clipBn input').val(bn.clipColor);
                    $('td#t00_rIBn input').val(bn.recuInversion);
                    $('td#t00_operBn input').val(bn.operadores);

                    $('#costosMantenimiento').show(); //Mostramos la tabla.
                    //Fin costos mantenimiento.                                      

                    //Ahora pasamos los datos consultados a los span.
                    $('td#t00_clipColor span').html('$' + controlPeticiones.formatearValor($('td#t00_clipColor input').val()));
                    $('td#t00_rIColor span').html('$' + controlPeticiones.formatearValor($('td#t00_rIColor input').val()));
                    $('td#t00_operColor span').html('$' + controlPeticiones.formatearValor($('td#t00_rIColor input').val()));
                    $('td span#costoIColor').html('$' + controlPeticiones.formatearValor(color.valorCosto));

                    $('td#t00_clipBn span').html('$' + controlPeticiones.formatearValor($('td#t00_clipBn input').val()));
                    $('td#t00_rIBn span').html('$' + controlPeticiones.formatearValor($('td#t00_rIBn input').val()));
                    $('td#t00_operBn span').html('$' + controlPeticiones.formatearValor($('td#t00_operBn input').val()));
                    $('td span#costoIBN').html('$' + controlPeticiones.formatearValor(bn.valorCosto));
                    //Fin muestra costos mantenimiento.
                    //Actualiza la última fecha de actualización de la tabla - costos mantenimiento.
                    $('span#fecha_actualizacion').html(controlPeticiones.formatearHora(bn.ultimaActualizacion));

//                    TAMAÑOS-Costos internos
                    if (respuesta.datos[2].length > 0) {
                        var modelo = $('table#tbcostosInternosImpresionLaser tr.valores td#vlrModelo');
                        var modelo2 = $('table#tbcostosImpresionMasPapel tr.valores td#valorModelo');
                        var modelo3 = $('table#tbcostosImpresionMasPapelBN tr.valores td#valorModelo');
                        $('table#tbcostosInternosImpresionLaser tr.valores').html('<td colspan="3"></td>');
                        $('table#tbcostosImpresionMasPapel tr.valores').html('<td></td>');
                        $('table#tbcostosImpresionMasPapelBN tr.valores').html('<td></td>');
                        $.each(respuesta.datos[2], function(indice, valor) {
                            if (valor.idTipoTamano != 2) {
                                //Agregamos los respectivos tamaños para la tabla de costos internos.
                                $('table#tbcostosInternosImpresionLaser tr.valores').append(modelo.clone().attr('id', 'valor_' + valor.idTipoTamano));
                                $('table#tbcostosInternosImpresionLaser tr.valores #valor_' + valor.idTipoTamano).attr('ondblclick', "preciosControl.mostrarUnInput('valor_" + valor.idTipoTamano + "')");
                                $('table#tbcostosInternosImpresionLaser tr.valores #valor_' + valor.idTipoTamano + ' input').attr('onchange', "preciosControl.cambiarEstadoInput('valor_" + valor.idTipoTamano + "')");
                                $('table#tbcostosInternosImpresionLaser tr.valores #valor_' + valor.idTipoTamano + ' input').val(valor.numeroHojas);
                                $('table#tbcostosInternosImpresionLaser tr.valores #valor_' + valor.idTipoTamano + ' span').html(valor.numeroHojas);

                                //Agregamos los respectivos costos para la tabla costos de impresión color + papel.                                                                
                                $('table#tbcostosImpresionMasPapel tr.valores').append(modelo2.clone().attr('id', 't02valor_' + valor.idTipoTamano));
                                $('table#tbcostosImpresionMasPapel tr.valores #t02valor_' + valor.idTipoTamano).attr('ondblclick', "preciosControl.mostrarUnInput('t02valor_" + valor.idTipoTamano + "')");
                                $('table#tbcostosImpresionMasPapel tr.valores #t02valor_' + valor.idTipoTamano + ' input').attr('onchange', "preciosControl.cambiarEstadoInput('t02valor_" + valor.idTipoTamano + "')");
                                console.warn(valor.costoImpresion);
                                $('table#tbcostosImpresionMasPapel tr.valores #t02valor_' + valor.idTipoTamano + ' input').val(valor.costoImpresion);
                                $('table#tbcostosImpresionMasPapel tr.valores #t02valor_' + valor.idTipoTamano + ' span').html(controlPeticiones.formatearValor(valor.costoImpresion));
                                
                                //Agregamos los respectivos costos para la tabla costos de impresión blanco y negro(B/N) + papel.
                                $('table#tbcostosImpresionMasPapelBN tr.valores').append(modelo3.clone().attr('id', 't03valor_' + valor.idTipoTamano));
                                $('table#tbcostosImpresionMasPapelBN tr.valores #t03valor_' + valor.idTipoTamano).attr('ondblclick', "preciosControl.mostrarUnInput('t03valor_" + valor.idTipoTamano + "')");
                                $('table#tbcostosImpresionMasPapelBN tr.valores #t03valor_' + valor.idTipoTamano + ' input').attr('onchange', "preciosControl.cambiarEstadoInput('t03valor_" + valor.idTipoTamano + "')");
                                console.warn(valor.costoImpresion);
                                $('table#tbcostosImpresionMasPapelBN tr.valores #t03valor_' + valor.idTipoTamano + ' input').val(valor.costoImpresion);
                                $('table#tbcostosImpresionMasPapelBN tr.valores #t03valor_' + valor.idTipoTamano + ' span').html(controlPeticiones.formatearValor(valor.costoImpresion));
                            }
                        });
                    }
//                    FIN TAMAÑOS-Costos internos

                    //Ahora vamos con los costos internos.
                    if (respuesta.datos[1].length > 0) {
                        var trModelo = $('table#tbcostosInternosImpresionLaser tr#trModelo');
                        var result = null;
                        var footer = $('table#tbcostosInternosImpresionLaser #informacion');
                        $('table#tbcostosInternosImpresionLaser #informacion').remove();
                        $.each(respuesta.datos[1], function(indice, valor) {
                            $('table#tbcostosInternosImpresionLaser').append(trModelo.clone().attr('id', 'tr' + indice).show());
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td.descrip').attr('id', 't01_descrip' + indice);
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_descrip' + indice + ' b').html(valor.idTipoPapel);
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_descrip' + indice).attr('ondblclick', "preciosControl.mostrarUnInput('t01_descrip" + indice + "')");
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_descrip' + indice + ' span').html(valor.papel);
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_descrip' + indice + ' input').val(valor.papel);
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_descrip' + indice + ' input').attr('onchange', "preciosControl.cambiarEstadoInputText('t01_descrip" + indice + "')");

                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' input[type="checkbox"').attr('onclick', 'preciosControl.checkboxClick("tr' + indice + '")');
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_pliego').attr('id', 't01_precio_pliego' + indice);
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_pliego' + indice).attr('ondblclick', "preciosControl.mostrarUnInput('t01_precio_pliego" + indice + "')");
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_pliego' + indice + ' input').attr('onchange', "preciosControl.cambiarEstadoInput('t01_precio_pliego" + indice + "')");
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_pliego' + indice + ' input').val(valor.precioPliego);
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_pliego' + indice + ' span').html('$' + controlPeticiones.formatearValor(valor.precioPliego));
                            //Obteniendo calculos.

                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_carta').attr('id', 't01_precio_carta' + indice);
                            result = $('#' + $('tr.valores').find('td')[1].id + ' input').val();
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_carta' + indice).html('$' + controlPeticiones.aproximarDecimal((valor.precioPliego / result)));

                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_octavo').attr('id', 't01_precio_octavo' + indice);
                            result = $('#' + $('tr.valores').find('td')[2].id + ' input').val();
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_octavo' + indice).html('$' + controlPeticiones.aproximarDecimal((valor.precioPliego / result)));

                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_tabloide').attr('id', 't01_precio_tabloide' + indice);
                            result = $('#' + $('tr.valores').find('td')[3].id + ' input').val();
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_tabloide' + indice).html('$' + controlPeticiones.aproximarDecimal((valor.precioPliego / result)));

                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_33x48').attr('id', 't01_precio_33x48' + indice);
                            result = $('#' + $('tr.valores').find('td')[3].id + ' input').val();
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_33x48' + indice).html('$' + controlPeticiones.aproximarDecimal((valor.precioPliego / result)));

                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_33x70').attr('id', 't01_precio_33x70' + indice);
                            result = $('#' + $('tr.valores').find('td')[5].id + ' input').val();
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_33x70' + indice).html('$' + controlPeticiones.aproximarDecimal((valor.precioPliego / result)));

                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_33x100').attr('id', 't01_precio_33x100' + indice);
                            result = $('#' + $('tr.valores').find('td')[6].id + ' input').val();
                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' td#t01_precio_33x100' + indice).html('$' + controlPeticiones.aproximarDecimal((valor.precioPliego / result)));
                            
                            preciosControl.valorizarColorMasPapel(indice,valor.papel);
                        });
                        $('table#tbcostosInternosImpresionLaser').append(footer.clone());
                    }
                    $('#costosInternosImpresionLaser').show(); //Mostramos la tabla.
                    //Fin costos internos.
                    $('#cargador').hide();
                    $('#opciones').slideToggle(500);
                } else {
                    console.error(respuesta);
                    $('#divContenido').html('');
                    $('#divContenido').append($('#error-data').clone().show());
                }
            }
        });
    },
    valorizarColorMasPapel: function(indice,descrip) {
        var trModelo2 = $('table#tbcostosImpresionMasPapel tr#trModelo');
        var trModelo3 = $('table#tbcostosImpresionMasPapelBN tr#trModelo');
        var valorColor = 0;
        //Calculamos los valores necesarios para la tabla costos de impresión color + papel.
        $('table#tbcostosImpresionMasPapel').append(trModelo2.clone().attr('id', 'tr' + indice).show());
        $('table#tbcostosImpresionMasPapelBN').append(trModelo3.clone().attr('id', 'tr' + indice).show());
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_descrip').html(descrip);
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_descrip').html(descrip);
        //Hallamos el primer valor de precio carta.
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[1].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_carta' + indice).html().replace('$', '')).replace('.',''));
        //Una vez hallado el valor correspondiente se lo atribuimos al td correspondiente escribiendolo con la función html().
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_carta').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));
        
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[1].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_carta' + indice).html().replace('$', '')).replace('.',''));
        //Una vez hallado el valor correspondiente se lo atribuimos al td correspondiente escribiendolo con la función html().
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_carta').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        //Seguimos calculando los demás valores...
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[2].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_octavo' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_octavo').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));
        
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[2].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_octavo' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_octavo').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[3].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_tabloide' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_tabloide').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));
        
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[3].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_tabloide' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_tabloide').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[4].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x48' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_33x48').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));
        
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[4].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x48' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_33x48').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[5].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x70' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_33x70').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));
        
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[5].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x70' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_33x70').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[6].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x100' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_33x100').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));
//        
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[6].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x100' + indice).html().replace('$', '')).replace('.',''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_33x100').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));
    },
    calcularCostosMantenimiento: function() {
        var valorFinal = parseFloat($('td#t00_clipColor input').val());
        valorFinal += parseFloat(($('td#t00_rIColor input').val()));
        valorFinal += parseFloat(($('td#t00_operColor input').val()));
        $('span#costoIColor').html('$' + controlPeticiones.formatearValor(valorFinal));

        valorFinal = parseFloat($('td#t00_clipBn input').val());
        valorFinal += parseFloat(($('td#t00_rIBn input').val()));
        valorFinal += parseFloat(($('td#t00_operBn input').val()));
        $('span#costoIBN').html('$' + controlPeticiones.formatearValor(valorFinal));
        console.info('Calculado...');
        preciosControl.actualizarCostosMantenimiento();

    },
    actualizarCostosMantenimiento: function() {
        //Un objeto es para los datos del bn y el otro para color.              
        var objetoJSON = {}; //BN
        objetoJSON.idCostosMantenimiento = 1;
        objetoJSON.clipColor = parseFloat($('td#t00_clipBn input').val());
        objetoJSON.recuInversion = parseFloat(($('td#t00_rIBn input').val()));
        objetoJSON.operadores = parseFloat(($('td#t00_operBn input').val()));
        var valorFinal = ($('span#costoIBN').html().replace(',', '.'));
        objetoJSON.valorCosto = parseFloat(valorFinal.replace('$', ''));

        var objetoJSON2 = {}; //Color
        objetoJSON2.idCostosMantenimiento = 2;
        objetoJSON2.clipColor = parseFloat($('td#t00_clipColor input').val());
        objetoJSON2.recuInversion = parseFloat(($('td#t00_rIColor input').val()));
        objetoJSON2.operadores = parseFloat(($('td#t00_operColor input').val()));
        valorFinal = ($('span#costoIColor').html().replace(',', '.'));
        objetoJSON2.valorCosto = parseFloat(valorFinal.replace('$', ''));

        console.info('objetoJSON');
        console.info(objetoJSON);
        console.info('objetoJSON2');
        console.info(objetoJSON2);

        $.ajax({
            'url': 'actualizarCostosMantenimiento',
            'type': 'POST',
            'data': {'dataBn': JSON.stringify(objetoJSON), 'dataColor': JSON.stringify(objetoJSON2)},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.info(respuesta);
                if (respuesta.codigo > 0) {
                    $('span#fecha_actualizacion').html(controlPeticiones.formatearHora(respuesta.datos));
                }
            }
        });
    },
    //Fin costos de mantenimiento.



//    PRECIOS INTERNOS


//    FIN PRECIOS INTERNOS

    eventos: function() {
        $('#tbcostosMantenimiento #titulo').click(function() {
            $('#tbcostosMantenimiento input').hide();
            $('#tbcostosMantenimiento span').show();
//            preciosControl.calcularCostosMantenimiento();
        });
        $('table#tbcostosInternosImpresionLaser #t01_titulo').click(function() {
            $('#tbcostosInternosImpresionLaser input[type="text"]').hide();
            $('#tbcostosInternosImpresionLaser span').show();
        });
        $('table#tbcostosImpresionMasPapel #t02_titulo').click(function() {
            $('#tbcostosImpresionMasPapel input[type="text"]').hide();
            $('#tbcostosImpresionMasPapel span').show();
        });
        $('table#tbcostosImpresionMasPapelBN #t03_titulo').click(function() {
            $('#tbcostosImpresionMasPapelBN input[type="text"]').hide();
            $('#tbcostosImpresionMasPapelBN span').show();
        });
        $('#btns img#del').mouseover(function() {
            $('#btns img#del').attr('src', 'img/icons/global/del2.gif');
        });
        $('#btns img#del').mouseout(function() {
            $('#btns img#del').attr('src', 'img/icons/global/del.gif');
        });
        $('#btns img#add').mouseover(function() {
            $('#btns img#add').attr('src', 'img/icons/global/add2.gif');
        });
        $('#btns img#add').mouseout(function() {
            $('#btns img#add').attr('src', 'img/icons/global/add.gif');
        });
        $('#btns img#add').click(function() {
            //Guardamos los datos existentes (del html).
//            var contentTable = $('#tbcostosInternosImpresionLaser tr.trdata');
//            var contentTable2 = $('#tbcostosInternosImpresionLaser tr.trSelect');

            //Eliminamos los datos existentes (del html).
//            $('#tbcostosInternosImpresionLaser tr.trdata').remove();
//            $('#tbcostosInternosImpresionLaser tr.trSelect').remove();                        

            $('#tbcostosInternosImpresionLaser ').append($('#tbcostosInternosImpresionLaser tr#trModelo').clone().attr('id', 'trAdd' + agregados).show());
            $('#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td.descrip').html('<span></span><input type="text" id="descripAdd" />');
            $('#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td.descrip').attr('id', "descripAdd" + agregados);
            $('#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td.descrip').attr('ondblclick', "preciosControl.mostrarUnInput('descripAdd" + agregados + "')");
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td.descrip input').attr('onchange', "preciosControl.cambiarEstadoInputText('descripAdd" + agregados + "')");
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td.descrip input').val('');
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td.descrip input').attr('placeholder', 'Descripción o nombre del papel');
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td.descrip span').html('');
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td.descrip span').hide();
            $('#descripAdd').show();
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' input[type="checkbox"').attr('onclick', 'preciosControl.checkboxClick("trAdd' + agregados + '")');
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td#t01_precio_pliego').attr('id', 't01_precio_pliegoAdd' + agregados);
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td#t01_precio_pliegoAdd' + agregados).attr('ondblclick', "preciosControl.mostrarUnInput('t01_precio_pliegoAdd" + agregados + "')");
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td#t01_precio_pliegoAdd' + agregados + ' input').attr('onchange', "preciosControl.cambiarEstadoInput('t01_precio_pliegoAdd" + agregados + "')");
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td#t01_precio_pliegoAdd' + agregados + ' input').val(0);
            $('table#tbcostosInternosImpresionLaser #trAdd' + agregados + ' td#t01_precio_pliegoAdd' + agregados + ' span').html('$' + 0);
            agregados++;
        });
        $('#cbxselecttodos').click(function() {
            if ($('#cbxselecttodos').is(':checked')) {
                $('#costosInternosImpresionLaser input[type="checkbox"]').prop('checked', true);
                $('#costosInternosImpresionLaser tr.trdata').attr('class', 'trSelect');
            } else {
                $('#costosInternosImpresionLaser input[type="checkbox"]').prop('checked', false);
                $('#costosInternosImpresionLaser tr.trSelect').attr('class', 'trdata');
            }
        });
    },
    actualizarCostos: function(nombreTr) {
        var precioPliego = $('#' + $('#' + nombreTr).find('td')[2].id + ' input[type="text"]').val();
        var dataTds = $('#' + nombreTr).find('td');
        var result = null;
        $.each(dataTds, function(indice, valor) {
            if (indice >= 3) {
                result = $('#' + $('tr.valores').find('td')[(indice - 2)].id + ' input').val();
                $('#' + $('#' + nombreTr).find('td')[indice].id).html('$' + controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal((precioPliego / result))));
            }
        });
        
        var indice = parseInt(nombreTr.replace('tr',''));
        preciosControl.valorizarColorMasPapel(indice,$('#' + $('#' + nombreTr).find('td')[1].id + ' input[type="text"]').val());
    }
};
$(document).ready(function() {
    preciosControl.init();
    preciosControl.eventos();
});