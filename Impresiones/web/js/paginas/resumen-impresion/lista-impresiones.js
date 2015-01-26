
var listaImpresiones = {
    init: function() {
        listaImpresiones.cargar('paginas/resumen-impresion/impresiones-realizadas.html');
    },
    eventos: function() {

    },
    cargar: function(pagina) {
        $("#divContenido")
                .html('')
                .append($('#cargador').clone().show());
        $.post(pagina, function(data) {
            $("#divContenido").html(data);
        });
    },
};
$(document).ready(function() {
    listaImpresiones.init();
});