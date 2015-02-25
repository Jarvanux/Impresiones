var controles = {
    init: function() {
        $('#vinculoImpresion').click(function() {
//            inicio.cargar('paginas/servicios/impresion.html');
        });
        $('#vinculoPapeleria').click(function() {
            inicio.cargar('paginas/servicios/papeleria.html');
        });
    }    
};
controles.init();