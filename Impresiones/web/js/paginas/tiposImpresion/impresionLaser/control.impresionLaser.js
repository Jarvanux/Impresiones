////En este objeto se encuentran los nombres de los componentes que no serán visibles cuando 
//carge el formulario, además será la lista que usará el sistema como referencia para
//saber que elemento de la vista debe mostrar por cada paso posteriormente.
ObjetoElementos = {"elementos": [
        {"nombre": "infoMixto"},
        {"nombre": "totalesImpre"},
        {"nombre": "modoImpresion"},
        {"nombre": "tamanoImpresion"},
        {"nombre": "tipoPapel"},
        {"nombre": "cargaArchivo"},
        {"nombre": "tipoColor"},
        {"nombre": "divSoloBN"},
        {"nombre": "divSoloColor"},
        {"nombre": "divMixto"},
        {"nombre": "btnsPasos"},
        {"nombre": "imgModoImpre"},
        {"nombre": "txtLinkArchivos"},
        {"nombre": "valoresImpre"},
        {"nombre": "txtCantBN"},
        {"nombre": "lblCantBN"},
        {"nombre": "imgRegresarBN"},
        {"nombre": "txtAtras"},
        {"nombre": "txtBNVar"},
        {"nombre": "txtCantVarBN"},
        {"nombre": "txtAtras2"},
        {"nombre": "imgRegresarBN2"},
        {"nombre": "txtCantColor"},
        {"nombre": "lblCantColor"},
        {"nombre": "imgRegresarColor"},
        {"nombre": "txtAtrasColor"},
        {"nombre": "txtColorVar"},
        {"nombre": "txtAtrasColor2"},
        {"nombre": "txtCantVarColor"},
        {"nombre": "imgRegresarColor2"},
        {"nombre": "continuarForm"},
        {"nombre": "btnSiguiente"},
        {"nombre": "fileUP"},
        {"nombre": "descripParamas"},
        {"nombre": "tipoCargaSelect"},
        {"nombre": "numPaginasBN"},
        {"nombre": "numPaginasColor"},
        {"nombre": "contentD2"},
        {"nombre": "contentD3"},
        {"nombre": "colorAnillo"},
        {"nombre": "colorTapas"},
        {"nombre": "colorTapas2"},
        {"nombre": "cmbxPlastificado"},
        {"nombre": "cmbxSCorte"},
        {"nombre": "smsInformativo"},
        {"nombre": "anilladoImgs"},
        {"nombre": "nums"},
        {"nombre": "info"},
        {"nombre": "subForm"},
        {"nombre": "vistaAnillado"},
        {"nombre": "servicioCorte"},
        {"nombre": "td5Add"},
        {"nombre": "contentCorte"},
        {"nombre": "td5Anillado span,#numAnill,#td5Anillado img.imgAyuda"},
    ]};
//En este objeto se encuentran los pasos del formulario.
ObjetoPasos = {"pasos": [
        {"descripcion": "<b>1 Paso: </b>Selecciona un tipo de color."},
        {"descripcion": "<b>2 Paso: </b>Parametriza los datos del tipo de color seleccionado."},
        {"descripcion": "<b>3 Paso: </b>Si deseas cambiar el número de copias de impresión, cambia el valor en la casilla que se ha habilitado."},
        {"descripcion": "<b>4 Paso: </b>Selecciona la manera en la que deseas imprimir(Una cara, Dos caras,etc.)."},
        {"descripcion": "<b>5 Paso: </b>Selecciona el tamaño del papel de tú impresión"},
        {"descripcion": "<b>6 Paso: </b>Selecciona el tipo de papel que deseas usar para tú impresión"},
        {"descripcion": "<b>7 Paso: </b>Carga el archivo que vas a imprimir desde tú equipo, o compartenos un link."}
    ]};
datatxtCantColor = 0;
var controlImpresionLaser = {
    init: function() {
        //Si la url actual equivale al panel de usuario simplemente se aplican y reemplazan algunos
        //estilos.
        if (location.href == "http://localhost:8080/impresiones/panelusuario.html") {
            $('div#divContenido').css({
                'padding': '50px 0px 0px 0px',
                'height': '570px',
                'box-shadow': '0px 0px 3px gray'
            });
            //Modificamos la altura del contenedor del carrito y el mismo.
            $('div#contentQ').css({'height': '570px'});
            
            $('div#cuerpoCarrito').css({
                'height': '520px',
                'background': '#fff',
            });
            //Fin edición carrito.
            
            $('#contentD3').css({'height': '500px','max-heigh': '500px'});

        }
//        if (document.getElementById("le_truquito") == undefined)
//        {
//            window.alert("Si se produce algún tipo de error en la funcionalidad del sistema, desactiva el AdBlock que te está protegiendo de anuncios.");
//        }
        //Citamos la funcionalidad de la instancia imagenesCursor para que las imagenes 
        //contenidas en su objeto se les
        //aplique el estilo cursor:pointer de css.
        $('#pop-Ayuda').hide();
        imagenesCursor.convertir();
        $('#link').attr({"disabled": "disabled"});
        eventosImpresionLaer.eventos();
        $('#mensaje').hide();
        contPaso = 0;
        huboCambioColor = false;
        $('.spanPaso').html(ObjetoPasos.pasos[contPaso].descripcion);
        for (i = 0; i < ObjetoElementos.elementos.length; i++) {
            $('#' + ObjetoElementos.elementos[i].nombre).css({"display": "none"});
        }
        //Realizamos las respectivas peticiones para llenar los combox con los datos
        //respectivamente traidos de la BD.
        peticionesImpresionLaser.coloresAnillo($('#cmbxColorAnillo'));
        peticionesImpresionLaser.colores($('#cmbxColorTapas'));
        peticionesImpresionLaser.colores($('#cmbxColorTapas2'));
        peticionesImpresionLaser.tipoPlasticos($('#cmbxPlastificado'));
        peticionesImpresionLaser.tipoCorte($('#cmbxSCorte'));
        peticionesImpresionLaser.modosImpresion($('#cmbxModoImpresion'));
        peticionesImpresionLaser.tipoPapel($('#cmbxTipoPapel'));
        peticionesImpresionLaser.tamanoPapel($('#cmbxTipoTamano'));
    },
    siguientePaso: function() {
        if (contPaso == 0) {
            validacionesImpresionLaser.validarTipoColor();
        } else if (contPaso == 1) {
            validacionesImpresionLaser.validarTipoColorParams();
        } else if (contPaso == 2) {
            validacionesImpresionLaser.validarNumCopias();
        } else if (contPaso == 3) {
            validacionesImpresionLaser.validarModoImpresion();
        } else if (contPaso == 4) {
            validacionesImpresionLaser.validarTamanoImpresion();
        } else if (contPaso == 5) {
            validacionesImpresionLaser.validarTipoPapel();
        } else {
            contPaso -= 1;
        }
    },
    pasoDos: function() {
        $('.spanPaso').html(ObjetoPasos.pasos[1].descripcion);
        contPaso = 1;
    },
    insertarImpresion: function() {
        var url = 'insertarprimerform';
        var metodo = 'POST';
        var divContent = $('#contentD');
        var divMensaje = $('');
        var objetoJSON;
        var datos = {};
        datos.tipoColor = tipoColorSeleccionado;
        if (datos.tipoColor == 1) {
            datos.hojasBn = divContent.find('#numTotalBN').val();
        } else if (datos.tipoColor == 2) {
            datos.hojasColor = divContent.find('#numTotalColor').val();
        } else if (datos.tipoColor == 3) {
            datos.hojasBn = divContent.find('#numTotalBN').val();
            datos.hojasColor = divContent.find('#numTotalColor').val();
        }
//        if(datos.tipoColor == )
        datos.copias = divContent.find('input#numCopys').val();
        datos.modoImpresion = divContent.find('#cmbxModoImpresion').val();
        datos.tamanoPapel = divContent.find('#cmbxTipoTamano').val();
        datos.tipoPapel = divContent.find('#cmbxTipoPapel').val();
        datos.tipoCarga = divContent.find('#cmbxTipoCarga').val();
        if (datos.tipoCarga == 1) {
            datos.rutaArchivo = "Ruta";
        } else {
            datos.linkArchivo = divContent.find("#txtLinkArchivos").val();
        }
        divContent = $('#contentD2');
        if (divContent.find("#anillado").is(":checked")) {
            datos.anillado = true;
            datos.colorAnillo = divContent.find('#cmbxColorAnillo').val();
            datos.colorTapa1 = divContent.find('#cmbxColorTapas').val();
            datos.colorTapa2 = divContent.find('#cmbxColorTapas2').val();
        } else {
            datos.anillado = false;
        }
        datos.instruccionesEspeciales = divContent.find('#txtaIAdicionales').val();
        datos.valorTotal = divContent.find('#valorTotal2').html();
        objetoJSON = {'impresionlaser': JSON.stringify(datos)};
        controlPeticiones.insertar(url, metodo, objetoJSON, divMensaje);
    }
};
controlImpresionLaser.init();