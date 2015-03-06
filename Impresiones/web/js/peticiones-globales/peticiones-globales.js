include = "js/peticiones-globales/imagenes.cursor.js";
var respuestaUsuario = null;
var posicionActual = 0;
var posFormulario = 0;
var controlPeticiones = {
    cerrarAlerta: function() {
        $('#notificaciones').hide('explode');
        $('#notificacionError').hide('explode');
    },
    /**
     * formatearHora() : function()
     * Una simple función para convertir un valor de tipo Date() en un String
     * personalizado con el formato (DD/MM/YYYY HH:MM:SS (AM/PM)).
     * @param {type} dateObject Recibe una fecha (Object Date());
     * @returns Devuelve un string con el formato (DD/MM/YYYY HH:MM:SS (AM/PM));
     */

    parseNum: function(num) {
        num = ""+num;
        num = num.replace('$','');
        var num2 = num.split(",");
        num2[0] = num2[0].replace('.', '');
        if (num2.length > 1) {
            num = num2[0] + '.' + num2[1];
        }        
        return num;
    },
    calcularPaginas: function(string, tipo) {
//        console.log('Convocado ' + tipo + " s:" + string);
        var array = {};
        var repited = false;
        var numsNegativos = false;
        var numMayor = false;
        var guionRepetido = false;
        var temp = string.split(",");
        var finalText = "";
        for (var i = 0; i < temp.length; i++) {
            if (temp[i] != "") {
                if (i < (temp.length - 1)) {
                    finalText += temp[i] + ",";
                } else {
                    finalText += temp[i];
                }
            }
        }
        var finalTemp = finalText.split(",");
        for (var i = 0; i < finalTemp.length; i++) {
            if (finalTemp[i].search("-") >= 0) {
                var tempChar = finalTemp[i].split("-");
                if (tempChar.length > 2) {
                    guionRepetido = true;
                    break;
                }
                var num1 = tempChar[0];
                var num2 = tempChar[1];
                finalTemp[i] = "";
                if (num1 == "" || num2 == "") {
                    numsNegativos = true;
                    break;
                }
                num1 = parseInt(num1);
                num2 = parseInt(num2);
                if (num2 < num1) {
                    numMayor = true;
                    break;
                }
                for (var j = (num1); j <= num2; j++) {
                    if (j < num2) {
                        finalTemp[i] += j + ",";
                    } else {
                        finalTemp[i] += j;
                    }
                }
            }
        }
        finalText = finalTemp.toString();
        var temp = finalText.split(",");
        var numPaginas = 0;
        for (var i = 0; i < temp.length; i++) {
            if (array[temp[i]] != "defined") {
                array[temp[i]] = "defined";
                numPaginas++;
            } else {
                repited = true;
            }
        }

        if (!repited) {
            if (!numsNegativos) {
                if (!guionRepetido) {
                    if (!numMayor) {
                        if (tipo == 1) {
                            $('#txtCantVarBN').val(finalText);
                            $('#numPaginasBN').val(numPaginas);
                            $('#numTotalBN').val($('#numPaginasBN').val() * $('#numCopys').val());
//                            console.log('Fijado bn');
                            $('#btnSiguiente').prop('disabled', false);
                        } else if (tipo == 2) {
//                            console.log('Fijado color');
                            $('#txtCantVarColor').val(finalText);
                            $('#numPaginasColor').val(numPaginas);
                            $('#numTotalColor').val($('#numPaginasColor').val() * $('#numCopys').val());
                            $('#btnSiguiente').prop('disabled', false);
                        }
//                        controlPeticiones.imprimirError('Páginas: ' + numPaginas);
                    } else {
                        $('#btnSiguiente').prop('disabled', true);
                        controlPeticiones.imprimirError('Rangos en donde el número inicial es mayor.')
                    }
                } else {
                    $('#btnSiguiente').prop('disabled', true);
                    controlPeticiones.imprimirError('Rangos con formatos invalidos (x-x-x).');
                }
            } else {
                $('#btnSiguiente').prop('disabled', true);
                controlPeticiones.imprimirError('No se permiten números negativos.');
            }
        } else {
            $('#btnSiguiente').prop('disabled', true);
            controlPeticiones.imprimirError('Existen páginas repetidas.');
        }
    },
    imprimirError: function(sms) {
        $('#mensaje span.textMensaje').html('ERROR: ' + sms);
        $('#mensaje').show();
    },
    guardarCodigo: function() {
        $.ajax({
            'url': 'insertarContenteditor',
            'type': 'POST',
            'data': {'text': $('.ace_text-layer').text()},
            success: function(data) {
                var respuesta = JSON.parse(data);
//                console.log(respuesta);
            }
        });
    },
    formatearHora: function(dateObject) {
        var d = new Date(dateObject);
        var day = d.getDate();
        var month = d.getMonth() + 1;
        var year = d.getFullYear();
        var hours = d.getHours();
        var minutes = d.getMinutes();
        var secounds = d.getSeconds();

        if (day < 10) {
            day = "0" + day;
        }
        if (month < 10) {
            month = "0" + month;
        }
        if (hours < 10) {
            hours = "0" + hours;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (secounds < 10) {
            secounds = "0" + secounds;
        }
        var formato = "PN";
        if (hours <= 12) {
            formato = "AM";
        } else {
            formato = "PM";
            hours = hours - 12;
        }
        var date = day + "/" + month + "/" + year + " " + hours + ":" + minutes + ":" + secounds + " " + formato;
//        console.log('hora' + date);

        return date;
    },
    /**
     * formatearValor: un simple scritp que nos permitirá formatear los valores de pesos
     * con la estructura o el formato de puntuación 0.000,00
     * @param {type} valor - recibe enteros y decimales.
     * @returns Un string con el formato 0.000,00
     */
    formatearValor: function(valor) {
        valor = '' + valor;
        if (valor.length <= 21) {
            var temp = valor;
            valor = parseInt(valor);
            valor = '' + valor;
            if (temp.search('[.]') > 0) {
                temp = temp.substring(temp.search('[.]'));
                temp = temp.replace('.', ',');
            } else {
                temp = null;
            }
//            console.info('valor: ' + valor);
//            console.info('temp: ' + temp);
            var contUnidades = 0;
            var conversion = "";
            for (var i = valor.length; i >= 0; i--) {
                conversion += valor.charAt(i);
                if (contUnidades == 3) {
                    conversion += '.';
                    contUnidades = 0;
                }
                contUnidades++;
            }
            //Una vez terminado como lo vimos en el ejemplo obtendriamos 000.1,
            //tenemos que organizar el número.
            valor = "";
            for (var i = conversion.length; i >= 0; i--) {
                valor += conversion.charAt(i);//Y así de fácil organizamos el número :D
            }

            //Ahora buscaremos si el valor inicial recibido era un decimal (1000.23)
            if (valor.search('[.]') == 0) { //Esto es simplemente por si a nuestro for 
                // se le pasó un . de más y quedó al principio de la cadena Ej(.1.000)
                valor = valor.substring(1);
            }
            if (temp != null) {
                //Si es así almacenamos en temp el valor desde el punto de referencia
//                temp = temp.substring(temp.search('[.]')+1);
                //y concatenamos el valor que contiene la cadena convertida + los decimales encontrados
                valor = valor + temp;
            }
            //Si no se cumple la condición anterior simplemente retornamos el valor que hemos convertido previamente.

            return valor;
        } else {
            console.warn('La función formatearValor(), solo permite números con un máximo de 21 dígitos.');
            return 0;
        }
    },
    /**
     * Como su nombre lo indica, recibe un número decimal y devuelve una aproximación del mismo en un entero.
     * @param {type} numero decimal.
     * @returns número entero apróximado.
     */
    aproximarDecimal: function(numero) {
        numero = '' + numero;
        var temp = '' + numero;
        numero = parseInt(numero);
        if (temp.search('[.]') >= 0) {
            temp = temp.substring(temp.search('[.]') + 1);
            temp = temp.substring(0, 1);
            if (parseInt(temp) >= 5) {
                numero = numero + 1;
            }
        } else {
            temp = 0;
        }
        return numero;
    },
    conseguirValorNumerico: function() {

    },
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
        controlPeticiones.calcularPaginas(txt2, 2);
    },
    PaginasRepetidasEnCampos: function(txt, txt2) {
        controlPeticiones.calcularPaginas($('#txtCantVarBN').val() + "," + $('#txtCantVarColor').val(), 3);
    },
    guardarImpresionLaser: function(idUsuario) {
        var objetoJSON = {};
        var tipoColor = 0;
        if ($('#soloBN').is(':checked')) {
            tipoColor = 0;
        } else if ($('#soloColor').is(':checked')) {
            tipoColor = 1;
        } else if ($('#mixto').is(':checked')) {
            tipoColor = 2;
        }
        var objetoJSON = {};
        var tipoColor = 0;
        if ($('#soloBN').is(':checked')) {
            tipoColor = 0;
        } else if ($('#soloColor').is(':checked')) {
            tipoColor = 1;
        } else if ($('#mixto').is(':checked')) {
            tipoColor = 2;
        }
        objetoJSON.tipoColor = tipoColor;
        objetoJSON.guiaImpresion = $('span#codigoImpre').html();
        objetoJSON.hojasBn = $('#numTotalBN').val();
        objetoJSON.hojasColor = $('#numTotalColor').val();
        objetoJSON.copias = $('#numCopys').val();
        objetoJSON.modoImpresion = $('#cmbxModoImpresion').val();
        objetoJSON.tamanoPapel = $('#cmbxTipoTamano').val();
        objetoJSON.tipoPapel = $('#cmbxTipoPapel').val();
        objetoJSON.tipoCarga = $('#cmbxTipoCarga').val();
        objetoJSON.rutaArchivo = formularioTemporal.rutaArchivo;
        objetoJSON.nombreArchivo = formularioTemporal.nombreArchivo;
        objetoJSON.linkArchivo = $('#txtLinkArchivos').val();
        objetoJSON.anillado = $('#anilladoSi').is(':checked');
        objetoJSON.colorAnillado = $('#cmbxColorAnillo').val();
        objetoJSON.colorTapa1 = $('#cmbxColorTapas').val();
        objetoJSON.colorTapa2 = $('#cmbxColorTapas2').val();
        objetoJSON.plastificado = $('#cmbxPlastificado').val();
        objetoJSON.servicioCorte = $('#cmbxSCorte').val();
        objetoJSON.cosidoTipoRevista = $('#cmbxSCorte').val();
        objetoJSON.instruccionesEspeciales = $('#txtaIAdicionales').val();
        objetoJSON.valorTotal = $('span#rtotal').html();
        objetoJSON = {'data': JSON.stringify(objetoJSON), 'idUsuario': idUsuario};
        controlPeticiones.insertar('guardarImpresionLaser', 'POST', objetoJSON, null);
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
//                console.log(respuesta);
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
    registrarEIngresar: function(url, metodo, objetoJSON, divMensaje) {
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
//                console.log(respuesta);
                switch (parseInt(respuesta.codigo)) {
                    case 1:
                        $('#usuario').val($('#email').val());
                        $('#password').val($('#contrasena').val());
                        controlIngresar.ingresar();
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

