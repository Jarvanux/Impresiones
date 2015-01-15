var formularioTemporal = null;
var archivoSubido = false;
$(document).ready(function() {
//    console.log('Fjadksfjsaflf');
    $('#btnFile').uploader({
        'url': 'subirArchivosServlet',
        onStart: function() {
//            console.log('star');
            $('div#cargador').show();
            if (formularioTemporal != null) {
                acciones.eliminarArchivo();
            }
        },
        onComplete: function(response) {
//                appUi.closeLoading();
            $('div#cargador').hide();
            var respuestaArchivo = JSON.parse(response);
//            console.log(respuestaArchivo);
            if (respuestaArchivo.codigo == 1) {
                $('#txtArchivosCargados').val(respuestaArchivo.datos.nombreArchivo);
                formularioTemporal = respuestaArchivo.datos;
                archivoSubido = true;
            }
            else if (respuestaArchivo.codigo == 0) {
                $('#txtArchivosCargados').val(respuestaArchivo.datos.nombreArchivo);
                validacionesImpresionLaser.informe('El formato del archivo es invalido, el sistema solo aceptará archivos de imagen (jpg,svg,bmp,png) y documentos pdf.');
                archivoSubido = false;
            } else if (respuestaArchivo.codigo < 0) {
                $('#txtArchivosCargados').val(respuestaArchivo.datos.nombreArchivo);
                validacionesImpresionLaser.informe('Se ha producido un error inesperado, vuelve a intentarlo o contacta el adminnistrador.');
                archivoSubido = false;
            }
        }
    });
});

var acciones = {
    eliminarArchivo: function() {
//        console.log('Eliminando...');
        $.ajax({
            'url': 'eliminarArchivo',
            'data': {'rutaArchivo': formularioTemporal.rutaArchivo},
            success: function(data) {
//                var respuesta = JSON.parse(data);
//                console.log(respuesta);
                formularioTemporal = null;
            }
        });
    }
};