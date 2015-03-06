/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var agregados = 0;
var primerVisita = true;
var dataConfigConsult = false;



//Evaluamos que se haya seleccionado por lo menos
//un elemento para cada content.
var configPaper = {
    validateSelecteds: function() {
        var x1 = $($($('#slcPapelesSeleted').find('div.contentOptions')).find('div.optionData input[type="checkbox"]')).is(':checked');
        var x2 = $($($('#slcTpImpresion').find('div.contentOptions')).find('div.optionData input[type="checkbox"]')).is(':checked');
        var x3 = $($($('#slcModosImpresion').find('div.contentOptions')).find('div.optionData input[type="checkbox"]')).is(':checked');
        var x4 = $($($('#slcTamanos').find('div.contentOptions')).find('div.optionData input[type="checkbox"]')).is(':checked');

        if (!x4) {
            $('#notificaciones p').html('Debes seleccionar por lo minimo un tamano de papel.');
            $('#notificaciones').hide();
            $('#notificaciones').show('slow');
            $('label.red').removeAttr('title');
            $('label.red').removeClass('red');
            $($('#slcTamanos').prev()).addClass('red');
            $($('#slcTamanos').prev()).attr('title', 'Ningún parámetro seleccionado.');
        }
        if (!x3) {
            $('#notificaciones p').html('Debes seleccionar por lo minimo un modo de impresión.');
            $('#notificaciones').hide();
            $('#notificaciones').show('slow');
            $('label.red').removeAttr('title');
            $('label.red').removeClass('red');
            $($('#slcModosImpresion').prev()).addClass('red');
            $($('#slcModosImpresion').prev()).attr('title', 'Ningún parámetro seleccionado.');
        }
        if (!x2) {
            $('#notificaciones p').html('Debes seleccionar por lo mínimo un tipo de impresión.');
            $('#notificaciones').hide();
            $('#notificaciones').show('slow');
            $('label.red').removeAttr('title');
            $('label.red').removeClass('red');
            $($('#slcTpImpresion').prev()).addClass('red');
            $($('#slcTpImpresion').prev()).attr('title', 'Ningún parámetro seleccionado.');
        }
        if (!x1) {
            $('#notificaciones p').html('Debes seleccionar por lo mínimo un tipo de papel.');
            $('#notificaciones').hide();
            $('#notificaciones').show('slow');
            $('label.red').removeAttr('title');
            $('label.red').removeClass('red');
            $($('#slcTamanos').prev()).addClass('red');
            $($('#slcTamanos').prev()).attr('title', 'Ningún parámetro seleccionado.');
        }

        if (x1 && x2 && x3 && x4) {
            $('#notificaciones').hide();
            $('label.red').removeAttr('title');
            $('label.red').removeClass('red');
            return true;
        } else {
            return false;
        }
    }, //Fin valida selecteds.

//Guarda congiguración realizada.
    saveConfig: function() {
        if (configPaper.validateSelecteds()) {
            //Una vez validado el formulario usando la función 
            //validateSelecteds que nos retornará true o false según
            //sea el caso, tenemos que construir los objetos que vamos a enviar.            

            //Iniciamos observando cuales papeles seleccionados están seleccionados, valga la redundancia.            
            var idPapel = 0;
            var papelesSeleccionados = [];
            var tiposImpresion = [];
            var modosImpresion = [];
            var tamanosImpresion = [];
            //Ahora construimos el objeto que contendrá todos los tipos de papel seleccionados por el usuario.                    
            var y = $($($('#slcPapelesSeleted').find('div.contentOptions')).find('div.optionData input[type="checkbox"]'));
            $.each(y, function(ind, dat) {
                if ($(dat).is(':checked')) {
                    var idPapel = (($($(dat).parent()).attr('id')).replace('option', ''));
                    papelesSeleccionados.push(parseInt(idPapel));
                }
            });
            //Ahora construimos el objeto que contendrá todos los tipos de impresión seleccionados por el usuario.                    
            y = $($($('#slcTpImpresion').find('div.contentOptions')).find('div.optionData input[type="checkbox"]'));
            $.each(y, function(ind, dat) {
                if ($(dat).is(':checked')) {
                    var idTipoImpresion = (($($(dat).parent()).attr('id')).replace('option', ''));
                    tiposImpresion.push(parseInt(idTipoImpresion));
                }
            });
            //Construimos el objeto con los modos de impresión seleccionados.
            y = $($($('#slcModosImpresion').find('div.contentOptions')).find('div.optionData input[type="checkbox"]'));
            $.each(y, function(ind, dat) {
                if ($(dat).is(':checked')) {
                    var idModoImpresion = (($($(dat).parent()).attr('id')).replace('option', ''));
                    modosImpresion.push(parseInt(idModoImpresion));
                }
            });
            //Construimos el objeto con los tamanos de papel.
            y = $($($('#slcTamanos').find('div.contentOptions')).find('div.optionData input[type="checkbox"]'));
            $.each(y, function(ind, dat) {
                if ($(dat).is(':checked')) {
                    var idTamano = (($($(dat).parent()).attr('id')).replace('option', ''));
                    tamanosImpresion.push(parseInt(idTamano));
                }
            });
        }
        configPaper.sendConfig(tiposImpresion, tiposImpresion, modosImpresion, tamanosImpresion);
    },
    //Guardamos todo lo concebido por la función anterior.
    sendConfig: function(tpP, tpI, mdI, tmI) {
        var tpp = JSON.stringify(tpP);
        tpp = tpp.replace('[', '');
        tpp = tpp.replace(']', '');
        var tps = JSON.stringify(tpI);
        tps = tps.replace('[', '');
        tps = tps.replace(']', '');
        var mds = JSON.stringify(mdI);
        mds = mds.replace('[', '');
        mds = mds.replace(']', '');
        var tms = JSON.stringify(tmI);
        tms = tms.replace('[', '');
        tms = tms.replace(']', '');
        $.ajax({
            'url': 'crearRelacionPapel',
            'type': 'POST',
            'async': false,
            'data': {'papelesSeleccionados': tpp, 'tiposImpresion': tps, 'modosImpresion': mds, 'tamanosImpresion': tms},
            success: function(data) {
                var respuesta = JSON.parse(data);
                $('#notificaciones').hide();
                $('#notificaciones p').html(respuesta.mensaje);
                $('#notificaciones').show('slow');
            }
        });
    }
};





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
    checkboxClick: function(varThis)
    {
        if ($($($(varThis).parent()).parent()).attr('class') != "titulos") {
            if ($(varThis).is(':checked')) {
                $($($(varThis).parent()).parent()).attr('class', 'trSelect');
            } else {
                $($($(varThis).parent()).parent()).attr('class', 'trdata');
            }
        }
    },
    dataConfigPapel: function() {
//Papeles seleccionados.
        $($('.selectAll')[0]).prop('checked', true);
        var widthContentPpl = 0;
        console.log(widthContentPpl);
        var optionModel = $('#slcPapelesSeleted div.contentOptions div.optionForSelect');
        $('#slcPapelesSeleted div.contentOptions div.optionData').remove();
        $.each($('table#tbcostosInternosImpresionLaser tr.trSelect'), function(indice, valor) {
            valor.idTipoPapel = $(valor).find('b').html();
            valor.papel = $(valor).find('.descrip span').html();
            $('div#slcPapelesSeleted div.contentOptions').append(optionModel.clone().attr('id', 'option' + valor.idTipoPapel).show());
            $('div#slcPapelesSeleted #option' + valor.idTipoPapel + ' span').html(valor.papel);
            $('div#slcPapelesSeleted #option' + valor.idTipoPapel).attr('class', 'optionData');
        });
        widthContentPpl = 0;
        $.each($('#slcPapelesSeleted div.optionData'), function(indice, valor) {
            if ($(valor).find('span').html().length > 45) {
                var valor2 = ('width', ($(valor).find('span').html().length * 7));
                if (valor2 > widthContentPpl) {
                    widthContentPpl = valor2;
                }
                if (widthContentPpl > 0) {
                    $('div#slcPapelesSeleted div.optionData').css('width', widthContentPpl + 'px');
                }
            }
        });
        $('#slcPapelesSeleted div.optionData input[type="checkbox"]').prop('checked', true);
//Fin papeles seleccionados.



        if (!dataConfigConsult) {
            dataConfigConsult = true;

            //Modo impresión.
            $.ajax({
                'url': 'modoimpresion',
                'type': 'POST',
                'async': false,
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    console.log(respuesta);
                    var widthOption = 0;
                    var optionModel = $('#slcModosImpresion div.contentOptions div.optionForSelect');
                    $('#slcModosImpresion div.contentOptions div.optionData').remove();
                    $.each(respuesta.datos, function(indice, valor) {
                        $('div#slcModosImpresion div.contentOptions').append(optionModel.clone().attr('id', 'option' + valor.idModoImpresion).show());
                        if (valor.modo.length > 45) {
                            var widthOption2 = $('div#slcTpPapel #option' + valor.idModoImpresion).width() + ((valor.modo.length - 45) * 5);
                            if (widthOption2 > widthOption) {
                                widthOption = widthOption2;
                            }
                        }
                        $('div#slcModosImpresion #option' + valor.idModoImpresion).attr('class', 'optionData');
                        if (widthOption > 0) {
                            $('div#slcTpPapel div.optionData').css('width', widthOption + 'px');
                        }
                        $('div#slcModosImpresion #option' + valor.idModoImpresion + ' span').html(valor.modo);
                    });
                }
            });
//Fin modo impresión.

//Tamaños de papel.
            $.ajax({
                'url': 'tamanopapel',
                'type': 'POST',
                'async': false,
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    console.log(respuesta);
                    var widthOption = 0;
                    var optionModel = $('#slcTamanos div.contentOptions div.optionForSelect');
                    $('#slcTamanos div.contentOptions div.optionData').remove();
                    $.each(respuesta.datos, function(indice, valor) {
                        $('div#slcTamanos div.contentOptions').append(optionModel.clone().attr('id', 'option' + valor.idTipoTamano).show());
                        if (valor.tipoTamano.length > 45) {
                            var widthOption2 = $('div#slcTpPapel #option' + valor.idTipoTamano).width() + ((valor.tipoTamano.length - 45) * 5);
                            if (widthOption2 > widthOption) {
                                widthOption = widthOption2;
                            }
                        }
                        $('div#slcTamanos #option' + valor.idTipoTamano + ' span').html(valor.tipoTamano);
                        if (widthOption > 0) {
                            $('div#slcTpPapel div.optionData').css('width', widthOption + 'px');
                        }
                        $('div#slcTamanos #option' + valor.idTipoTamano).attr('class', 'optionData');
                    });
                }
            });
//Fin tamaños de papel.

////Tipos de papel.
//            $.ajax({
//                'url': 'tipopapel',
//                'type': 'POST',
//                success: function(data) {
//                    var respuesta = JSON.parse(data);
//                    console.log(respuesta);
//                    var widthOption = 0;
//                    var optionModel = $('#slcTpPapel div.contentOptions div.optionForSelect');
//                    $('#slcTpPapel div.contentOptions div.optionData').remove();
//                    $.each(respuesta.datos, function(indice, valor) {
//                        $('div#slcTpPapel div.contentOptions').append(optionModel.clone().attr('id', 'option' + valor.idTipoPapel).show());
//                        if (valor.papel.length > 45) {
//                            var widthOption2 = $('div#slcTpPapel #option' + valor.idTipoPapel).width() + ((valor.papel.length - 45) * 5);
//                            if (widthOption2 > widthOption) {
//                                widthOption = widthOption2;
//                            }
//                            $('div#slcTpPapel #option' + valor.idTipoPapel + ' span').html(valor.papel);
//                        }
//                        $('div#slcTpPapel #option' + valor.idTipoPapel + ' span').html(valor.papel);
//                        if (widthOption > 0) {
//                            $('div#slcTpPapel div.optionData').css('width', widthOption + 'px');
//                        }
//                        $('div#slcTpPapel #option' + valor.idTipoPapel).attr('class', 'optionData');
//                    });
//                }
//            });
//Fin tipos de papel.

//Tipos de impresión.
            $.ajax({
                'url': 'tipoimpresion',
                'type': 'POST',
                'async': false,
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    console.log(respuesta);
                    var widthOption = 0;
                    var optionModel = $('#slcTpImpresion div.contentOptions div.optionForSelect');
                    $('#slcTpImpresion div.contentOptions div.optionData').remove();
                    $.each(respuesta.datos, function(indice, valor) {
                        $('div#slcTpImpresion div.contentOptions').append(optionModel.clone().attr('id', 'option' + valor.idTipoImpresion).show());
                        if (valor.tipoImpresion.length > 45) {
                            var widthOption2 = $('div#slcTpImpresion #option' + valor.idTipoImpresion).width() + ((valor.tipoImpresion.length - 45) * 5);
                            if (widthOption2 > widthOption) {
                                widthOption = widthOption2;
                            }
                            $('div#slcTpImpresion #option' + valor.idTipoImpresion + ' span').html(valor.tipoImpresion);
                        }
                        $('div#slcTpImpresion #option' + valor.idTipoImpresion + ' span').html(valor.tipoImpresion);
                        if (widthOption > 0) {
                            $('div#slcTpImpresion div.optionData').css('width', widthOption + 'px');
                        }
                        $('div#slcTpImpresion #option' + valor.idTipoImpresion).attr('class', 'optionData');
                    });
                }
            });
//Fin tipos de impresión.
        }//Fin consult.

        var idPapel = $('#slcPapelesSeleted div.contentOptions div.optionData')[0];
        idPapel = parseInt(idPapel.id.replace('option', ''));
        console.log(idPapel);
        $.ajax({
            'url': 'consultarConfiguraciones',
            'type': 'POST',
            'async': false,
            'data': {'idPapel': idPapel},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                //Al realizar esta petición recibiremos
                //un objeto JSON con un subObjeto llamado datos
                //que contiene 3 listas de los elementos
                //que deberán mostrarse seleccionados
                //previamente del papel.

                if (respuesta.datos[0].length > 0) {
                    //Chekeamos los check's de tipos de impresión.
                    $.each(respuesta.datos[0], function(indice, valor) {
                        $('#slcTpImpresion div.contentOptions div#option' + valor.tipoImpresion + ' input[type="checkbox"]').prop('checked', true);
                    });
                } else {
                    $('#slcTpImpresion div.optionData input[type="checkbox"]').prop('checked', false);
                }

                if (respuesta.datos[1].length > 0) {
                    //Chekeamos los check's de los modos de impresión.
                    $.each(respuesta.datos[1], function(indice, valor) {
                        $('#slcModosImpresion div.contentOptions div#option' + valor.modoImpresion + ' input[type="checkbox"]').prop('checked', true);
                    });
                } else {
                    $('#slcModosImpresion div.optionData input[type="checkbox"]').prop('checked', false);
                }

                if (respuesta.datos[2].length > 0) {
                    //Chekeamos los check's de los tamanos de papel.
                    $.each(respuesta.datos[2], function(indice, valor) {
                        $('#slcTamanos div.contentOptions div#option' + valor.tamanoPapel + ' input[type="checkbox"]').prop('checked', true);
                    });
                } else {
                    $('#slcTamanos div.optionData input[type="checkbox"]').prop('checked', false);
                }
            }
        });
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
//        console.warn(idTd);
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
        $('#' + idTd + ' input').hide();
        $('#' + idTd + ' span').html($('#' + idTd + ' input').val());
        $('#' + idTd + ' span').show();
        if (idTd.search('t01_descrip') >= 0) {
            var idPapel = idTd.replace('t01_descrip', '');
            var nombreTr = 'tr' + idPapel;
            idPapel = $('#' + nombreTr + ' b').html();
            var nuevoNombre = $('#' + idTd + ' input[type="text"]').val();
            preciosControl.actualizarNombrePapel(idPapel, nuevoNombre);
        } else if (idTd.search('tableGanancias') >= 0) {
            var idPorcentaje = $($('#' + idTd).parent()).attr('id').replace('tr', '');
//            console.log('ID PARENT: ' + idPorcentaje);
            var objJSON = {};
            objJSON.idPorcentaje = idPorcentaje;
            objJSON.inicio = $($('#tableGanancias #tr' + idPorcentaje).find('td')[1]).find('input').val();
            objJSON.fin = $($('#tableGanancias #tr' + idPorcentaje).find('td')[2]).find('input').val();
            objJSON.porcentaje = $($('#tableGanancias #tr' + idPorcentaje).find('td')[3]).find('input').val();
            objJSON.tipoColor = 1;
            objJSON = {'data': JSON.stringify(objJSON)};
            preciosControl.actualizarPorcentajes(objJSON);
        } else if (idTd.search('tableBNGanancias') >= 0) {
            var idPorcentaje = $($('#' + idTd).parent()).attr('id').replace('tr', '');
//            console.log('ID PARENT: ' + idPorcentaje);
            var objJSON = {};
            objJSON.idPorcentaje = idPorcentaje;
            objJSON.inicio = $($('#tableBNGanancias #tr' + idPorcentaje).find('td')[1]).find('input').val();
            objJSON.fin = $($('#tableBNGanancias #tr' + idPorcentaje).find('td')[2]).find('input').val();
            objJSON.porcentaje = $($('#tableBNGanancias #tr' + idPorcentaje).find('td')[3]).find('input').val();
            objJSON.tipoColor = 0;
            objJSON = {'data': JSON.stringify(objJSON)};
            preciosControl.actualizarPorcentajes(objJSON);
        }
    },
    cambiarEstadoInputTextPorcent: function(idTd) {
        $('#' + idTd + ' input').hide();
        $('#' + idTd + ' span').html($('#' + idTd + ' input').val() + '%');
        $('#' + idTd + ' span').show();
        if (idTd.search('tableGanancias') >= 0) {
            var idPorcentaje = $($('#' + idTd).parent()).attr('id').replace('tr', '');
//            console.log('ID PARENT: ' + idPorcentaje);
            var objJSON = {};
            objJSON.idPorcentaje = idPorcentaje;
            objJSON.inicio = $($('#tableGanancias #tr' + idPorcentaje).find('td')[1]).find('input').val();
            objJSON.fin = $($('#tableGanancias #tr' + idPorcentaje).find('td')[2]).find('input').val();
            objJSON.porcentaje = $($('#tableGanancias #tr' + idPorcentaje).find('td')[3]).find('input').val();
            objJSON.tipoColor = 1;
            objJSON = {'data': JSON.stringify(objJSON)};
            preciosControl.actualizarPorcentajes(objJSON);
        }
        if (idTd.search('tableBNGanancias') >= 0) {
            var idPorcentaje = $($('#' + idTd).parent()).attr('id').replace('tr', '');
//            console.log('ID PARENT: ' + idPorcentaje);
            var objJSON = {};
            objJSON.idPorcentaje = idPorcentaje;
            objJSON.inicio = $($('#tableBNGanancias #tr' + idPorcentaje).find('td')[1]).find('input').val();
            objJSON.fin = $($('#tableBNGanancias #tr' + idPorcentaje).find('td')[2]).find('input').val();
            objJSON.porcentaje = $($('#tableBNGanancias #tr' + idPorcentaje).find('td')[3]).find('input').val();
            objJSON.tipoColor = 0;
            objJSON = {'data': JSON.stringify(objJSON)};
            preciosControl.actualizarPorcentajes(objJSON);
        }
    },
    actualizarPorcentajes: function(objJSON) {
        $.ajax({
            'url': 'actualizarPorcentaje',
            'type': 'POST',
            'data': objJSON,
            success: function(data) {
                var respuesta = JSON.parse(data);
//                console.log(respuesta);
            }
        });
    },
    actualizarNombrePapel: function(idPapel, nuevoNombre) {
        $.ajax({
            'url': 'actualizarNombrePapel',
            'type': 'POST',
            'data': {'idPapel': idPapel, 'nuevoNombre': nuevoNombre},
            success: function(data) {
                var respuesta = JSON.parse(data);
//                console.info(respuesta);
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
//                console.info(respuesta);
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
            'async': false,
            success: function(data) {
                var respuesta = JSON.parse(data);
                if (respuesta.codigo > 0) {
//                    console.info(respuesta);

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
                    $('td#t00_operColor span').html('$' + controlPeticiones.formatearValor($('td#t00_operColor input').val()));
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
//                                console.warn(valor.costoImpresion);
                                $('table#tbcostosImpresionMasPapel tr.valores #t02valor_' + valor.idTipoTamano + ' input').val(valor.costoImpresion);
                                $('table#tbcostosImpresionMasPapel tr.valores #t02valor_' + valor.idTipoTamano + ' span').html(controlPeticiones.formatearValor(valor.costoImpresion));

                                //Agregamos los respectivos costos para la tabla costos de impresión blanco y negro(B/N) + papel.
                                $('table#tbcostosImpresionMasPapelBN tr.valores').append(modelo3.clone().attr('id', 't03valor_' + valor.idTipoTamano));
                                $('table#tbcostosImpresionMasPapelBN tr.valores #t03valor_' + valor.idTipoTamano).attr('ondblclick', "preciosControl.mostrarUnInput('t03valor_" + valor.idTipoTamano + "')");
                                $('table#tbcostosImpresionMasPapelBN tr.valores #t03valor_' + valor.idTipoTamano + ' input').attr('onchange', "preciosControl.cambiarEstadoInput('t03valor_" + valor.idTipoTamano + "')");
//                                console.warn(valor.costoImpresion);
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

                            $('table#tbcostosInternosImpresionLaser #tr' + indice + ' input[type="checkbox"').attr('onclick', 'preciosControl.checkboxClick(this)');
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

                            preciosControl.valorizarColorMasPapel(indice, valor.papel);
                        });
                        $('table#tbcostosInternosImpresionLaser').append(footer.clone());
                    }
                    $('#costosInternosImpresionLaser').show(); //Mostramos la tabla.
                    //Fin costos internos.                  

                    var trModelo = $($('#tableGanancias tr.valores2')[0]);
                    $('#tableGanancias tr.valores2').remove();
                    $('#tableGanancias tr.dataset').remove();
                    var contTR = 0;

                    //Inicia el recorrido de los datos consultados, para construir la tabla % Ganancias color.
                    $.each(respuesta.datos[3], function(indice, valor) {
//                        console.log(valor);
                        $('#tableGanancias #trModelo').remove();
                        $('#tableGanancias').append(trModelo.clone().attr('id', 'tr' + valor.idPorcentaje).show());
                        $('#tableGanancias #tr' + valor.idPorcentaje).attr('class', 'dataset');
                        $.each(valor, function(ind, dat) {
                            if (ind != "idPorcentaje") {
                                $('#tableGanancias #tr' + valor.idPorcentaje).append($(trModelo.find('#valorModelo')).clone().attr('id', 'td' + ind).show());
                                $('#tableGanancias #tr' + valor.idPorcentaje).find('#td' + ind).attr('ondblclick', "preciosControl.mostrarUnInput('tableGanancias #tr" + valor.idPorcentaje + " #td" + ind + "')");
                                if (ind == "porcentaje") {
                                    $('#tableGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' input').attr('onchange', "preciosControl.cambiarEstadoInputTextPorcent('tableGanancias #tr" + valor.idPorcentaje + " #td" + ind + "')");
                                    $('#tableGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' span').html(dat + '%');
                                } else {
                                    $('#tableGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' input').attr('onchange', "preciosControl.cambiarEstadoInputText('tableGanancias #tr" + valor.idPorcentaje + " #td" + ind + "')");
                                    $('#tableGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' span').html(dat);
                                }
                                $('#tableGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' input').val(dat);
                            }
                        });
                        contTR++;
                        $($('#tableGanancias #tr' + valor.idPorcentaje).find('td#valorModelo')).attr('id', 'titleRango' + valor.idPorcentaje).html('Rango color ' + contTR);
                        $($('#tableGanancias #tr' + valor.idPorcentaje).find('td#titleRango' + valor.idPorcentaje)).attr('class', 'title-rango');
                    });
                    //Termina el recorrido de los datos consultados, para construir la tabla % Ganancias color.


                    //Inicia el recorrido de los datos para construir la tabla % Ganancias B/N.
                    trModelo = $($('#tableBNGanancias tr.valores2')[0]);
                    $('#tableBNGanancias tr.valores2').remove();
                    $('#tableBNGanancias tr.dataset').remove();
                    contTR = 0;
                    $.each(respuesta.datos[4], function(indice, valor) {
//                        console.log(valor);
                        $('#tableBNGanancias #trModelo').remove();
                        $('#tableBNGanancias').append(trModelo.clone().attr('id', 'tr' + valor.idPorcentaje).show());
                        $('#tableBNGanancias #tr' + valor.idPorcentaje).attr('class', 'dataset');
                        $.each(valor, function(ind, dat) {
                            if (ind != "idPorcentaje") {
                                $('#tableBNGanancias #tr' + valor.idPorcentaje).append($(trModelo.find('#valorModelo')).clone().attr('id', 'td' + ind).show());
                                $('#tableBNGanancias #tr' + valor.idPorcentaje).find('#td' + ind).attr('ondblclick', "preciosControl.mostrarUnInput('tableBNGanancias #tr" + valor.idPorcentaje + " #td" + ind + "')");
                                if (ind == "porcentaje") {
                                    $('#tableBNGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' input').attr('onchange', "preciosControl.cambiarEstadoInputTextPorcent('tableBNGanancias #tr" + valor.idPorcentaje + " #td" + ind + "')");
                                    $('#tableBNGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' span').html(dat + '%');
                                } else {
                                    $('#tableBNGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' input').attr('onchange', "preciosControl.cambiarEstadoInputText('tableBNGanancias #tr" + valor.idPorcentaje + " #td" + ind + "')");
                                    $('#tableBNGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' span').html(dat);
                                }
                                $('#tableBNGanancias #tr' + valor.idPorcentaje).find('#td' + ind + ' input').val(dat);
                            }
                        });
                        contTR++;
                        $($('#tableBNGanancias #tr' + valor.idPorcentaje).find('td#valorModelo')).attr('id', 'titleRango' + valor.idPorcentaje).html('Rango B/N ' + contTR);
                        $($('#tableBNGanancias #tr' + valor.idPorcentaje).find('td#titleRango' + valor.idPorcentaje)).attr('class', 'title-rango');
                    });
                    //Finaliza el recorrido de los datos para construir la tabla % Ganancias B/N.

                    $('#cargador').hide();
                    $('#opciones').slideToggle(500);

                } else {
//                    console.error(respuesta);
                    $('#divContenido').html('');
                    $('#divContenido').append($('#error-data').clone().show());
                }
            }
        });

        $('.tab-content').show();
    },
    valorizarColorMasPapel: function(indice, descrip) {
        var trModelo2 = $('table#tbcostosImpresionMasPapel tr#trModelo');
        var trModelo3 = $('table#tbcostosImpresionMasPapelBN tr#trModelo');
        var valorColor = 0;
        //Calculamos los valores necesarios para la tabla costos de impresión color + papel.
        $('table#tbcostosImpresionMasPapel').append(trModelo2.clone().attr('id', 'tr' + indice).show());
        $('table#tbcostosImpresionMasPapelBN').append(trModelo3.clone().attr('id', 'tr' + indice).show());
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_descrip').html(descrip);
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_descrip').html(descrip);
        //Hallamos el primer valor de precio carta.
        valorColor = parseFloat(controlPeticiones.parseNum(($('table#tbcostosMantenimiento span#costoIColor').html())));
//                ($('table#tbcostosMantenimiento span#costoIColor').html().replace('$', ''));
        console.warn('PRECIO CONCEDIDO: ' + valorColor);
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[1].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_carta' + indice).html().replace('$', '')).replace('.', ''));
        //Una vez hallado el valor correspondiente se lo atribuimos al td correspondiente escribiendolo con la función html().
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_carta').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat(controlPeticiones.parseNum(($('table#tbcostosMantenimiento span#costoIBN').html())));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[1].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_carta' + indice).html().replace('$', '')).replace('.', ''));
        //Una vez hallado el valor correspondiente se lo atribuimos al td correspondiente escribiendolo con la función html().
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_carta').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        //Seguimos calculando los demás valores...
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[2].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_octavo' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_octavo').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[2].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_octavo' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_octavo').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[3].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_tabloide' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_tabloide').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[3].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_tabloide' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_tabloide').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[4].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x48' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_33x48').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[4].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x48' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_33x48').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[5].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x70' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_33x70').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[5].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x70' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_33x70').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));

        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIColor').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapel tr.valores').find('td')[6].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x100' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapel tr#tr' + indice + ' td#t02_precio_33x100').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));
//        
        valorColor = parseFloat((($('table#tbcostosMantenimiento span#costoIBN').html()).replace(',', '.')).replace('$', ''));
        valorColor = valorColor * $('#' + $('table#tbcostosImpresionMasPapelBN tr.valores').find('td')[6].id + ' input').val();
        valorColor = valorColor + parseInt(($('#t01_precio_33x100' + indice).html().replace('$', '')).replace('.', ''));
        $('table#tbcostosImpresionMasPapelBN tr#tr' + indice + ' td#t03_precio_33x100').html(controlPeticiones.formatearValor(controlPeticiones.aproximarDecimal(valorColor)));
    },
    calcularCostosMantenimiento: function() {
        var valorFinal = parseFloat($('td#t00_clipColor input').val());
        valorFinal += parseFloat(($('td#t00_rIColor input').val()));
        valorFinal += parseFloat(($('td#t00_operColor input').val()));

        console.info('VALOR: ' + valorFinal);
        $('span#costoIColor').html('$' + valorFinal);

        valorFinal = parseFloat($('td#t00_clipBn input').val());
        valorFinal += parseFloat(($('td#t00_rIBn input').val()));
        valorFinal += parseFloat(($('td#t00_operBn input').val()));
        $('span#costoIBN').html('$' + valorFinal);
//        console.info('Calculado...');
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

//        console.info('objetoJSON');
//        console.info(objetoJSON);
//        console.info('objetoJSON2');
//        console.info(objetoJSON2);

        $.ajax({
            'url': 'actualizarCostosMantenimiento',
            'type': 'POST',
            'async': false,
            'data': {'dataBn': JSON.stringify(objetoJSON), 'dataColor': JSON.stringify(objetoJSON2)},
            success: function(data) {
                var respuesta = JSON.parse(data);
//                console.info(respuesta);
                if (respuesta.codigo > 0) {
                    $('span#fecha_actualizacion').html(controlPeticiones.formatearHora(respuesta.datos));
                }
            }
        });
        inicio.cargar('paginas/administracionsSitio/panel-usuario/precios.html');
        preciosControl.mostrarAlerta("Se han actualizado los costos de mantenimiento.");
    },
    //Fin costos de mantenimiento.



//    PRECIOS INTERNOS


//    FIN PRECIOS INTERNOS

    mostrarAlerta: function(text) {
        $('#notificaciones p').html(text);
        $('#notificaciones').hide();
        $('#notificaciones').show('slow');
    },
    eventos: function() {
        $('.selectAll').click(function() {
            if ($(this).is(':checked')) {
                $($($(this).parent()).next()).find('input[type="checkbox"]').prop('checked', true);
            } else {
                $($($(this).parent()).next()).find('input[type="checkbox"]').prop('checked', false);
            }
        });
        //Usamos la función delegate para estandrizar el evento que necesitamos
        //que se cumpla en todos los elementos con la clase optionData.
        $(document).bind('.optionData', 'click', function() {
            $($(this).find('input[type="checkbox"]')).click();
        });
        $('#del').click(function() {
            if ($('tr.trSelect').length > 0) {
                $('#confirmar').show('slow');
            } else {
                preciosControl.mostrarAlerta('No ha seleccionado ningún registro.');
            }
        });

        $('#config').click(function() {
            if ($('tr.trSelect').length > 0) {
                preciosControl.dataConfigPapel();
                $('#configPanel').show('slow');
            } else {
                preciosControl.mostrarAlerta('No ha seleccionado ningún registro.');
            }
        });

        $('#siEliminar').click(function() {
            $('#confirmar').hide();
            if ($('tr.trSelect').length > 0) {
                var idDel = 0;
                var respuesta = "Se ha producido un error desconocido.";
                for (i = 0; i < $('tr.trSelect').length; i++) {
                    idDel = $($($('tr.trSelect')[i]).find('b')[0]).html();
//                    console.log('Id enviado: ' + idDel);
                    $.ajax({
                        'url': 'elminarTipoPapel',
                        'type': 'POST',
                        'async': false,
                        'data': {'id': idDel},
                        success: function(data) {
                            respuesta = JSON.parse(data);
//                            console.log(respuesta);
                        }
                    });
                }
                preciosControl.mostrarAlerta(respuesta.mensaje);
                inicio.cargar('paginas/administracionsSitio/panel-usuario/precios.html');
            }
        });


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
        $('table').click(function() {
            $('table input[type="text"]').hide();
            $('table span').show();
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
        $('#guardarBTN').click(function() {
            if ($('#namePaper').val().length > 2 && $('#preciPaper').val().length > 2) {
                var objetoJSON = {};
                objetoJSON.papel = $('#namePaper').val();
                objetoJSON.precioPliego = $('#preciPaper').val();
                objetoJSON = {'data': JSON.stringify(objetoJSON)};
                $.ajax({
                    'url': 'addTipoPapel',
                    'type': 'POST',
                    'data': objetoJSON,
                    success: function(data) {
                        var respuesta = JSON.parse(data);
                        inicio.cargar('paginas/administracionsSitio/panel-usuario/precios.html');
                        preciosControl.mostrarAlerta(respuesta.mensaje);
                    }
                });
            } else {
                alert('Faltan campos en el formulario.');
            }
        });
        $('#btns img#add').click(function() {
            $('#newTipo').show();
            $('#namePaper').focus();            
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

        var indice = parseInt(nombreTr.replace('tr', ''));
        preciosControl.valorizarColorMasPapel(indice, $('#' + $('#' + nombreTr).find('td')[1].id + ' input[type="text"]').val());
    }
};
$(document).ready(function() {
    preciosControl.init();
    preciosControl.eventos();
});