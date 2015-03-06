var formularioTemporal = null;
var archivoSubido = false;
$(document).ready(function() {
    var xhr = null;
    var wd = 0;
//    function fileSelected() {
//        var file = document.getElementById('fileToUpload').files[0];
//        if (file) {
//            var fileSize = 0;
//            var fileSize2 = 0;
//
//            if (file.size > 1048576) {
//                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
//                fileSize2 = file.size + 'MB';
//            }
//            else {
//                if (file.size < 1024) {
//                    fileSize = file.size + 'KB';
//                } else {
//                    fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
//                }
//                fileSize2 = file.size + 'KB';
//            }
////            document.getElementById('fileName').innerHTML = 'Name: ' + file.name;
////            document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
////            document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
//        }
//    }

    function uploadFile() {
        $('#txtArchivosCargados').val("");
        $('div#cargador').show();
        if (formularioTemporal != null) {
            acciones.eliminarArchivo();
        }
        $('#cargaArchivo input').hide();
        $('#cargaArchivo .progressContent').show();
        $('#cargaArchivo img').hide();
        wd = $('#cargaArchivo #progressbar').width() / 100;
        $('#cargaArchivo #progressbar').width(0);
        var fd = new FormData();
        fd.append("fileUP", document.getElementById('fileUP').files[0]);
        xhr = new XMLHttpRequest();
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        xhr.open("POST", "subirArchivosServlet");
        xhr.send(fd);
    }

    function uploadProgress(evt) {
        if (evt.lengthComputable) {
            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
            $('#cargaArchivo #progressbar').width((wd * percentComplete));
            document.getElementById('progressVal').innerHTML = percentComplete.toString() + '%';
        }
        else {
            document.getElementById('progressVal').innerHTML = 'unable to compute';
        }
    }

    function cancelar() {
        xhr.abort();
    }

    function uploadComplete(evt) {
        /* This event is raised when the server send back a response */
//        alert(evt.target.responseText);
        var response = evt.target.responseText;
        $('div#cargador').hide();
        var respuestaArchivo = JSON.parse(response);
//            console.log(respuestaArchivo);
        if (respuestaArchivo.codigo == 1) {
            $('#txtArchivosCargados').val(respuestaArchivo.datos.nombreArchivo);
            formularioTemporal = respuestaArchivo.datos;
            archivoSubido = true;
            $('#txtArchivosCargados').prop('disabled', true);
        }
        else if (respuestaArchivo.codigo == 0) {
            $('#txtArchivosCargados').val("Archivo no admitido.");
            validacionesImpresionLaser.informe('El formato del archivo es invalido, el sistema solo aceptará archivos de imagen (jpg,svg,bmp,png) y documentos pdf.');
            archivoSubido = false;
        } else if (respuestaArchivo.codigo < 0) {
            $('#txtArchivosCargados').val("Error al subir.");
            validacionesImpresionLaser.informe('Se ha producido un error inesperado, vuelve a intentarlo o contacta el adminnistrador.');
            archivoSubido = false;
        }
        mostrarTipoCarga();
    }

    function mostrarTipoCarga() {
        $('#cargaArchivo .progressContent').hide();
        if ($('#cmbxTipoCarga').val() == 1) {
            $($('#cargaArchivo input')[0]).show();
            $($('#cargaArchivo img')[1]).show();
            $($('#cargaArchivo img')[0]).show();
            $($('#cargaArchivo img')[0]).attr('src', 'img/icons/global/cargaArchivo.jpg');
        } else if ($('#cmbxTipoCarga').val() == 2) {
            $($('#cargaArchivo input')[1]).show();
            $($('#cargaArchivo img')[1]).show();
            $($('#cargaArchivo img')[0]).show();
            $($('#cargaArchivo img')[0]).attr('src', 'img/icons/global/compartirLink.jpg');
        }
    }

    function uploadFailed(evt) {
        alert("There was an error attempting to upload the file.");
    }

    function uploadCanceled(evt) {
        alert("The upload has been canceled by the user or the browser dropped the connection.");
    }
    $('#fileUP').change(function() {
        uploadFile();
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