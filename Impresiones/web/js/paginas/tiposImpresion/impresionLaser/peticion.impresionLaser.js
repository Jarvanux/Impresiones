//Datos para calcular el valor final.
modoImpresion = null;
tamanoPapel = null;
TipoPapel = null;
num1 = 0;
num2 = 0;
tempCont = 0;
resultado = 0;
ListaColores = {"colores": [
        {"nombre": "Negro", "idColor": 1, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/imgNegra"},
        {"nombre": "Azul", "idColor": 2, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/imgAzul"},
        {"nombre": "Blanco", "idColor": 3, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/imgBlanca"},
        {"nombre": "Transparente", "idColor": 3, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/imgTransparente"},
        {"nombre": "Rojo", "idColor": 4, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/imgRoja"},
        {"nombre": "Verde", "idColor": 5, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/imgVerde"},
    ]};
ListaColoresAnillo = {"colores": [
        {"nombre": "Negro", "idColor": 1, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/anilloNegro.png"},
        {"nombre": "Azul", "idColor": 2, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/anilloAzul.png"},
        {"nombre": "Blanco", "idColor": 3, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/anilloBlanco.png"},
        {"nombre": "Rojo", "idColor": 4, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/anilloRojo.png"},
        {"nombre": "Verde", "idColor": 5, "urlImg": "img/data/paginas/impresionLaser/conversionesIMG/anilloVerde.png"},
    ]};
colorConsultado = null;
colorAnilloConsultado = null;
//Fin datos para calcular el valor final.
var peticionesImpresionLaser = {
    consultarColoresPorID: function(id) {
        var datos = null;
        for (var i = 0; i < ListaColores.colores.length; i++) {
            datos = ListaColores.colores[i];
            if (datos.idColor == id) {
                colorConsultado = datos;
            }
        }
    },
    consultarColoresAnilloPorID: function(id) {
        var datos = null;
        for (var i = 0; i < ListaColoresAnillo.colores.length; i++) {
            datos = ListaColoresAnillo.colores[i];
            if (datos.idColor == id) {
                colorAnilloConsultado = datos;
            }
        }
    },
    modosImpresion: function(cmbx) {
        $.ajax({
            'url': 'modoimpresion', //Nombre de instancia del servlet.
            success: function(data) {
                cmbx.html(''); //Reiniciamos y/o formateamos el contenido del elemento.                
//                alert(data);
                var respuesta = JSON.parse(data); //Concatenamos el objeto recibido a un objeto de JSON.                
                cmbx.append(new Option("Selecciona", "-1"));
                $.each(respuesta.datos, function(indice, valor) {
                    cmbx.append(new Option(valor.modo, valor.idModoImpresion));
                });
            }
        });
    },
    tipoPapel: function(cmbx) {
        $.ajax({
            'url': 'tipopapel', //Nombre de instancia del servlet.
            success: function(data) {
                cmbx.html(''); //Reiniciamos y/o formateamos el contenido del elemento.
                var respuesta = JSON.parse(data); //Concatenamos el objeto recibido a un objeto de JSON.
                cmbx.append(new Option("Selecciona", -1));
                $.each(respuesta.datos, function(indice, valor) {
                    cmbx.append(new Option(valor.papel, valor.idTipoPapel));
                });
            }
        });
    },
    colores: function(cmbx) {
        cmbx.html('');
        cmbx.append(new Option("Selecciona", -1));
        $.each(ListaColores.colores, function(indice, valor) {
            cmbx.append(new Option(valor.nombre, valor.idColor));
        });
    },
    coloresAnillo: function(cmbx) {
        cmbx.html('');
        cmbx.append(new Option("Selecciona", -1));
        $('#cmbxColorAnillo').find('option')[0].value
        $.each(ListaColoresAnillo.colores, function(indice, valor) {
            cmbx.append(new Option(valor.nombre, valor.idColor));
        });
    },
    tipoPlasticos: function(cmbx) {
        $.ajax({
            'url': 'tipoPlastico',
            success: function(data) {
                cmbx.html('');
                var respuesta = JSON.parse(data);
                cmbx.append(new Option("Selecciona", -1));
                $.each(respuesta.datos, function(indice, valor) {
                    cmbx.append(new Option(valor.tipo, valor.idTipoPlastico));
                });
            }
        });
    },
    tipoCorte: function(cmbx) {
        $.ajax({
            'url': 'serviciosCorte',
            success: function(data) {
                cmbx.html('');
                var respuesta = JSON.parse(data);
                cmbx.append(new Option("Selecciona", -1));
                $.each(respuesta.datos, function(indice, valor) {
                    cmbx.append(new Option(valor.tipo, valor.idTipoCorte));
                });
            }
        });
    },
    tamanoPapel: function(cmbx) {
        $.ajax({
            'url': 'tamanopapel', //Nombre de instancia del servlet.
            success: function(data) {
                cmbx.html(''); //Reiniciamos y/o formateamos el contenido del elemento.
                var respuesta = JSON.parse(data); //Concatenamos el objeto recibido a un objeto de JSON.
                cmbx.append(new Option("Selecciona", -1));
                $.each(respuesta.datos, function(indice, valor) {
                    cmbx.append(new Option(valor.tipoTamano, valor.idTipoTamano));
                });
            }
        });
    },
    consultarImgModoImpre: function() {
        $('#imgModo').removeAttr("src");
        $('#imgModo').attr({"src": "img/icons/global/loader.gif"});
        $('#imgModo').show();
//        $('#imgModo').css({"src": "img/icons/global/loader.gif"});        
        $('#imgModoImpre').slideDown(500);
        $.ajax({
            'url': 'imgmodoimpre', //Nombre de instancia del serlet.
            'type': 'POST',
            'data': {'idModoSelect': $('#cmbxModoImpresion').val()},
            success: function(data) {
                var respuesta = JSON.parse(data);
                modoImpresion = respuesta;
                console.log(modoImpresion);
                if (respuesta.codigo > 0) {
                    $('#imgModo').removeAttr("src");
                    $('#imgModo').attr({"src": respuesta.datos.urlImg});
                } else {
                    $('#imgModoImpre').hide();
                }
            }
        });
    },
    consultarTamanoPapel: function() {
        $.ajax({
            'url': 'tipopapel',
            'type': 'POST',
            'data': {'idTipoPapel': 0},
            success: function(data, textStatus, jqXHR) {

            }
        });
    },
    subirArchivo: function() {
        $.ajax({
            'url': 'subirArchivosServlet',
            type: 'POST',
            'data': {'archivo': $('#formFile')},
            success: function(data) {
                var respuesta = JSON.parse(data);
                alert(respuesta.descripcion);
            }
        });
    },
    consultarValorImpre: function() {
        var tipoColor = -1;
        var totalPaginas = 0;
        var totalPaginaColor = 0;
        if ($('#soloBN').is(':checked')) {
            tipoColor = 0;
            totalPaginas = $('#numTotalBN').val();
        } else if ($('#soloColor').is(':checked')) {
            tipoColor = 1;
            totalPaginas = $('#numTotalColor').val();
        } else if ($('#mixto').is(':checked')) {
            tipoColor = 2;
        }
        $.ajax({
            'url': 'calcularvalorimpre',
            'type': 'POST',
            'data': {
                'idTipoColor': tipoColor,
                'idModoImpre': $('#cmbxModoImpresion').val(),
                'idTamanoPapel': $('#cmbxTipoTamano').val(),
                'idTipoPapel': $('#cmbxTipoPapel').val(),
                'numeroHojas': totalPaginas
            },
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if (respuesta.codigo > 0) {
                    $('#valorUnitario').html('$' + respuesta.datos.valorImpresion);
                    //Vamos a hacer uso del modo seleccionado para hallar el valor final.
                    //Una cara normal
                    if ($('#cmbxModoImpresion').val() == 1) {
                        $('#valorTotal').html('$' + (respuesta.datos.valorImpresion * totalPaginas));
                    }
                    //Por doble cara.
                    else if ($('#cmbxModoImpresion').val() == 2) {
                        if ($('#numTotalBN').val() > 1 || ($('#numTotalColor').val() > 1)) {
                            $('#valorTotal').html('$' + ((respuesta.datos.valorImpresion * totalPaginas) / 2));
                        } else {
                            $('#mensaje').slideDown(500);
                            $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor a uno.');
                        }
                    }
                    //Dos páginas en una cara
                    else if ($('#cmbxModoImpresion').val() == 3) {
                        if ($('#numTotalBN').val() > 1 || ($('#numTotalColor').val() > 1)) {
                            $('#valorTotal').html('$' + ((respuesta.datos.valorImpresion * totalPaginas) / 2));
                        } else {
                            $('#mensaje').slideDown(500);
                            $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor a uno.');
                        }
                    }
                    //Dos páginas en dos caras.
                    else if ($('#cmbxModoImpresion').val() == 4) {
                        if ($('#numTotalBN').val() >= 4 || ($('#numTotalColor').val() >= 4)) {
                            $('#valorTotal').html('$' + ((respuesta.datos.valorImpresion * totalPaginas) / 4));
                        } else {
                            $('#mensaje').slideToggle(500);
                            $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor o igual a 4.');
                        }
                    }
                    //Cuatro páginas en una cara.
                    else if ($('#cmbxModoImpresion').val() == 5) {
                        if ($('#numTotalBN').val() >= 4 || ($('#numTotalColor').val() >= 4)) {
                            $('#valorTotal').html('$' + ((respuesta.datos.valorImpresion * totalPaginas) / 4));
                        } else {
                            $('#mensaje').slideToggle(500);
                            $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor o igual a 4.');
                        }
                    }
                    //Cuatro páginas en dos caras.
                    else if ($('#cmbxModoImpresion').val() == 6) {
                        if ($('#numTotalBN').val() >= 8 || ($('#numTotalColor').val() >= 8)) {
                            $('#valorTotal').html('$' + ((respuesta.datos.valorImpresion * totalPaginas) / 8));
                        } else {
                            $('#mensaje').slideToggle(500);
                            $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor o igual a 8.');
                        }
                    }
                } else {
                    $('#mensaje').slideToggle(500);
                    $('#mensaje span.textMensaje').html('Error al calcular el valor de la impresión, hay un problema en el sistema.');
                }
            }
        });
    },
    consultarValorImpreMix2: function(condicion) {
        var tipoColor = -1;
        var totalPaginas = 0;
        var totalPaginaColor = 0;
        if (condicion == 0) {
            tipoColor = 0;
            totalPaginas = $('#numTotalBN').val();
        } else if (condicion == 1) {
            tipoColor = 1;
            totalPaginas = $('#numTotalColor').val();
        } else if (condicion == 2) {
            tipoColor = 2;
        }
        $.ajax({
            'url': 'calcularvalorimpre',
            'type': 'POST',
            'data': {
                'idTipoColor': tipoColor,
                'idModoImpre': $('#cmbxModoImpresion').val(),
                'idTamanoPapel': $('#cmbxTipoTamano').val(),
                'idTipoPapel': $('#cmbxTipoPapel').val(),
                'numeroHojas': totalPaginas
            },
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if (respuesta.codigo > 0) {
//                    $('#valorUnitario').html('$' + respuesta.datos.valorImpresion);
                    if (condicion == 0) {
                        num1 = respuesta.datos.valorImpresion;
                    } else if (condicion == 1) {
                        num2 = respuesta.datos.valorImpresion;
                        $('#valorFinalUnitario span').html('$' + num1 + ' |<span style="color: #9FFB95;">| $' + num2 + '</span>');
                        resultado = (num1 * $('#numTotalBN').val()) + (num2 * $('#numTotalColor').val());
                        //Vamos a hacer uso del modo seleccionado para hallar el valor final.
                        //Una cara normal
                        if ($('#cmbxModoImpresion').val() == 1) {
                            $('#valorTotal').html('$' + (resultado));
                        }
                        //Por doble cara.
                        else if ($('#cmbxModoImpresion').val() == 2) {
                            if ($('#numTotalBN').val() > 1 || ($('#numTotalColor').val() > 1)) {
                                $('#valorTotal').html('$' + ((resultado) / 2));
                            } else {
                                $('#mensaje').slideDown(500);
                                $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor a uno.');
                            }
                        }
                        //Dos páginas en una cara
                        else if ($('#cmbxModoImpresion').val() == 3) {
                            if ($('#numTotalBN').val() > 1 || ($('#numTotalColor').val() > 1)) {
                                $('#valorTotal').html('$' + ((resultado * totalPaginas) / 2));
                            } else {
                                $('#mensaje').slideDown(500);
                                $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor a uno.');
                            }
                        }
                        //Dos páginas en dos caras.
                        else if ($('#cmbxModoImpresion').val() == 4) {
                            if ($('#numTotalBN').val() >= 4 || ($('#numTotalColor').val() >= 4)) {
                                $('#valorTotal').html('$' + ((resultado) / 4));
                            } else {
                                $('#mensaje').slideToggle(500);
                                $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor o igual a 4.');
                            }
                        }
                        //Cuatro páginas en una cara.
                        else if ($('#cmbxModoImpresion').val() == 5) {
                            if ($('#numTotalBN').val() >= 4 || ($('#numTotalColor').val() >= 4)) {
                                $('#valorTotal').html('$' + ((resultado) / 4));
                            } else {
                                $('#mensaje').slideToggle(500);
                                $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor o igual a 4.');
                            }
                        }
                        //Cuatro páginas en dos caras.
                        else if ($('#cmbxModoImpresion').val() == 6) {
                            if ($('#numTotalBN').val() >= 8 || ($('#numTotalColor').val() >= 8)) {
                                $('#valorTotal').html('$' + ((respuesta.datos.valorImpresion * totalPaginas) / 8));
                            } else {
                                $('#mensaje').slideToggle(500);
                                $('#mensaje span.textMensaje').html('El número de hojas debe ser mayor o igual a 8.');
                            }
                        }
                    }
                } else {
                    $('#mensaje').slideToggle(500);
                    $('#mensaje span.textMensaje').html('Error al calcular el valor de la impresión, hay un problema en el sistema.');
                }
            }
        });
    },
    consultarValorAnillado: function(numBloques) {
        $.ajax({
            'url': 'consultaValorAnillado',
            type: 'POST',
            'data': {'numero': numBloques},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if(respuesta.codigo > 0){
                    
                }else{
                    
                }
            }
        });
    },
    consultarValorPlastificado: function(numPaginas, tipo) {
        $.ajax({
            'url': 'consultaValorPlastificado',
            type: 'POST',
            'data': {'numero': numPaginas},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if(respuesta.codigo > 0){
                    
                }else{
                    
                }
            }
        });
    },
    consultarValorCorte: function(numPaginas) {
        $.ajax({
            'url': 'consultaValorCorte',
            type: 'POST',
            'data': {'numero': numPaginas},
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if(respuesta.codigo > 0){
                    
                }else{
                    
                }
            }
        });
    },
    consultarValorCosido: function() {

    }
};